package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.database.Database;
import application.models.Employer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AuthController implements Initializable  {
	
	

	//input Inscription Employer//	
	@FXML
	TextField txtNom;
	@FXML
	TextField txtPrenom;
	@FXML
	TextField txtVille;
	@FXML
	TextField txtUsername;
	@FXML
	PasswordField txtMotDePass;
	@FXML
	PasswordField txtConfirmPass;
	//Button//
	@FXML
	Button btnInscription;
	//input Connexion Employer//
	@FXML
	TextField txtLogin;
	@FXML
	PasswordField txtPassword;
	//Button 
	@FXML
	Button btnConnexion;
	//Authentification Checking
	@FXML Label AuthChek;
	@FXML Label ConChek;
	
	@FXML ProgressIndicator loadEffect;
	
	Database database = new Database();
	Employer employer;
	
	ArrayList<Employer> listemployer = new ArrayList<Employer>();

	ResultSet result;
	
	String query;
	boolean res, admin = false;
	
	
	
	
	
	
	
	@FXML
	public void registration(ActionEvent mouseEvent)throws IOException,SQLException{
		
		try {
			if(txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtVille.getText().equals("") 
					|| txtUsername.getText().equals("") || txtMotDePass.getText().equals("") || txtConfirmPass.getText().equals(""))
			{
				AuthChek.setText("Veulliez Remplir Tous les Champs pour Crée un Compte ");
				AuthChek.setTextFill(Color.RED);
			}else
			{
				if(txtMotDePass.getText().equals(txtConfirmPass.getText()))
				{
					
					database.connexion();
					
					employer = new Employer(txtNom.getText(), txtPrenom.getText(), txtUsername.getText(), txtMotDePass.getText(), txtVille.getText(), admin);
					
					query = "insert into employers values (null,'"+employer.getMatricule()+"','"+employer.getNom()+"','"+employer.getPrenom()+"','"
					+employer.getLogin()+"','"+employer.getPassword()+"','"+employer.getVille()+"',"+employer.isAdmin()+")";
					
					res = database.insertOne(query);
					if(res == true) {
						loadEffect.setVisible(true);
						
						AuthChek.setText("Ajout Effectué avec Succes");
						AuthChek.setTextFill(Color.GREEN);
						loadEffect.setVisible(false);
					}	
					else {
						AuthChek.setText("Error!!!Ajout Failed");
						AuthChek.setTextFill(Color.RED);
					}
					
					
				}else
				{
					AuthChek.setText("Mot de Passe n'est pas Comfirmer");
					AuthChek.setTextFill(Color.RED);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
	}
	
	
	
	
	@FXML
	public void login(ActionEvent mouseEvent){
		
		try {
			database.connexion();
			
				//System.out.println("Connected");
				
				result = database.showLists("employers");
				
					while(result.next())
					{
//					admin.setId(result.getInt("id"));
//					admin.setUsername(result.getString("username"));
//					admin.setPassword(result.getString("password"));
						
						employer = new Employer(result.getString("nom"),result.getString("prenom"),result.getString("username"),result.getString("password"),result.getString("ville"),result.getBoolean("admin"));
						
						listemployer.add(employer);
					}
					
					
				if(txtLogin.getText().equals("") || txtPassword.getText().equals(""))
				{

					ConChek.setText("Veulliez Remplir les 2 Champs pour s'authentifier ");
					ConChek.setTextFill(Color.RED);
					
					
					
				}else
				{
				
					for(int i = 0; i < listemployer.size() ; i++)
					{
						if(listemployer.get(i).getLogin().equals(txtLogin.getText()))
								
						{
						
							if(listemployer.get(i).getPassword().equals(txtPassword.getText()))
							{
								
								if(listemployer.get(i).isAdmin() == true)
								{
									loadStage("../fxml/Dashboard.fxml", btnConnexion);
								}else
								{
									loadStage("../fxml/HomeEmployer.fxml", btnConnexion);
								}

							}else
							{
								ConChek.setText("Password Incorrect !!!");
								ConChek.setTextFill(Color.RED);
							}
						
						}else
						{
							ConChek.setText("Username Incorrect !!!");
							ConChek.setTextFill(Color.RED);
						}
					
					}
				}
					



			database.deConnexion();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}	

	
	}
	
	
    private void loadStage(String fxml, Button btn) throws IOException{
        
//      Parent root = FXMLLoader.load(getClass().getResource(fxml));
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root));
//      stage.getIcons().add(new Image("/home/icons/icon.png"));
//      stage.initModality(Modality.APPLICATION_MODAL);
//      stage.show();
	
//      System.out.println("test success");
	
      Parent root = FXMLLoader.load(getClass().getResource(fxml));
		Stage window = (Stage) btn.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
		window.setScene(scene);
      

}
	
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	

    }

}
