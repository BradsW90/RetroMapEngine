package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Editor {

    public static VBox tileBar;
    public static GridPane grid;
    public static Path path = Paths.get("Path/to/assests/folder");

    public static void editorView(Stage stage) {
        
        try {
            
            //TOOLBAR
            HBox toolBar = new HBox();
            toolBar.setSpacing(5);
            toolBar.setPadding(new Insets(5));
            toolBar.getStyleClass().add("toolbox-border");
            
            //TILE WINDOW
            tileBar = new VBox();
            tileBar.setSpacing(2);
            tileBar.setPadding(new Insets(5));
            TileBar[] baseTiles = TileBar.loadFromAssets(path);
            tileBar = TileBar.populateTileBar(tileBar, baseTiles);
            
            //TILE CONTAINER
            ScrollPane tileContainer = new ScrollPane(tileBar);
            tileContainer.getStyleClass().add("tilebar-container");
            tileContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            tileContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            
            //GRID
            grid = new GridPane();
            grid.getStyleClass().add("grid");
            grid = GridView.defaultGrid(grid, baseTiles[0]);
            
            //GRID CONTAINER
            ScrollPane container = new ScrollPane(grid);
            container.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            container.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            
            //BASE LAYOUT
            BorderPane baseLayout = new BorderPane();
            baseLayout.getStyleClass().add("baselayout");
            
            //SCENE SETTINGS
            Scene mainScreen = new Scene(baseLayout,900,600);
            mainScreen.getStylesheets().add(Editor.class.getResource("application.css").toExternalForm());
            
            //FINAL
            baseLayout.setCenter(container);
            toolBar = ToolBar.loadTools(toolBar);
            baseLayout.setTop(toolBar);
            baseLayout.setLeft(tileContainer);
            stage.setTitle("RetroMapEngine");
            stage.setScene(mainScreen);
            stage.show();
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}