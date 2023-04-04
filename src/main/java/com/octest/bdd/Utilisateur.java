package com.octest.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.octest.beans.User;

public class Utilisateur {
    private Connection connexion;
    public List<User> classement;
    public User compteConnect;
    
    public List<User> recupererUtilisateurs() {
        List<User> utilisateurs = new ArrayList<User>();
        
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        
        Statement statement = null;
        ResultSet resultat = null;

        //loadDatabase();
        
        try {
        	connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/SNAKE ", "root", "root");
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT * FROM Utilisateur;");

            // Récupération des données
            while (resultat.next()) {
                String identifiant = resultat.getString("IDENTIFIANT");
                int bestScore = resultat.getInt("BEST_SCORE");
                int lastScore = resultat.getInt("LAST_SCORE");
                
                User utilisateur = new User();
                utilisateur.setIDENTIFIANT(identifiant);
                utilisateur.setBEST_SCORE(bestScore);
                utilisateur.setLAST_SCORE(lastScore);
                
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        classement = utilisateurs;
        return utilisateurs;
    }
    
    public void ajouterUtilisateur(User utilisateur) {   	
        //loadDatabase();
    	try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
    	
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO Utilisateur VALUES(NULL,?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, utilisateur.getNOM());
            preparedStatement.setString(2, utilisateur.getPRENOM());
            preparedStatement.setString(3, utilisateur.getIDENTIFIANT());
            preparedStatement.setString(4, utilisateur.getMDP());
            preparedStatement.setDate(5, (java.sql.Date) new Date());
            preparedStatement.setDate(6, (java.sql.Date) new Date());
            preparedStatement.setInt(7, utilisateur.getBEST_SCORE());
            preparedStatement.setInt(8, utilisateur.getLAST_SCORE());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}