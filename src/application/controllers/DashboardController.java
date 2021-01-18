package application.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.database.Database;
import application.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DashboardController implements Initializable {

	//Switch Dashboard
	@FXML
	Button btnAcceuill;
	
	@FXML
	Button gerEmploye;
	
	@FXML
	Button gerFormation;
	
	@FXML
	Button gerSession;
		
	
	@FXML
	Pane panelAcceuill;
	@FXML
	Pane panelEmployer;
	@FXML 
	Pane panelFormation;
	@FXML 
	Pane panelSession;
	
	//Fxml:id : Gestion Employers
		//TableView
	@FXML private TableView<Employers> employerList;
	@FXML private TableColumn<Employers, String> matricueCell;
	@FXML private TableColumn<Employers, String> nomCell;
	@FXML private TableColumn<Employers, String> prenomCell;
	@FXML private TableColumn<Employers, String> loginCell;
	@FXML private TableColumn<Employers, String> villeCell;
	
	ObservableList<Employers> listEmp = FXCollections.observableArrayList();
	
	@FXML TextField matriculeEmp;
	@FXML TextField nomEmp;
	@FXML TextField prenomEmp;
	@FXML TextField loginEmp;
	@FXML TextField villeEmp;
	
	@FXML Button btnUpdEmp;
	@FXML Button btnSuppEmp;
	@FXML Button btnVideEmp;
	
	
	int idSelected;
	
	Database database = new Database();
	Employers employer;
	Formation formations;
	Session sessions;
	
	ResultSet result;
	
	//Changement des Paneaux chez l'Administrateur
	@FXML
	public void manipulation(ActionEvent mouseEvent)throws Exception{
		
		// Affichage Par Défaut
		panelAcceuill.setVisible(true);
		panelEmployer.setVisible(false);
		panelFormation.setVisible(false);
		panelSession.setVisible(false);
		
		if(mouseEvent.getSource() == btnAcceuill)
		{
			panelAcceuill.setVisible(true);
			panelEmployer.setVisible(false);
			panelFormation.setVisible(false);
			panelSession.setVisible(false);
			
		}else if(mouseEvent.getSource() == gerEmploye)
		{
			panelAcceuill.setVisible(false);
			panelEmployer.setVisible(true);
			panelFormation.setVisible(false);
			panelSession.setVisible(false);
			
		}else if(mouseEvent.getSource() == gerFormation)	
		{
			panelAcceuill.setVisible(false);
			panelEmployer.setVisible(false);
			panelFormation.setVisible(true);
			panelSession.setVisible(false);
			
		}else if(mouseEvent.getSource() == gerSession)
		{
			panelAcceuill.setVisible(false);
			panelEmployer.setVisible(false);
			panelFormation.setVisible(false);
			panelSession.setVisible(true);
		}
	}
	
	//----------------------------- Manipulation Crud Of Employers --------------------------------//
	
	public void showEmploye() throws SQLException {
		
		
		database.connexion();
		result = database.showLists("employers");
		while (result.next()) {
		
			 employer = new Employers(result.getInt("id"), result.getString("matricule"), result.getString("nom"), result.getString("prenom"), result.getString("username"),
					 result.getString("password"), result.getString("ville"), result.getBoolean("admin"));
			 listEmp.add(employer);
		}
		
		
		matricueCell.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		nomCell.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCell.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		loginCell.setCellValueFactory(new PropertyValueFactory<>("login"));
		villeCell.setCellValueFactory(new PropertyValueFactory<>("ville"));
		
		
		employerList.setItems(listEmp);
		
		
		employerList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				idSelected = employerList.getSelectionModel().getSelectedIndex();
				System.out.println(idSelected);
				int idE = employerList.getSelectionModel().getSelectedItem().getId();
				matriculeEmp.setText(employerList.getSelectionModel().getSelectedItem().getMatricule());
				nomEmp.setText(employerList.getSelectionModel().getSelectedItem().getNom());
				prenomEmp.setText(employerList.getSelectionModel().getSelectedItem().getPrenom());
				loginEmp.setText(employerList.getSelectionModel().getSelectedItem().getLogin());
				villeEmp.setText(employerList.getSelectionModel().getSelectedItem().getVille());
				
				
				
		
			}

		});
		
		database.deConnexion();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	try {
			showEmploye();
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

    }
}
