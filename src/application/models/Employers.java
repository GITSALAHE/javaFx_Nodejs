package application.models;

public class Employers {
	
	private int id;
	private String matricule;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private String ville;
	private boolean admin;
	
	
	
	
	
	
	public Employers(int id, String matricule, String nom, String prenom, String login, String password, String ville, boolean d) {
		
		this.id = id;
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.ville = ville;
		this.admin = d;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getMatricule() {
		return matricule;
	}






	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}






	public String getNom() {
		return nom;
	}






	public void setNom(String nom) {
		this.nom = nom;
	}






	public String getPrenom() {
		return prenom;
	}






	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}






	public String getLogin() {
		return login;
	}






	public void setLogin(String login) {
		this.login = login;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	public String getVille() {
		return ville;
	}






	public void setVille(String ville) {
		this.ville = ville;
	}






	public boolean isAdmin() {
		return admin;
	}






	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	

	
}
