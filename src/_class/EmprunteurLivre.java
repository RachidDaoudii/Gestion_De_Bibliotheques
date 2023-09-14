package _class;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DatabaseConnection;

public class EmprunteurLivre {
    private List<Livre> livres;
    private Emprunteur emprunteur;
    private LocalDate DateEmprunt;
    private LocalDate DateReteur;

    public EmprunteurLivre(List<Livre> livre, Emprunteur emprunteur, LocalDate dateEmprunt, LocalDate dateReteur) {
        this.livres = livres;
        this.emprunteur = emprunteur;
        DateEmprunt = dateEmprunt;
        DateReteur = dateReteur;
    }

    public EmprunteurLivre(){
        this.livres = new ArrayList<>();
    }

    public List<Livre> getLivre() {
        return livres;
    }

    public void addLivre(Livre livre) {
        livres.add(livre);
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public LocalDate getDateEmprunt() {
        return DateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        DateEmprunt = dateEmprunt;
    }

    public LocalDate getDateReteur() {
        return DateReteur;
    }

    public void setDateReteur(LocalDate dateReteur) {
        DateReteur = dateReteur;
    }
    DatabaseConnection db = new DatabaseConnection();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Livre livre : livres) {
            sb.append("Isbn Livre :").append(livre.getISBN()).append("\n");
            sb.append("Title Livre :").append(livre.getTitre()).append("\n");
        }

        sb.append("Cin Emprunteur :").append(emprunteur.getCin()).append("\n");
        sb.append("Emprunteur :").append(emprunteur.getPrenom()).append(" ").append(emprunteur.getNom()).append("\n");
        sb.append("DateEmprunt :").append(DateEmprunt).append("\n");
        sb.append("DateReteur :").append(DateReteur).append("\n");



        return sb.toString();
    }

    public void add_EmprunteurLivre(){
        String query ="INSERT INTO `emprunteurlivre`(`Cin`, `Isbn`, `DateEmprunt`, `DateReteur`) VALUES (?,?,?,?)";
        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            for (Livre livre : livres) {
                statement.setString(1, emprunteur.getCin());
                statement.setString(2, livre.getISBN());
                statement.setString(3, String.valueOf(DateEmprunt));
                statement.setString(4, String.valueOf(DateReteur));
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println(toString());
                }
            }
        }catch (SQLException e){
            System.out.println( e.getMessage());
        }
    }

    public boolean VerifierLivresEmprunt(String isbn,String cin){
        String query = "SELECT * from Livres INNER JOIN emprunteurlivre on livres.Isbn = emprunteurlivre.Isbn INNER JOIN emprunteur ON emprunteurlivre.Cin = emprunteur.Cin and livres.Isbn LIKE ? and emprunteur.Cin LIKE ?";
        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1,isbn);
            statement.setString(2,cin);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        }catch (Exception e){
            return false;
        }

    }

    public void supprimerEmprunteur(String isbn , String cin){
        String query ="SELECT id FROM `emprunteurlivre` WHERE Isbn LIKE ? and Cin LIKE ? and supprimer is null";
        ArrayList<Integer> listID = new ArrayList<Integer>();

        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1,isbn);
            statement.setString(2,cin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                listID.add(id);
            }
            if (listID.size() > 0){
                int _id = listID.get(0);
                supprimer(_id);
            }else {
                System.out.println("Le livre n'est pas emprunté.");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public  boolean supprimer(int id){
        String query = "UPDATE emprunteurlivre SET supprimer = ? WHERE Id LIKE ? ";
        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2,id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Livre Emprunté il est supprimé avec succès !");
                return true;
            } else {
                System.out.println("Aucun livre Emprunté avec cet ISBN et Cin");
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
