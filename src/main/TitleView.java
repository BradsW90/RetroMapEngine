package main;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;



public class TitleView {

    public static void titleView(Stage stage) {
        //BACKGROUND
        Image background = new Image(TitleView.class.getResourceAsStream("/res/Title_Screen_BG.jpg"));
        ImageView bgView = new ImageView(background);
        bgView.fitWidthProperty().bind(stage.widthProperty());
        bgView.fitHeightProperty().bind(stage.heightProperty());
        bgView.setPreserveRatio(false);
        
        
        //BANNER AND BUTTONS
        
        //BANNER
        HBox banner = new HBox();
        Image bannerImage = new Image(TitleView.class.getResource("/res/RME_Banner.png").toExternalForm());
        ImageView bannerView = new ImageView(bannerImage);
        bannerView.fitWidthProperty().bind(stage.widthProperty().divide(2));
        bannerView.fitHeightProperty().bind(stage.heightProperty().divide(2));
        bannerView.setPreserveRatio(false);
        banner.getChildren().add(bannerView);
        
        //NEW MAP BUTTON
        ImageView newBtnImg = new ImageView(new Image(TitleView.class.getResourceAsStream("/res/NewGameBtn.png")));
        newBtnImg.setFitWidth(175);
        newBtnImg.setFitHeight(35);
        Rectangle newBtnOverlay = new Rectangle(175, 35);
        newBtnOverlay.setFill(Color.rgb(0,0,0,0.5));
        Label newBtnTxt = new Label("New Map");
        newBtnTxt.setTextFill(Color.WHITE);
        newBtnTxt.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        StackPane newBtn = new StackPane(newBtnImg, newBtnOverlay, newBtnTxt);
        newBtn.setOnMouseClicked(e -> {
            Editor.editorView(stage);
        });
        
        //LOAD MAP BUTTON
        Button loadButton = new Button("Load Map");
        loadButton.setPrefWidth(175);
        loadButton.setPrefHeight(35);
        
        //EXIT BUTTON
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(175);
        exitButton.setPrefHeight(35);
        exitButton.setOnAction(e -> Platform.exit());
        
        //CONTAINER
        VBox container = new VBox();
        container.getChildren().add(banner);
        container.getChildren().add(newBtn);
        container.getChildren().add(loadButton);
        container.getChildren().add(exitButton);
        container.setAlignment(Pos.CENTER);
        container.setMaxWidth(Region.USE_PREF_SIZE);
        container.setSpacing(7);
        
        //BASE LAYOUT
        StackPane baseLayout = new StackPane(bgView, container);
        baseLayout.setAlignment(container, Pos.CENTER);
        
        
        //SCENE SETTINGS
        Scene titleScreen = new Scene(baseLayout, 900, 600);
        titleScreen.getStylesheets().add(TitleView.class.getResource("application.css").toExternalForm());
        
        //FINAL
        stage.setTitle("RetroMapEngine");
        stage.setScene(titleScreen);
        stage.show();        
    }
}