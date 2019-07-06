
package studentinfosystem;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jasmine
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection Con;
    PreparedStatement Pre;
    ResultSet Result;
    
    @FXML
    private TextField username;
    
    @FXML
    private Label loginError;
    @FXML
    private PasswordField password;
    
    Stage stage1 = null;
    @FXML
    void LoginBtnPushed(ActionEvent event) throws SQLException, IOException {
      try{
            String sql = "select * from admin where username = ? and password = ?";
        Pre = Con.prepareStatement(sql);
        Pre.setString(1,username.getText());
        Pre.setString(2, password.getText());
        Result = Pre.executeQuery();
        if(Result.next())
        {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("/studentinfosystem/Main.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Information System");
        stage.show();

        }
        else
        {
            loginError.setVisible(true);
        }
      }
        catch(NullPointerException ex)
                {
                
                }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loginError.setVisible(false);
        DbConnection db = new DbConnection();
        try {
            Con = db.DbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
