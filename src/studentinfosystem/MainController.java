/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfosystem;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jasmine
 */
public class MainController implements Initializable {

    Connection Con;
    PreparedStatement Pre;
    ResultSet Result;
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addPane;

    @FXML
    private AnchorPane updatePane;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private Label updateRoll;

    @FXML
    private TextField updateName;

    @FXML
    private TextField updateFaculty;

    @FXML
    private TextField updateAddress;

    @FXML
    private TextField updateEmail;

    @FXML
    private Label updateRollError;

    @FXML
    private Label updateNameError;

    @FXML
    private Label updateAddressError;

    @FXML
    private Label updatePhoneError;

    @FXML
    private TextField updatePhone;

    @FXML
    private TextField updateBrowse;

    @FXML
    private TextField RollNo;

    @FXML
    private TextField Name;

    @FXML
    private TextField Faculty;

    @FXML
    private TextField Address;

    @FXML
    private TextField Email;

    @FXML
    private TextField Phone;

    @FXML
    private Label rollnoError;

    @FXML
    private Label nameError;

    @FXML
    private Label addressError;

    @FXML
    private Label numberError;

    @FXML
    private TextField browse;

    @FXML
    private Label headingName;

    @FXML
    private Label profileRollNo;

    @FXML
    private Label profileName;

    @FXML
    private Label profileFaculty;

    @FXML
    private Label profileAddress;

    @FXML
    private Label profileEmail;
    @FXML
    private Label profilePhone;

    @FXML
    private TextField searchTextField;

    @FXML
    private JFXButton updateBtn;

    void searchFunction(String roll) throws SQLException {
        String sql = "select * from student_info where rollno=?";
        Pre = Con.prepareStatement(sql);

        Pre.setString(1, roll);

        Result = Pre.executeQuery();
        if (Result.next()) {
            headingName.setText(Result.getString("name"));
            profileRollNo.setText(Result.getString("rollno"));
            profileName.setText(Result.getString("name"));
            profileFaculty.setText(Result.getString("faculty"));
            profileAddress.setText(Result.getString("address"));
            profilePhone.setText(Result.getString("phone"));
            profileEmail.setText(Result.getString("email"));
            updateRoll.setText(Result.getString("rollno"));
            updateName.setText(Result.getString("name"));
            updateFaculty.setText(Result.getString("faculty"));
            updateAddress.setText(Result.getString("address"));
            updatePhone.setText(Result.getString("phone"));
            updateEmail.setText(Result.getString("email"));
            searchTextField.setText(null);
            profilePane.toFront();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error alert");
            alert.setHeaderText("Cannot find information");
            alert.setContentText("Roll Number : "+searchTextField.getText()+" student does not exists!");

            alert.showAndWait();
        }
    }

    @FXML
    void searchAction(ActionEvent event) throws SQLException {
        searchFunction(searchTextField.getText());
    }

    @FXML
    void AddBtnPushed() {
        addPane.toFront();
    }

    @FXML
    void DeleteBtnPushed() throws SQLException {
        String sql = "delete from student_info where rollno =?";
        Pre = Con.prepareStatement(sql);
        Pre.setString(1, profileRollNo.getText());
        Pre.executeUpdate();
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Deleted the user");
        dialog.setContentText("Student info has been deleted");
        dialog.show();
        addPane.toFront();
        
    }

    @FXML
    void EditBtnPushed() {
        updatePane.toFront();
    }

    @FXML
    void UpdateCancelBtnPushed() {
        profilePane.toFront();
    }

    @FXML
    void RegisterBtnPushed() throws SQLException {
        
        if (RollNo.getText().trim().equals("") || Name.getText().trim().equals("")
                || Address.getText().trim().equals("") || Phone.getText().trim().equals("")
                || Faculty.getText().trim().equals("")) {

        } else {
            try {
                String sql = "insert into student_info values(?,?,?,?,?,?)";
                Pre = Con.prepareStatement(sql);
                Pre.setString(1, RollNo.getText());
                Pre.setString(2, Name.getText());
                Pre.setString(3, Address.getText());
                Pre.setString(4, Phone.getText());
                Pre.setString(5, Email.getText());
                Pre.setString(6, Faculty.getText());
                Pre.executeUpdate();
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Sucessfull Registration");
                dialog.setContentText("New student has been registered");
                dialog.show();
                RollNo.setText(null);
                Name.setText(null);
                Address.setText(null);
                Phone.setText(null);
                Email.setText(null);
                Faculty.setText(null);
            } catch (SQLException | NullPointerException ex) {

            }
        }
    }

    @FXML
    void UpdateBrowseBtnPushed(ActionEvent event) {

    }

    @FXML
    void UpdateBtnPushed() throws SQLException {
    if(updateName.getText().trim().equals("")
                || updateAddress.getText().trim().equals("") || updatePhone.getText().trim().equals("")
                || updateFaculty.getText().trim().equals(""))
    {
        
    }
    else{
        try{
                String sql = "update student_info set name = ? , address = ?, phone = ? , faculty = ?, email =? "
                + "where rollno = ?";
        Pre = Con.prepareStatement(sql);
        Pre.setString(1, updateName.getText());
        Pre.setString(2, updateAddress.getText());
        Pre.setString(3, updatePhone.getText());
        Pre.setString(4, updateFaculty.getText());
        Pre.setString(5, updateEmail.getText());
        Pre.setString(6, updateRoll.getText());
        Pre.executeUpdate();
        searchFunction(updateRoll.getText());
        profilePane.toFront();
        }
        catch(SQLException ex)
        {
            
        }
    }

    }

    @FXML
    void registerBrowserBtnPushed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
              DbConnection db = new DbConnection();
        try {
            Con = db.DbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
