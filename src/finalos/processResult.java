/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.util.Arrays;
import static java.util.Arrays.fill;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author alial
 */
public class processResult extends Application{
    
    static RRC r = new RRC();
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane grid1 = new GridPane();
        int [] BT = r.burst;
        int [] ff = new int[r.gantChart.length];
        for (int i = 1; i < ff.length; i++) {
            int j = BT[getIndex(r.pName,r.gantChart[i])] - Integer.parseInt(processInfo.time_slice);
            if (j>=0) {
                ff[i] = Integer.parseInt(processInfo.time_slice);
                BT[getIndex(r.pName,r.gantChart[i])] -= Integer.parseInt(processInfo.time_slice);
            } else {
                ff[i] = BT[getIndex(r.pName,r.gantChart[i])] ;
            }
        }
        int v = 0;
        Label [] gant = new Label[ff.length];
        Label [] numric = new Label[ff.length+1];
        for (int i = 1; i < gant.length; i++) {
            Label label = new Label(r.gantChart[i]);
            gant[i] = label;
            gant[i].setStyle("-fx-border-color:black; -fx-background-color:red;");
            gant[i].setAlignment(Pos.CENTER);
            int c =(ff[i]+1)*7;
            gant[i].setMinHeight(40);
            gant[i].setMinWidth(c);
            grid1.add(gant[i], i, 0,ff[i]+1,1);
            v += ff[i-1];
            Label label1 = new Label(Integer.toString(v));
            numric[i] = label1;
            numric[i].setMinWidth(c);
            grid1.add(numric[i], i, 1);
        }
        v += ff[gant.length-1];
        numric[gant.length] = new Label(Integer.toString(v));
        grid1.add(numric[gant.length], gant.length, 1);
        grid1.setHgap(2);
        ScrollPane Sct = new ScrollPane(grid1);
        root.setTop(Sct);
        Text footer = new Text("powred by @Alito");
        footer.setFont(Font.font("roman", 8));
        root.setBottom(footer);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));  
        
        
        
        Label L1 = new Label("Process Name");
        Label L2 = new Label("Process Time Arraival");
        Label L4 = new Label("Process Time Compilation");
        Label L5 = new Label("Process Wait Time");
        grid.add(L1, 0, 1);
        grid.add(L2, 1, 1);
        //grid.add(L3, 2, 1);
        grid.add(L4, 3, 1);
        grid.add(L5, 4, 1);
        int x = Integer.parseInt(processInfo.num_of_proc);
        TextField text[][] = new TextField[x][5];
        for(int i = 0;i<x;i++){
            TextField textField1 = new TextField();
            text[i][0] = textField1;
            TextField textField2 = new TextField();
            text[i][1] = textField2;
            //TextField textField3 = new TextField();
            //text[i][2] = textField3;
            TextField textField4 = new TextField();
            text[i][3] = textField4;
            TextField textField5 = new TextField();
            text[i][4] = textField5;
            grid.add(text[i][0], 0, i+2);
            grid.add(text[i][1], 1, i+2);
            //grid.add(text[i][2], 2, i+2);
            grid.add(text[i][3], 3, i+2);
            grid.add(text[i][4], 4, i+2);
        }
        for (int i = 0; i < x; i++) {
            text[i][0].setText(r.pName[i]);
            text[i][0].setDisable(true);
            text[i][1].setText(Integer.toString(r.aTime[i]));
            text[i][1].setDisable(true);
            //text[i][2].setText(Integer.toString(BT[i]));
            //text[i][2].setDisable(true);
            text[i][3].setText(Integer.toString(r.com[i]));
            text[i][3].setDisable(true);
            text[i][4].setText(Integer.toString(r.wTime[i]));
            text[i][4].setDisable(true);
        }
        Label L6 = new Label("Average waiting time is :");
        grid.add(L6, 0, x+2);
        TextField T6 = new TextField(Float.toString(r.avgwtime));
        grid.add(T6, 1, x+2);
        T6.setDisable(true);
        Label L7 = new Label("Average compilation  time is :");
        grid.add(L7, 0, x+3);
        TextField T7 = new TextField(Float.toString(r.avgctime));
        grid.add(T7, 1, x+3);
        T7.setDisable(true);
        Button btn = new Button("Finish");
        HBox hbtn = new HBox(btn);
        hbtn.setAlignment(Pos.CENTER);
        grid.add(hbtn, 0, x+4, 5, 1);
        btn.setOnAction((event) -> {
            primaryStage.close();
        });
        
        
        
       
        root.setCenter(grid);
        
        ScrollPane Sc = new ScrollPane(root);

        
        
        Scene scene = new Scene(Sc);
        primaryStage.setMaxWidth(900);
        primaryStage.setTitle("process Result");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public int getIndex (String [] arr ,String val){
        int x=-1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(val)){
                x = i;
                
                break;
            }
            
        }
 
        return x;
    }
    
}
