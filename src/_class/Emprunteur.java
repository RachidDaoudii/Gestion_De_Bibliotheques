package _class;

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
}
