package com.octest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.User;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DaoFactory daoFactory;
    private User userConnect;

    UtilisateurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(User utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Utilisateur (NOM, PRENOM, IDENTIFIANT, MDP, DATE_CREATION, DATE_MODIF, BEST_SCORE, LAST_SCORE) VALUES(?, ?, ?, ?, '2023-03-15', '2023-03-15', ?, ?);");
            preparedStatement.setString(1, utilisateur.getNOM());
            preparedStatement.setString(2, utilisateur.getPRENOM());
            preparedStatement.setString(3, utilisateur.getIDENTIFIANT());
            preparedStatement.setString(4, utilisateur.getMDP());
            preparedStatement.setInt(5, utilisateur.getBEST_SCORE());
            preparedStatement.setInt(6, utilisateur.getLAST_SCORE());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> lister() {
        List<User> utilisateurs = new ArrayList<User>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT IDENTIFIANT, MDP FROM Utilisateur;");

            while (resultat.next()) {
                String id = resultat.getString("IDENTIFIANT");
                String mdp = resultat.getString("MDP");

                User utilisateur = new User();
                utilisateur.setIDENTIFIANT(id);
                utilisateur.setMDP(mdp);

                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    
  
    @Override
    public User VerifCompte(String sId, String sMdp) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            connexion = daoFactory.getConnection();
            System.out.println("connection");
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE MDP=? AND IDENTIFIANT=?");
            System.out.println("requete");
            preparedStatement.setString(1, sMdp);
            preparedStatement.setString(2, sId);
            System.out.println("setString");
            ResultSet resultat = preparedStatement.executeQuery();
            System.out.println("execute Requete");
            while (resultat.next()) {
            	System.out.println("lecture Requete");
                user.IDENTIFIANT = resultat.getString("IDENTIFIANT");
                user.MDP = resultat.getString("MDP");
                user.PRENOM = resultat.getString("PRENOM");
                user.NOM = resultat.getString("NOM");
                user.DATE_CREATION = java.time.LocalDate.now();
                user.DATE_MODIF = java.time.LocalDate.now();
                user.BEST_SCORE = resultat.getInt("BEST_SCORE");
                user.LAST_SCORE = resultat.getInt("LAST_SCORE");
            }
            System.out.println("fin lecture Requete");
            System.out.println(user.IDENTIFIANT);
            System.out.println(user.MDP);
            userConnect = user;
            System.out.println("return = "+userConnect.IDENTIFIANT);
            return user;
        } 
        
        catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }

    }
    

	@Override
	public User returnCompteConnect() {
		return userConnect;
	}
	
	@Override
	public List<User> RecupClassement(){
		List<User> utilisateurs = new ArrayList<User>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Utilisateur ORDER BY BEST_SCORE DESC;");

            while (resultat.next()) {
                String id = resultat.getString("IDENTIFIANT");
                String nom = resultat.getString("NOM");
                String prenom = resultat.getString("PRENOM");
                int score = resultat.getInt("BEST_SCORE");

                User utilisateur = new User();
                utilisateur.setIDENTIFIANT(id);
                utilisateur.setNOM(nom);
                utilisateur.setPRENOM(prenom);
                utilisateur.setBEST_SCORE(score);

                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;		
	}
	
	@Override
	public void jouerPartie(int nouveauScore, String identifiant) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            System.out.println("connection");
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE IDENTIFIANT=?");
            System.out.println("requete");
            preparedStatement.setInt(1, nouveauScore);
            ResultSet resultat = preparedStatement.executeQuery();
            System.out.println("execute Requete");
            while (resultat.next()) {
            	System.out.println("lecture Requete");
                int bestScore = resultat.getInt("BEST_SCORE");
                if(bestScore < nouveauScore) {
                    preparedStatement = connexion.prepareStatement("UPDATE Utilisateur SET LAST_SCORE=?, BEST_SCORE=?, WHERE IDENTIFIANT=?");
                    System.out.println("requete");
                    preparedStatement.setInt(1, nouveauScore);
                    preparedStatement.setInt(2, nouveauScore);
                    preparedStatement.setString(3, identifiant);
                    ResultSet res = preparedStatement.executeQuery();
                }
                else {
                    preparedStatement = connexion.prepareStatement("UPDATE Utilisateur SET LAST_SCORE=? WHERE IDENTIFIANT=?");
                    System.out.println("requete");
                    preparedStatement.setInt(1, nouveauScore);
                    preparedStatement.setString(2, identifiant);
                    ResultSet res = preparedStatement.executeQuery();
                }
            }
        }     
        catch (SQLException e) {
            System.out.println("error Update Score");
            e.printStackTrace();
        }
	}
	

}