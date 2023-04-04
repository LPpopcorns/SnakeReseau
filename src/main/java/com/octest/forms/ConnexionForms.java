package com.octest.forms;

import javax.servlet.http.HttpServletRequest;

public class ConnexionForms {

	private String resultat;
	
	public void verifierIdentifiants(HttpServletRequest request) {
		String id = request.getParameter("id");
		String mdp = request.getParameter("mdp");
		if(mdp==null) {mdp = "";}
		if(id==null) {id = "";}
		
		if(mdp.equals(id)) {
			setResultat("Connexion Réussi");
		}
		else {
			setResultat("Connexion Refusé");
		}
	}
	
	
	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
	
}
