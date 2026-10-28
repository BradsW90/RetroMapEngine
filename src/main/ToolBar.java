package main;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class ToolBar {
    public static HBox loadTools(HBox toolBar) {
        Button toolOne = new Button("New");
        toolOne.setOnAction(e -> {
            Stage stage = (Stage) toolOne.getScene().getWindow();
            restart(stage);
        });
        
        Button toolTwo = new Button("Load");
        Button toolFive = new Button("Save");
        toolFive.setOnAction(e -> {
        Stage stage = (Stage) toolFive.getScene().getWindow();
        saveGrid(stage);
        });
        
        Button toolThree = new Button("Fill");
        Button toolFour = new Button("Randomize");
        
        toolBar.getChildren().add(toolOne);
        toolBar.setMargin(toolOne, new Insets(0,0,0,5));
        toolBar.getChildren().add(toolTwo);
        toolBar.getChildren().add(toolFive);
        toolBar.getChildren().add(toolThree);
        toolBar.getChildren().add(toolFour);
        
        return toolBar;
    }
    
    private static int[][] exportGrid(int rows, int cols) {
        int[][] data = new int[rows][cols];
        
        for(Node node : Editor.grid.getChildren()) {
            if(node instanceof VBox cell) {
                Integer row = GridPane.getRowIndex(cell);
                Integer col = GridPane.getColumnIndex(cell);
                
                TileBar tile = (TileBar) cell.getUserData();
                data[row][col] = (tile == null) ? 0 : tile.getId();
            }
        }
        
        return data;
        
    }
    
    private static File showSaveDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Map");
        
        //fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
        
        fileChooser.setInitialFileName("map.txt");
        
        return fileChooser.showSaveDialog(stage);
    }
    
    private static void writeGridToFile(File file, int[][] gridData) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(int row = 0; row < gridData.length; row++) {
                for(int col = 0; col < gridData[row].length; col++) {
                    writer.write(Integer.toString(gridData[row][col]));
                    if(col < gridData[row].length -1) {
                        writer.write(" ");
                    }
                }
                
                writer.newLine();
            }
        }
    }
    
    public static void saveGrid(Stage stage) {
        File file = showSaveDialog(stage);
        if(file == null) return;
        
        int[][] gridData = exportGrid(50, 50);
        
        try {
            writeGridToFile(file, gridData);
            System.out.println("Saved to: " + file.getAbsolutePath());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void restart(Stage stage) {
        Editor.editorView(stage);
    }   
}