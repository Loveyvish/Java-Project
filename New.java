//package application;

import java.awt.MenuBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javax.security.auth.Subject;

public class New extends Application {
    @FXML
    public TextField studentName;
    @FXML
    public TextField studentUSN;
    @FXML TextField complainttext;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Query Portal");
        ////Label collegeimg = new Label("");
        ////Image img= new Image("college.png");
        ////ImageView iv= new ImageView(img);
        Label studentlabel= new Label("Student Login");

        TextField tfusername= new TextField();
        tfusername.setPromptText("Enter Username");
        PasswordField pfpassword= new PasswordField();
        pfpassword.setPromptText("Enter password");
        tfusername.setText("");
        pfpassword.setText("");


//        button = new Button();
//        button.setText("Login");
        Label label1=new Label();
        Button login = new Button("Login");


        //scene2
        Label label2= new Label("\nWelcome\nFeel free to raise a query here");
        VBox layout2 = new VBox(5);

        TextField id= new TextField();
        id.setPromptText("Enter your Name");
        TextField sem = new TextField();
        sem.setPromptText("Enter sem");

        TextField subject = new TextField();
        subject.setPromptText("Enter subject");
        TextField complainttext= new TextField();
        ////Image img2= new Image("welcome.png");
        ////ImageView iv2= new ImageView(img2);
        complainttext.setPromptText("Enter your doubt:");
        Button submit = new Button("Submit");
        Button logout=new Button("Logout");
        Button trending = new Button("Trending");
        trending.setContentDisplay(ContentDisplay.TOP);
        Button help = new Button("Help");
        help.setContentDisplay(ContentDisplay.TOP);
        //
        Menu optionsmenu= new Menu("Options");
        optionsmenu.getItems().add(new MenuItem("Trending"));
        optionsmenu.getItems().add(new MenuItem("Help"));

        MenuBar mb = new MenuBar();
        //mb.getMenus().add(optionsmenu);

        Label printlab= new Label();
        layout2.getChildren().addAll(trending,logout,id,sem,subject,complainttext,submit,printlab);
        layout2.setAlignment(Pos.CENTER);
        Scene scene2= new Scene(layout2,550,500);
        ////scene2.getStylesheets().add("application.css");

        //This class will handle the button events
        button = new Button("Login");
        button.getStyleClass().add("button-blue");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String checkUser = tfusername.getText().toString();
                String checkPw = pfpassword.getText().toString();
                Connection con =null;
                Statement stmt= null;
                tfusername.setText("");
                pfpassword.setText("");
                label1.setText("Enter login credentials");

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

                    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Students?useSSL=false","root","");  //enter password
                    stmt = con.createStatement();
                    ResultSet rs= stmt.executeQuery("SELECT * FROM user where Name='"+checkUser+"'");

                    while(rs.next()) {
                        if(rs.getString(2).equals(checkPw)) {
                            label1.setText("Enter login credentials");

                            primaryStage.setScene(scene2);
//                            primaryStage.show();
                        }
                        else {
                            label1.setText("Incorrect user or pw.");

                        }
                    }
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        VBox layout = new VBox(10);
        Label labelintro = new Label("RVCE online query portal");
        layout.getChildren().addAll(labelintro,help,tfusername,pfpassword,button,label1);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,700,500);
        ////scene.getStylesheets().add("application.css");

        primaryStage.setScene(scene);
        primaryStage.show();


        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                Connection con =null;
                Statement stmt= null;
                String sname = id.getText().toString();
//                String stUSN = studentUSN.getText().toString();
                String doubt = complainttext.getText().toString();
                String semester = sem.getText().toString();
                String sub = subject.getText().toString();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

                    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Students?useSSL=false","root","");
                    stmt = con.createStatement();
//                    String sql = "INSERT INTO `users` (`Name`, `USN`) VALUES ('"+stName+"',"+stUSN+")";
                    String sql = "INSERT INTO `users` (`Name`,`Semester`,`Subject`,`Query`) VALUES ('"+sname+"','"+semester+"','"+sub+"','"+doubt+"') ";
                    printlab.setText("Complaint successfully registered");
                    stmt.executeUpdate(sql);
//                    stmt.executeUpdate(sql2);

                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

//        button = new Button("Submit");



        logout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });


//        help.setOnAction(e->AlertBox.display("Help Centre"));
//        trending.setOnAction(e->TrendingBox.display());
    }
}