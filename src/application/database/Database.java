package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Database {

	String  serverName = "jdbc:mysql://localhost:3306/gastion_formation";
	String	userName = "root";
	String	password = "";
	
	Connection myConnection;
	Statement stmt;
	ResultSet result;
	PreparedStatement prestmt;
	
	
	
	
	
	
	public Database() {}


	public void connexion()
	 {

		try {
				
				//1.Get a connection to database
				Class.forName("com.mysql.cj.jdbc.Driver");  
				myConnection = DriverManager.getConnection(serverName, userName, password);
				
		
				
			}catch(Exception exc) {
				exc.printStackTrace();
			}
	 }
	 
	 
	 public void deConnexion()
	 {
		 try {
			myConnection.close();
			
		} catch (SQLException exc) {
			
			exc.printStackTrace();
		}
	 }
	 
	 
	 public boolean insertOne(String query) throws Exception,SQLException
	 {
			boolean res = false;
		
					
					stmt = myConnection.createStatement();
					
					int statut = stmt.executeUpdate(query);
					
					if(statut == 1)
					{
						res = true;
					} 
			
				
			return res;
	 }
	 
	 public boolean updateOne(String query){
			
		 	boolean result = false;
		 	
			try{  
				
						stmt = myConnection.createStatement();
						
						int statut = stmt.executeUpdate(query);
						if(statut == 1)
						{
							result = true;
						} 
				           
			}catch(Exception e){ 
			
					System.out.println(e);
					
			} 
			
			return result;
			
		}
	
	 
		public boolean deleteOne(String query){
			
			boolean result = false;
			;  
			try {
				 
				stmt = myConnection.createStatement();
			    int  statut = stmt.executeUpdate(query);  
			    if(statut == 1)
			    {
			    	result = true;
			    }
			}catch(Exception e) {
				
				System.out.println(e.getMessage());
				
			}
			
		return result;
		}
	 
	public ResultSet showLists(String table_name){
			
		
			try{  
				
			
				 stmt = myConnection.createStatement();
			     result = stmt.executeQuery("select * from "+table_name);
			     
			    
			     
				//here sonoo is database name, root is username and password      
				}catch(Exception e){ 
					
					System.out.println(e.getMessage());
					
				} 	
			 return result;
		}

}