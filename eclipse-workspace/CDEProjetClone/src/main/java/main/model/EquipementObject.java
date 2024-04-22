package main.model;

import java.util.Date;

public class EquipementObject {
    private int id;
    private String designation;
    private int quantite;
    private Date dlu;
    

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Date getDlu() {
		return dlu;
	}
	public void setDlu(Date dlu) {
		this.dlu = dlu;
	}  
}
