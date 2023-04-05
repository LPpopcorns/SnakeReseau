package com.octest.dao;

import java.util.List;

import com.octest.beans.User;

public interface UtilisateurDao {
	User returnCompteConnect();
    void ajouter( User utilisateur );
    List<User> lister();
	User VerifCompte(String sId, String sMdp);
	List<User> RecupClassement();
	void jouerPartie(int nouveauScore, String identifiant);
	User modifProfil(String id, String newid, String nom, String prenom);
}