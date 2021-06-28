package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.lang.Class;

public class TrendingBox  {

    private static TableView<Person> table = new TableView<Person>();

    public static void display() {
        Stage st= new Stage();
        st.setTitle("Student Query Portal");
        st.setWidth(340);
        st.setHeight(500);
        Scene scene = new Scene(new Group());

        Connection con =null;
        Statement stmt= null;

        ObservableList<Person> data=FXCollections.observableArrayList(
                new Person("", ""));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/listdb","root","pswd");
            stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("SELECT * from listtable");
            while(rs.next()) {
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                data.add(new Person(
                        rs.getString(1),
                        rs.getString(2)
                ));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        final Label label = new Label("Query List");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstUSNCol = new TableColumn("USN");
        firstUSNCol.setMinWidth(50);
        firstUSNCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("USN"));

        TableColumn firstNameCol = new TableColumn("Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Complaint");
        lastNameCol.setMinWidth(200);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        table.setItems(data);
        table.getColumns().addAll(firstUSNCol, firstNameCol, lastNameCol);


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10,10,10));
        vbox.getChildren().addAll( label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        st.setScene(scene);
        st.show();
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;


        private Person(String fName, String lName) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);

        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }


    }
}
