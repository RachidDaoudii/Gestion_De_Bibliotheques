package _class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DatabaseConnection;
import java.util.*;
public class Livre {
    private String ISBN;
    private String Titre;
    private String Auteur;
    private String Statut;
    private int QntTotal;
    private int QntEmprunt;
    private int QntPerdus;

    public Livre(String ISBN, String titre, String auteur, String statut, int qntTotal, int qntEmprunt , int qntPerdus) {
        this.ISBN = ISBN;
        Titre = titre;
        Auteur = auteur;
        Statut = statut;
        QntTotal = qntTotal;
        QntEmprunt = qntEmprunt;
        QntPerdus = qntPerdus;
    }

    public Livre(){

    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
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

    public int getQntTotal() {
        return QntTotal;
    }

    public int getQntEmprunt() {
        return QntEmprunt;
    }

    public void setQntTotal(int qntTotal) {
        QntTotal = qntTotal;
    }

    public int getQntPerdus() {
        return QntPerdus;
    }

    DatabaseConnection db = new DatabaseConnection();

    public void Afficher_livre_disponible() {
        String query = "SELECT * FROM livres where Statut Like 'disponible'";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String Isbn = resultSet.getString("Isbn");
                String Titre = resultSet.getString("Titre");
                String Auteur = resultSet.getString("Auteur");
                String Statut = resultSet.getString("Statut");
                String QntTotal = resultSet.getString("QntTotal");
                String QntEmprunt = resultSet.getString("QntEmprunt");
                String QntPerdus = resultSet.getString("QntPerdus");
                System.out.println("Détails du livre disponible:");
                System.out.println("Isbn: " + Isbn);
                System.out.println("Titre: " + Titre);
                System.out.println("Auteur: " + Auteur);
                System.out.println("Statut: " + Statut);
                System.out.println("QntTotal: " + QntTotal);
                System.out.println("QntEmprunt: " + QntEmprunt);
                System.out.println("QntPerdus: " + QntPerdus);
                System.out.println("\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Afficher_livre_empruntes() {
        String query = "SELECT * FROM livres where Statut Like 'emprunte'";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String Isbn = resultSet.getString("Isbn");
                String Titre = resultSet.getString("Titre");
                String Auteur = resultSet.getString("Auteur");
                String Statut = resultSet.getString("Statut");
                String QntTotal = resultSet.getString("QntTotal");
                String QntEmprunt = resultSet.getString("QntEmprunt");
                String QntPerdus = resultSet.getString("QntPerdus");
                System.out.println("Détails du livre emprunte:");
                System.out.println("Isbn: " + Isbn);
                System.out.println("Titre: " + Titre);
                System.out.println("Auteur: " + Auteur);
                System.out.println("Statut: " + Statut);
                System.out.println("QntTotal: " + QntTotal);
                System.out.println("QntEmprunt: " + QntEmprunt);
                System.out.println("QntPerdus: " + QntPerdus);
                System.out.println("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Rechercher_livre(String titre , String auteur) {
        String query = "SELECT * FROM livres WHERE Titre LIKE ? OR Auteur LIKE ?";

        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.setString(1, titre );
            statement.setString(2, auteur);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String Isbn = resultSet.getString("Isbn");
                String Titre = resultSet.getString("Titre");
                String Auteur = resultSet.getString("Auteur");
                String Statut = resultSet.getString("Statut");
                String QntTotal = resultSet.getString("QntTotal");
                String QntEmprunt = resultSet.getString("QntEmprunt");
                String QntPerdus = resultSet.getString("QntPerdus");
                System.out.println("Détails du livre :");
                System.out.println("Isbn: " + Isbn);
                System.out.println("Titre: " + Titre);
                System.out.println("Auteur: " + Auteur);
                System.out.println("Statut: " + Statut);
                System.out.println("QntTotal: " + QntTotal);
                System.out.println("QntEmprunt: " + QntEmprunt);
                System.out.println("QntPerdus: " + QntPerdus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Modifier_livre(String isbn){


        Scanner in = new Scanner(System.in);

        String nouveauTitre;
        String nouvelAuteur;
        String nouveauStatut;
        String QntTotal;
        String QntEmprunt;
        String QntPerdus;

        do {

        } while (!Validation(isbn));

        do {
            System.out.println("Donner nouveau Titre : ");
            nouveauTitre = in.nextLine();
        } while (!Validation(nouveauTitre));

        do {
            System.out.println("Donner nouveau Auteur : ");
            nouvelAuteur = in.nextLine();
        } while (!Validation(nouvelAuteur));

        do {
            System.out.println("Donner nouveau Statut : ");
            nouveauStatut = in.nextLine();
        } while (!Validation(nouveauStatut));

        do {
            System.out.println("Donner nouveau QntTotal : ");
            QntTotal = in.nextLine();
        } while (!Validation(QntTotal));

        do {
            System.out.println("Donner nouveau QntEmprunt : ");
            QntEmprunt = in.nextLine();
        } while (!Validation(QntEmprunt));

        do {
            System.out.println("Donner nouveau QntPerdus : ");
            QntPerdus = in.nextLine();
        } while (!Validation(QntPerdus));

        String query = "UPDATE livres SET Titre = ?, Auteur = ?, Statut = ?,QntTotal = ?,QntEmprunt = ?,QntPerdus = ? WHERE Isbn = ?";



        DatabaseConnection db = new DatabaseConnection();
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {

        // Définir les paramètres de la requête
            statement.setString(1, nouveauTitre);
            statement.setString(2, nouvelAuteur);
            statement.setString(3, nouveauStatut);
            statement.setString(4, QntTotal);
            statement.setString(5, QntEmprunt);
            statement.setString(6, QntPerdus);
            statement.setString(7, isbn);


            int rowCount = statement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Livre modifié avec succès.");
            } else {
                System.out.println("Aucun livre trouvé avec cet ISBN.");
            }

            // N'oubliez pas de fermer la connexion et la déclaration lorsque vous avez terminé
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs SQL ici
        }

    }

    public static  boolean  Validation(String input){
        return !input.isEmpty();
    }

    public boolean get_All_Isbn_Livres(){
        boolean result = false;
        String query = "SELECT * FROM livres ";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String Isbn = resultSet.getString("Isbn");
                if (Isbn.equals(getISBN())) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void Supprimer_livre(){

    }

    public void Emprunt_livre(){

    }
    public void Retourner_livre(){

    }

    public void LivresTotal(){

    }

    public void LivresDisponible(){

    }

    public void LivresEmprunt(){

    }

    public void LivresPerdus(){

    }

}
