/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed
 */
public class JavaFXApplication3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Text footer = new Text("powred by @Alito");
        footer.setFont(Font.font("roman", 8));
        root.setBottom(footer);

        GridPane gride = new GridPane();
        gride.setHgap(10);
        gride.setVgap(10);
        gride.setPadding(new Insets(25,25,25,25));
        Label header = new Label("Round Robin scheduler");
        root.setTop(header);

        Label timeslice_l = new Label("Enter your TimeSlice(in msec) :");
        gride.add(timeslice_l, 0, 1);

        TextField timeslice_s = new TextField("1");
        gride.add(timeslice_s, 1, 2);

        Label num_of_process_l = new Label("Enter the Number of Process :");
        gride.add(num_of_process_l, 0, 3);

        TextField num_of_process_s = new TextField("1");
        gride.add(num_of_process_s, 1, 4);

        Button btn = new Button("Go");
        HBox hbtn = new HBox(btn);
        hbtn.setAlignment(Pos.CENTER);
        gride.add(hbtn, 0, 5, 2, 1);

        btn.setOnAction((event) -> {
            if (isValidTime(num_of_process_s.getText())&&isValidTime(timeslice_s.getText())){
            processInfo x = new processInfo(num_of_process_s.getText(),timeslice_s.getText());
            Stage stage = new Stage();
            x.start(stage);
            stage.show();}
        });

        root.setRight(gride);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Round Robin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public boolean isValidTime(String s){
     String regex="[0-9]+";
      return s.matches(regex);//returns true if input and regex matches otherwise false;
 }
}
