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
        return """
                -----------------------------------------------------------------------------------------------------
                |                                        Livre                                                      |
                -----------------------------------------------------------------------------------------------------
                | Isbn             |Titre             | Auteur     | Statut     | QntTotal | QntEmprunt | QntPerdus |
                """+String.format("| %-16s | %-16s | %-10s | %-10s | %-8d | %-10d | %-9d |\n",ISBN ,Titre, Auteur,Statut,getQntTotal() , QntEmprunt , QntPerdus)
                +"-----------------------------------------------------------------------------------------------------";
    }

    DatabaseConnection db = new DatabaseConnection();
    Scanner in = new Scanner(System.in);

    public void Ajouter_livre(){
        String nouveauIsbn;
        String nouveauTitre;
        String nouvelAuteur;
        String QntTotal;
        setStatut("disponible");
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
            QntTotal = String.valueOf(Integer.parseInt(QntTotal));
            setQntTotal(Integer.parseInt(QntTotal));
        } while (!Validation(QntTotal));



        String query = "INSERT INTO `livres`(`Isbn`, `Titre`, `Auteur`, `QntTotal`) VALUES (?,?,?,?)";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1, getISBN() );
            statement.setString(2, getTitre());
            statement.setString(3, getAuteur());
            statement.setString(4, QntTotal);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(toString());
            }

        } catch (SQLException e) {
            System.out.println("L'ajout du livre a échoué. L'ISBN existe déjà dans la base de données.");
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
                System.out.println(String.format("| %-16s | %-16s | %-10s | %-10s | %-8s | %-10d | %-9d |",Isbn ,Titre, Auteur,Statut,QntTotal , QntEmprunt , QntPerdus));

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


        System.out.println("Donner nouveau Titre : ");
        nouveauTitre = in.nextLine();
        if (!nouveauTitre.isEmpty()){
            setTitre(nouveauTitre);
        }




        System.out.println("Donner nouveau Auteur : ");
        nouvelAuteur = in.nextLine();
        if (!nouvelAuteur.isEmpty()){
            setAuteur(nouvelAuteur);
        }



        System.out.println("Veuillez saisir le nouveau statut :");
        System.out.println("Le statut peut être 'disponible' ou 'emprunte'");
        nouveauStatut = in.nextLine();
        if (nouveauStatut.equals("disponible") || nouveauStatut.equals("emprunte")){
            if (!nouveauStatut.isEmpty()) {
                setStatut(nouveauStatut);
            }

        }else {
            System.out.println("Statut invalide. Le statut doit être 'disponible' ou 'emprunte'.");
        }

        System.out.println("Donner nouveau QntTotal : ");
        QntTotal = in.nextLine();
        if (!QntTotal.isEmpty()){
            setQntTotal(Integer.parseInt(QntTotal));
        }


        System.out.println("Donner nouveau QntEmprunt : ");
        QntEmprunt = in.nextLine();
        if(!QntEmprunt.isEmpty()){
            setQntEmprunt(Integer.parseInt(QntEmprunt));
        }


        System.out.println("Donner nouveau QntPerdus : ");
        QntPerdus = in.nextLine();
        if (!QntPerdus.isEmpty()){
            setQntPerdus(Integer.parseInt(QntPerdus));
        }

        String query = "UPDATE livres SET Titre = ?, Auteur = ?, Statut = ?,QntTotal = ?,QntEmprunt = ?,QntPerdus = ? WHERE Isbn = ?";

        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {

        // Définir les paramètres de la requête
            statement.setString(1, getTitre());
            statement.setString(2, getAuteur());
            statement.setString(3, getStatut());
            statement.setInt(4, getQntTotal());
            statement.setInt(5, getQntEmprunt());
            statement.setInt(6, getQntPerdus());
            statement.setString(7, getISBN());

            int rowCount = statement.executeUpdate();

            if (rowCount > 0) {
                System.out.println(toString());
            } else {
                System.out.println("Aucun livre trouvé avec cet ISBN.");
            }

        } catch (SQLException e) {
            System.err.println("Une erreur s'est produite lors de l'ajout du livre : " + e.getMessage());
        }

    }

    public static  boolean  Validation(String input){
        return !input.isEmpty();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
            setISBN(isbn);
        } while (!Validation(isbn));

        String query = "DELETE FROM `livres` WHERE Isbn = ?";
        System.out.println("Confirmez-vous la suppression de ces informations ? (Oui/Non)");
        String confirmation = in.nextLine();

        if (confirmation.equalsIgnoreCase("Oui")) {
            try (PreparedStatement statement = db.getConnection().prepareStatement(query)){
                statement.setString(1, getISBN());
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
        else {
            System.out.println("La suppression a été annulée.");
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
        String query = "SELECT * from Livres where Isbn Like ?";
        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1,isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                setISBN(resultSet.getString("Isbn"));
                setTitre(resultSet.getString("Titre"));
                setAuteur(resultSet.getString("Auteur"));
                setStatut(resultSet.getString("Statut"));
                setQntTotal(resultSet.getInt("QntTotal"));
                setQntEmprunt(resultSet.getInt("QntEmprunt"));
                setQntPerdus(resultSet.getInt("QntPerdus"));
                return true;
            } else {
                return false; // No matching book found
            }

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
