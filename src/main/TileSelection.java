package main;

import javafx.scene.layout.VBox;

public class TileSelection {
    private TileBar selectedTile;
    private int x;
    private int y;
    
    public TileSelection(TileBar tile, int x, int y) {
        this.tile = tile;
        this.x = x;
        this.y = y;
    }
}