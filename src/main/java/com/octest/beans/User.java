package com.octest.beans;

import java.time.LocalDate;
import java.util.Date;

public class User {

	public String NOM;
	public String PRENOM;
	public String IDENTIFIANT;
	public String MDP;
	public LocalDate DATE_CREATION;
	public LocalDate DATE_MODIF;
	public int BEST_SCORE;
	public int LAST_SCORE;
	
	public String getNOM() {
		return NOM;
	}
	public void setNOM(String nOM) {
		NOM = nOM;
	}
	public String getPRENOM() {
		return PRENOM;
	}
	public void setPRENOM(String pRENOM) {
		PRENOM = pRENOM;
	}
	public String getIDENTIFIANT() {
		return IDENTIFIANT;
	}
	public void setIDENTIFIANT(String iDENTIFIANT) {
		IDENTIFIANT = iDENTIFIANT;
	}
	public String getMDP() {
		return MDP;
	}
	public void setMDP(String mDP) {
		MDP = mDP;
	}
	public LocalDate getDATE_CREATION() {
		return DATE_CREATION;
	}
	public void setDATE_CREATION(LocalDate dATE_CREATION) {
		DATE_CREATION = dATE_CREATION;
	}
	public LocalDate getDATE_MODIF() {
		return DATE_MODIF;
	}
	public void setDATE_MODIF(LocalDate dATE_MODIF) {
		DATE_MODIF = dATE_MODIF;
	}
	public int getBEST_SCORE() {
		return BEST_SCORE;
	}
	public void setBEST_SCORE(int bEST_SCORE) {
		BEST_SCORE = bEST_SCORE;
	}
	public int getLAST_SCORE() {
		return LAST_SCORE;
	}
	public void setLAST_SCORE(int lAST_SCORE) {
		LAST_SCORE = lAST_SCORE;
	}

	
	
}
