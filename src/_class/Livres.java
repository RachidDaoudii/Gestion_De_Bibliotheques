package _class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DatabaseConnection;

public class Livres extends DatabaseConnection {
    private int ISBN;
    private String Titre;
    private String Auteur;
    private String Statut;

    public Livres(int ISBN, String titre, String auteur, String statut) {
        this.ISBN = ISBN;
        Titre = titre;
        Auteur = auteur;
        Statut = statut;
    }

    public Livres(){

    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getAuteur() {
        return Auteur;
    }

    public void setAuteur(String auteur) {
        Auteur = auteur;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String statut) {
        Statut = statut;
    }

    public void retrieveUsers() {
        String query = "SELECT * FROM categories";
        DatabaseConnection db = new DatabaseConnection();
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("category_name");
                String email = resultSet.getString("created_at");
                System.out.println(username + " " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
