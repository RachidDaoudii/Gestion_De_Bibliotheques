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

    public void setQntEmprunt(int qntEmprunt) {
        QntEmprunt = qntEmprunt;
    }

    public void setQntPerdus(int qntPerdus) {
        QntPerdus = qntPerdus;
    }

    public int getQntPerdus() {
        return QntPerdus;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "ISBN='" + ISBN + '\'' +
                ", Titre='" + Titre + '\'' +
                ", Auteur='" + Auteur + '\'' +
                ", Statut='" + Statut + '\'' +
                ", QntTotal=" + QntTotal +
                ", QntEmprunt=" + QntEmprunt +
                ", QntPerdus=" + QntPerdus +
                '}';
    }

    DatabaseConnection db = new DatabaseConnection();
    Scanner in = new Scanner(System.in);



    public void Ajouter_livre(){
        String nouveauIsbn;
        String nouveauTitre;
        String nouvelAuteur;
        String QntTotal;
        String QntEmprunt;
        String QntPerdus;

        do {
            System.out.println("Donner nouveau Isbn : ");
            nouveauIsbn = in.nextLine();
            setISBN(nouveauIsbn);
        } while (!Validation(nouveauIsbn));

        do {
            System.out.println("Donner nouveau Titre : ");
            nouveauTitre = in.nextLine();
            setTitre(nouveauTitre);
        } while (!Validation(nouveauTitre));

        do {
            System.out.println("Donner nouveau Auteur : ");
            nouvelAuteur = in.nextLine();
            setAuteur(nouvelAuteur);
        } while (!Validation(nouvelAuteur));


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

        String query = "INSERT INTO `livres`(`Isbn`, `Titre`, `Auteur`, `QntTotal`, `QntEmprunt`, `QntPerdus`) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1, getISBN() );
            statement.setString(2, getTitre());
            statement.setString(3, getAuteur());
            statement.setString(4, QntTotal);
            statement.setString(5, QntEmprunt);
            statement.setString(6, QntPerdus);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Livre ajouté avec succès !");
            } else {
                System.out.println("L'ajout du livre a échoué.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void LivresDisponible() {
        String query = "SELECT * FROM livres where Statut Like 'disponible'";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("|                                       Détails du livre disponible                                  |");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("| Isbn             |Titre             | Auteur     | Statut     | QntTotal | QntEmprunt | QntPerdus  |");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                String Isbn = resultSet.getString("Isbn");
                String Titre = resultSet.getString("Titre");
                String Auteur = resultSet.getString("Auteur");
                String Statut = resultSet.getString("Statut");
                int QntTotal = resultSet.getInt("QntTotal");
                int QntEmprunt = resultSet.getInt("QntEmprunt");
                int QntPerdus = resultSet.getInt("QntPerdus");
                System.out.println(String.format("| %-16s | %-16s | %-10s | %-10s | %-8d | %-10d | %-9d |",Isbn ,Titre, Auteur,Statut,QntTotal , QntEmprunt , QntPerdus));

            }
            System.out.println("-----------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LivresEmprunt() {
        String query = "SELECT * FROM livres where Statut Like 'emprunte'";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("|                                       Détails des livres emprunts                                  |");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("| Isbn             |Titre             | Auteur     | Statut     | QntTotal | QntEmprunt | QntPerdus  |");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                String Isbn = resultSet.getString("Isbn");
                String Titre = resultSet.getString("Titre");
                String Auteur = resultSet.getString("Auteur");
                String Statut = resultSet.getString("Statut");
                int QntTotal = resultSet.getInt("QntTotal");
                int QntEmprunt = resultSet.getInt("QntEmprunt");
                int QntPerdus = resultSet.getInt("QntPerdus");
                System.out.println(String.format("| %-16s | %-16s | %-10s | %-10s | %-8d | %-10d | %-9d |",Isbn ,Titre, Auteur,Statut,QntTotal , QntEmprunt , QntPerdus));

            }
            System.out.println("-----------------------------------------------------------------------------------------------------");
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
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("|                                               Détails du livre                                     |");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("| Isbn             |Titre             | Auteur     | Statut     | QntTotal | QntEmprunt | QntPerdus  |");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                String Isbn = resultSet.getString("Isbn");
                String Titre = resultSet.getString("Titre");
                String Auteur = resultSet.getString("Auteur");
                String Statut = resultSet.getString("Statut");
                int QntTotal = resultSet.getInt("QntTotal");
                int QntEmprunt = resultSet.getInt("QntEmprunt");
                int QntPerdus = resultSet.getInt("QntPerdus");
                System.out.println(String.format("| %-16s | %-16s | %-10s | %-10s | %-8d | %-10d | %-9d |",Isbn ,Titre, Auteur,Statut,QntTotal , QntEmprunt , QntPerdus));

            }
            System.out.println("-----------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Modifier_livre(String isbn){

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
            //e.printStackTrace();
            //System.out.println(e.getMessage());
            // Gérer les erreurs SQL ici
            System.err.println("Une erreur s'est produite lors de l'ajout du livre : " + e.getMessage());
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
        String isbn;

        do {
            System.out.println("Donner Isbn livre supprimer : ");
            isbn = in.nextLine();
        } while (!Validation(isbn));

        String query = "DELETE FROM `livres` WHERE Isbn = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1, isbn);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Livre supprimé avec succès !");
            } else {
                System.out.println("Aucun livre trouvé avec cet ISBN.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Emprunt_livre(){

    }
    public void Retourner_livre(){

    }

    public void Total_Livres(){
        String query = "SELECT count(*) FROM livres ";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)){
            ResultSet countResult = statement.executeQuery();
            if (countResult.next()) {
                int totalCount = countResult.getInt(1); // Assuming count(*) is the first column
                System.out.println("Total des livres : " + totalCount);
            } else {
                System.out.println("No records found.");
            }
        }catch (SQLException e){
            e.getMessage();
        }

    }

    public void Total_Livres_Emprunt(){
        String query = "SELECT count(*) FROM livres WHERE Statut LIKE 'emprunte'";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)){
            ResultSet countResult = statement.executeQuery();
            if (countResult.next()) {
                int totalCount = countResult.getInt(1); // Assuming count(*) is the first column
                System.out.println("Total des livres emprunts : " + totalCount);
            } else {
                System.out.println("No records found.");
            }
        }catch (SQLException e){
            e.getMessage();
        }

    }

    public void LivresPerdus(){

    }

    public boolean VerifierLivres(String isbn){
        String query = "SELECT Isbn from Livres where Isbn Like ? And Statut Like ?";
        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1,isbn);
            statement.setString(2,"disponible");
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    public void Rapprot(){
        String query = "SELECT Titre , QntTotal , QntEmprunt , QntPerdus FROM livres ";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)){
            //statement.setString(1,getTitre());
            ResultSet resultSet = statement.executeQuery();
            System.out.println("---------------------------------------------------------");
            System.out.println("|                       Statistique                     |");
            System.out.println("---------------------------------------------------------");
            System.out.println("| Titre            | QntTotal | QntEmprunt | QntPerdus  |");
            System.out.println("---------------------------------------------------------");

            while (resultSet.next()) {
                String Titre = resultSet.getString("Titre");
                int QntTotal = resultSet.getInt("QntTotal");
                int QntEmprunt = resultSet.getInt("QntEmprunt");
                int QntPerdus = resultSet.getInt("QntPerdus");
                System.out.println(String.format("| %-16s | %-8d | %-10d | %-9d |", Titre, QntTotal , QntEmprunt , QntPerdus));

            }
            System.out.println("---------------------------------------------------------");

        }catch (SQLException e){
            e.getMessage();
        }
    }

}
