package application.models;

public class Session {
	
	private String code;
	private String libelle;
	private String formation;
	
	
	
	
	
	public Session(String code, String libelle, String formation) {

		this.code = code;
		this.libelle = libelle;
		this.formation = formation;
	}





	public String getCode() {
		return code;
	}





	public void setCode(String code) {
		this.code = code;
	}





	public String getLibelle() {
		return libelle;
	}





	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}





	public String getFormation() {
		return formation;
	}





	public void setFormation(String formation) {
		this.formation = formation;
	}
	
	
	

}
