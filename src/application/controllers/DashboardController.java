package application.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
	
	//Formation input //
	@FXML private TableView<Formation> formationList;
	@FXML private TableColumn<Formation, String> codeFormation;
	@FXML private TableColumn<Formation, String> libelleFormation;
	@FXML private TableColumn<Formation, String> descFormation;
	
	@FXML TextField txtCodeFor;
	@FXML TextField txtLibelFor;
	@FXML TextField txtDescFor;
	
	ObservableList<Formation> listFormations = FXCollections.observableArrayList();
	
//	btnAjoutFor;
//	btnMdifiFor;
	
	Database database = new Database();
	Employers employer;
	Formation formation;
	Session session;
	String query;
	ResultSet result;
	
	//Changement des Paneaux chez l'Administrateur
	@FXML
	public void manipulation(ActionEvent mouseEvent)throws Exception{
		
		// Affichage Par D�faut
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
		
			 employer = new Employers(result.getString("matricule"), result.getString("nom"), result.getString("prenom"), result.getString("username"),
					 result.getString("ville"));
			 listEmp.add(employer);
		}
		
		matriculeEmp.setDisable(true);
		
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
				
				matriculeEmp.setText(employerList.getSelectionModel().getSelectedItem().getMatricule());
				nomEmp.setText(employerList.getSelectionModel().getSelectedItem().getNom());
				prenomEmp.setText(employerList.getSelectionModel().getSelectedItem().getPrenom());
				loginEmp.setText(employerList.getSelectionModel().getSelectedItem().getLogin());
				villeEmp.setText(employerList.getSelectionModel().getSelectedItem().getVille());
				
				
				
		
			}

		});
		
		database.deConnexion();

	}
	
	
	@FXML
	public void updateEmployer() throws SQLException
	{
		database.connexion();
		query = "UPDATE employers SET nom ='"+nomEmp.getText()+"', prenom ='"+prenomEmp.getText()+"', username='"+loginEmp.getText()+"', ville='"+villeEmp.getText()+"' "
				+ "where matricule='"+matriculeEmp.getText()+"'";
		boolean res = database.updateOne(query);
		
		if(res == true)
		{
			System.out.println("Update Succes");
		}else
		{
			System.out.println("Update Failed");
		}
		
		employer = new Employers(matriculeEmp.getText(), nomEmp.getText(), prenomEmp.getText(), loginEmp.getText(), villeEmp.getText());
		listEmp.set(idSelected, employer);
		
		viderChampsEmployer();
		
		database.deConnexion();
	}
	
	
	public void deleteEmployer()
	{
		
		 query = "DELETE FROM employers WHERE matricule='"+matriculeEmp.getText()+"'";
		 database.connexion();
		 boolean res = database.deleteOne(query);
			if(res == true)
			{
				System.out.println("Delete Succes");
			}else
			{
				System.out.println("Delete Failed");
			}
			
			listEmp.remove(idSelected);
			
			viderChampsEmployer();
			
			database.deConnexion();
	}
	
	
	public void viderChampsEmployer()
	{
		matriculeEmp.clear();
		nomEmp.clear();
		prenomEmp.clear();
		loginEmp.clear();
		villeEmp.clear();
	}
	
	//----------------------------- Manipulation Crud Of Formations --------------------------------//
	
		public void showFormation() throws SQLException {
				
				
				database.connexion();
				result = database.showLists("formation");
				while (result.next()) {
				
					formation = new Formation(result.getString("code"), result.getString("libelle"), result.getString("description"));
					listFormations.add(formation);
				}
				
			
				
				codeFormation.setCellValueFactory(new PropertyValueFactory<>("code"));
				libelleFormation.setCellValueFactory(new PropertyValueFactory<>("libelle"));
				descFormation.setCellValueFactory(new PropertyValueFactory<>("description"));
		
				
				
				formationList.setItems(listFormations);
				
				
				formationList.setOnMouseClicked(new EventHandler<MouseEvent>() {
		
					@Override
					public void handle(MouseEvent arg0) {
						
						idSelected = formationList.getSelectionModel().getSelectedIndex();
						
						System.out.println(idSelected);
						
						txtCodeFor.setText(formationList.getSelectionModel().getSelectedItem().getCode());
						txtLibelFor.setText(formationList.getSelectionModel().getSelectedItem().getLibelle());
						txtDescFor.setText(formationList.getSelectionModel().getSelectedItem().getDescription());
			
		
					}
		
				});
				
				database.deConnexion();
		
			}
	
		
		
		
		public void insertFormation() {
			
			try {
				if(txtCodeFor.getText().equals("") || txtLibelFor.getText().equals("") || txtDescFor.getText().equals(""))
				{
					
				}else
				{
					database.connexion();
					query = "insert into formation values ('"+txtCodeFor.getText()+"','"+txtLibelFor.getText()+"','"+txtDescFor.getText()+"')";
					boolean res = database.insertOne(query);
					if(res == true)
					{
						System.out.println("Ajout Succes");
					}else
					{
						System.out.println("Ajout Failed");
					}
					formation = new Formation(txtCodeFor.getText(), txtLibelFor.getText(), txtDescFor.getText());
					listFormations.add(formation);
					
					viderChampsFormation();
					
					database.deConnexion();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		public void updateFormation() throws SQLException
		{
			database.connexion();
			query = "UPDATE formation SET libelle ='"+txtLibelFor.getText()+"', description ='"+txtDescFor.getText()+"' where code='"+txtCodeFor.getText()+"'";
			boolean res = database.updateOne(query);
			
			if(res == true)
			{
				System.out.println("Update Succes");
			}else
			{
				System.out.println("Update Failed");
			}
			
			formation = new Formation(txtCodeFor.getText(), txtLibelFor.getText(), txtDescFor.getText());
			listFormations.set(idSelected, formation);
			
			viderChampsFormation();
			
			database.deConnexion();
		}
	
	
		
		public void deleteFormation()
		{
			
			 query = "DELETE FROM formation WHERE code='"+txtCodeFor.getText()+"'";
			 database.connexion();
			 boolean res = database.deleteOne(query);
				if(res == true)
				{
					System.out.println("Delete Succes");
				}else
				{
					System.out.println("Delete Failed");
				}
				
				listFormations.remove(idSelected);
				
				viderChampsFormation();

				database.deConnexion();
			
		}
		
		public void viderChampsFormation()
		{
			txtCodeFor.clear();
			txtLibelFor.clear();
			txtDescFor.clear();
		}
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	try {
    		
			showEmploye();
			showFormation();
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

    }
}