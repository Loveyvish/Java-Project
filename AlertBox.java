package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlertBox {

    public static void display(String title) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        String message ="\n\nThis is an online Complaint registration portal.\nYour complaints will be responded within 24 hours\nFor any further quieries contact Xxxxxxxxxx";
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Back");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
       //// scene.getStylesheets().add("application.css");
        window.setScene(scene);
        window.showAndWait();
    }

}