package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Employer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	@FXML
	TableView<Employer> employerList;
	
	@FXML TextField matriculeEmp;
	@FXML TextField nomEmp;
	@FXML TextField prenomEmp;
	@FXML TextField loginEmp;
	@FXML TextField villeEmp;
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	

    }
}
