package _class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DatabaseConnection;
public class Emprunteur {
    private String Cin;
    private String Nom;
    private String Prenom;

    public Emprunteur(String cin, String nom, String prenom) {
        Cin = cin;
        Nom = nom;
        Prenom = prenom;
    }

    public Emprunteur(){

    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    DatabaseConnection db = new DatabaseConnection();

    public void Ajouter_Emprunteur(){
        String query = "INSERT INTO `emprunteur`(`Cin`, `Nom`, `Prenom`) VALUES (?,?,?)";
        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1,Cin);
            statement.setString(2,Nom);
            statement.setString(3,Prenom);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("L'utilisateur ajouté avec succès !");
            }
        }catch (SQLException e){
            //msg
            //System.out.println(e.getMessage());
        }
    }

    public boolean VerifierLivres(String cin){
        String query = "SELECT * from Emprunteur where Cin Like ?";
        try(PreparedStatement statement = db.getConnection().prepareStatement(query)){
            statement.setString(1,cin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                setCin(resultSet.getString("Cin"));
                setNom(resultSet.getString("Nom"));
                setPrenom(resultSet.getString("Prenom"));
                return true;
            } else {
                return false;
            }

        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }
}
