/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import static java.awt.SystemColor.text;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author alial
 */
public class processInfo extends Application{
   static String num_of_proc = new String();
   static String time_slice = new String();

    public processInfo() {
    }
    public processInfo(String num_of_proc,String time_slice  ) {
    this.num_of_proc = num_of_proc;
    this.time_slice = time_slice;
    }

    public String getNum_of_proc() {
        return num_of_proc;
    }

    public String getTime_slice() {
        return time_slice;
    }
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Button btnback = new Button("Back");
        btnback.setOnAction((event) -> {
            primaryStage.close();
        });
        root.setLeft(btnback);
        Text footer = new Text("powred by @Alito");
        footer.setFont(Font.font("roman", 8));
        root.setBottom(footer);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));  
        
        Label L1 = new Label("Process Name");
        Label L2 = new Label("Process Time Arraival");
        Label L3 = new Label("Process Time Executable");
        grid.add(L1, 0, 0);
        grid.add(L2, 1, 0);
        grid.add(L3, 2, 0);
        int x = Integer.parseInt(getNum_of_proc());
        TextField text[][] = new TextField[x][3];
        for(int i = 0;i<x;i++){
            TextField textField1 = new TextField();
            text[i][0] = textField1;
            TextField textField2 = new TextField("0");
            text[i][1] = textField2;
            TextField textField3 = new TextField();
            text[i][2] = textField3;
            grid.add(text[i][0], 0, i+1);
            grid.add(text[i][1], 1, i+1);
            grid.add(text[i][2], 2, i+1);
        }
        
        Button btn = new Button("Go");
        HBox hbtn = new HBox(btn);
        hbtn.setAlignment(Pos.CENTER);
        grid.add(hbtn, 0, x+1, 3, 1);
        String [] p = new String[x];
        int [] a = new int[x];
        int [] b = new int[x];
        int n = Integer.parseInt(getTime_slice());
        btn.setOnAction((event) -> {
            Boolean flag = true;
            for (int i = 0; i < x; i++) {
                if(isValidName(text[i][0].getText())&&isValidTime(text[i][1].getText()) && isValidTime(text[i][2].getText())){
                
                }else {
                    flag = false;
                    break;
                }
            }
            if (flag){
            for (int i = 0; i < x; i++) {
                String str = text[i][0].getText();
                p[i] = str;
                int j = Integer.parseInt(text[i][1].getText());
                a[i] = j;
                int k = Integer.parseInt(text[i][2].getText());
                b[i] = k;
            }
            RRC R = new RRC();
            R.roundRobin(p, a, b, n);
            primaryStage.close();
            processResult l = new processResult();
            l.r = R ;
            Stage S = new Stage();
            l.start(S);
            S.show();}
        });
        ScrollPane Sc = new ScrollPane(grid);
                
        
        root.setCenter(Sc);
        
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("process Info");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static boolean isValidTime(String s){      
     String regex="[0-9]+";      
      return s.matches(regex);//returns true if input and regex matches otherwise false;
 }
    
    public static boolean isValidName(String s){      
     String regex="[A-Za-z\\s]+";      
      return s.matches(regex);//returns true if input and regex matches otherwise false;
 }

}
