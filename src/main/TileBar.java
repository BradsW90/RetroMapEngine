package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javafx.scene.input.Clipboard;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.DataFormat;

public class TileBar {
    final int id;
    final String name;
    final Image image;
    public static DataFormat TILE_NAME = new DataFormat("retromapengine/rme-tile-name");
    public static TileBar selectedTile;
    
    public TileBar(int id, String name, Image image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Image getImage() {
        return image;
    }
    
    public TileBar copy() {
        return new TileBar(id, name, image);
    }
    
    public static TileBar[] loadFromAssets(Path assetsFolder) {
        List<TileBar> tiles = new ArrayList<>();
        int idCounter = 0;
        
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(assetsFolder, "*.png")) {
            for(Path path : stream) {
                String name = path.getFileName().toString();
                Image image = new Image(path.toUri().toString());
                
                tiles.add(new TileBar(idCounter++, name, image));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        return tiles.toArray(new TileBar[0]);
    }
    
    public static VBox populateTileBar(VBox tileBar, TileBar[] tiles) {
        for(TileBar tile : tiles) {
            VBox tileBox = new VBox();
            tileBox.getStyleClass().add("notSelected");
        
            ImageView tileImage = new ImageView(tile.getImage());
            tileImage.setFitWidth(48);
            tileImage.setFitHeight(48);
            tileImage.setPreserveRatio(true);
            
            tileBox.getChildren().add(tileImage);
            tileBox.setUserData(tile);
            
            tileBox.setOnMouseClicked(event -> {
                selectedTile = tile.copy();
                highlightSelected(tileBox);
            });
            
            tileBar.getChildren().add(tileBox);
        }
        return tileBar;
    }
    
    public static void highlightSelected(VBox selected) {
        Editor.tileBar.getChildren().forEach(tile -> {
            if(tile.getStyleClass().contains("selected")) {
                tile.getStyleClass().remove("selected");
                tile.getStyleClass().add("notSelected");
            }
            selected.getStyleClass().remove("notSelected");
            selected.getStyleClass().add("selected");
        });
        selected.getStyleClass().add("selected");
    }
}