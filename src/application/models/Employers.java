package application.models;

public class Employers {
	

	private String matricule;
	private String nom;
	private String prenom;
	private String login;
	private String ville;

	
	
	
	
	
	
	public Employers(String matricule, String nom, String prenom, String login, String ville) {
		
	
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
	
		this.ville = ville;
	
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









	public String getVille() {
		return ville;
	}






	public void setVille(String ville) {
		this.ville = ville;
	}






	
}
