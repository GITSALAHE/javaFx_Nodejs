package application.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.database.Database;
import application.models.Formation;
import application.models.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.Desktop;
import java.io.IOException;


public class HomeController implements Initializable {
	
	
	
	
	
	@FXML Button RedirectFormation;
	@FXML Button RedirectSession;
	
	@FXML ComboBox<String> comboFormation;
	@FXML ComboBox<String> comboSession;
	
	
	@FXML Label libelForm;
	@FXML Label descForm;
	@FXML Label libelSession;
	
	@FXML
	private WebView mapsView;

	Database database = new Database();
	ResultSet result;
	
	Formation formation;
	Session session;
	ArrayList<Formation> formations = new ArrayList<Formation>();
	
	ObservableList<String> valueCombo1 = FXCollections.observableArrayList();
	ObservableList<String> valueCombo2 = FXCollections.observableArrayList();
	ArrayList<Session> sessions = new ArrayList<Session>();
	
	
	
	@FXML
	public void showAll()throws Exception, SQLException
	{
		
			database.connexion();
			result = database.showLists("formation");
			
			while(result.next())
			{
				formation = new Formation(result.getString("code"), result.getString("libelle"), result.getString("description"));
				
				formations.add(formation);
			}
			
			for(int i=0; i < formations.size(); i++)
			{
				valueCombo1.add(formations.get(i).getCode());
			}
			
			comboFormation.setItems(valueCombo1);
			comboFormation.getSelectionModel().selectFirst();
			
		
			comboFormation.valueProperty().addListener(new ChangeListener<String>() {
				
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		            System.out.println(ov);
		              System.out.println(t);

						comboSession.getItems().clear();
						valueCombo2.clear();
						sessions.clear();
		      		result = database.showLists("session where formation='"+t1+"'");
					try {
						while(result.next())
						{
							session = new Session(result.getString("code"), result.getString("libelle"), result.getString("formation"));
							
							sessions.add(session);
						}
					} catch (SQLException e) {
						
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					
					for(int i=0; i < sessions.size(); i++)
					{
						valueCombo2.add(sessions.get(i).getCode());
			
					}
					
					comboSession.setItems(valueCombo2);
					comboSession.getSelectionModel().selectFirst();
					
					for(int i=0; i < sessions.size(); i++)
					{
						if(comboSession.getSelectionModel().getSelectedItem().equals(sessions.get(i).getCode()))
						{
							libelSession.setText(sessions.get(i).getLibelle());
							
						}
						
					}
					
		          }    
		        
		      });
		
	}
	

	
	public void getSession() {
        String formCode = comboFormation.getValue();
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("http://localhost:3000/formation/"+formCode));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	try {
            showAll();
			WebEngine web  = mapsView.getEngine();
			web.setJavaScriptEnabled(true);
			web.load(getClass().getResource("../index.html").toString());
        }
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}