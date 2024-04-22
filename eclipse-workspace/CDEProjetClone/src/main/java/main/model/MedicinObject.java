package main.model;

import java.sql.Date;

public class MedicinObject {
	
    private int id;
    private String produit;
    private String dci;
    private String forme_dosage;
    private Date dlu;
    private int qtte;
    private String lot;
    private String classe_therapeutique;
    private int n_caisse;
    private String caisse;
    private int donation;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}
	public String getDci() {
		return dci;
	}
	public void setDci(String dci) {
		this.dci = dci;
	}
	public String getForme_dosage() {
		return forme_dosage;
	}
	public void setForme_dosage(String forme_dosage) {
		this.forme_dosage = forme_dosage;
	}
	public Date getDlu() {
		return dlu;
	}
	public void setDlu(Date date) {
		this.dlu = date;
	}
	public int getQtte() {
		return qtte;
	}
	public void setQtte(int qtte) {
		this.qtte = qtte;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getClasse_therapeutique() {
		return classe_therapeutique;
	}
	public void setClasse_therapeutique(String classe_therapeutique) {
		this.classe_therapeutique = classe_therapeutique;
	}
	public int getN_caisse() {
		return n_caisse;
	}
	public void setN_caisse(int n_caisse) {
		this.n_caisse = n_caisse;
	}
	public String getCaisse() {
		return caisse;
	}
	public void setCaisse(String caisse) {
		this.caisse = caisse;
	}
	public int getDonation() {
		return donation;
	}
	public void setDonation(int donation) {
		this.donation = donation;
	}
}
