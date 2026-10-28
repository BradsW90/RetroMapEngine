package main;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.Node;

public class GridView {

    private static boolean painting = false;

    public static GridPane defaultGrid(GridPane grid, TileBar tile) {
        for(int row=0; row<50; row++) {
                for(int col=0; col<50; col++) {
                    VBox cell = new VBox();
                    
                    ImageView cellImage = new ImageView(tile.getImage());
                    cellImage.setFitWidth(48);
                    cellImage.setFitHeight(48);
                    cellImage.setPreserveRatio(true);
                    cellImage.setMouseTransparent(true);
                    
                    cell.getChildren().setAll(cellImage);
                    
                    cell.setUserData(tile.copy());
                    
                    Editor.grid.setOnMousePressed(event -> {
                        if(event.isPrimaryButtonDown()) {
                            painting = true;
                            
                            Node node = event.getPickResult().getIntersectedNode();
                            while(node != null && !(node instanceof VBox)) {
                                node = node.getParent();
                            }
                            
                            if(node instanceof VBox gridCell) {
                                paintCell(gridCell);
                            }
                        }
                    });
                    
                    Editor.grid.setOnMouseReleased(event -> {
                        painting = false;
                        Editor.tileBar.setMouseTransparent(false);
                    });
                    
                    cell.setOnMouseDragged(event -> {
                        if(!painting) return;
                        
                        Editor.tileBar.setMouseTransparent(true);
                        
                        Node node = event.getPickResult().getIntersectedNode();
                        
                        while(node != null && !(node instanceof VBox)) {
                            node = node.getParent();
                        }
                        
                        if(node instanceof VBox gridCell && Editor.grid.getChildren().contains(cell)) {
                            paintCell(gridCell);
                        }
                    });
                    
                    grid.add(cell,col,row);
                }
        }
        return grid;
    }
    
    private static void paintCell(VBox cell) {
        
        ImageView newCellImage = new ImageView(TileBar.selectedTile.getImage());
        newCellImage.setFitWidth(48);
        newCellImage.setFitHeight(48);
        newCellImage.setPreserveRatio(true);
        newCellImage.setMouseTransparent(true);
        
        cell.getChildren().setAll(newCellImage);
        cell.setUserData(TileBar.selectedTile);
    }
}