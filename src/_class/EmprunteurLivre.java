package _class;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EmprunteurLivre {
    private List<Livre> livres;
    private Emprunteur emprunteur;
    private LocalDate DateEmprunt;
    private LocalDate DateReteur;

    public EmprunteurLivre(List<Livre> livre, Emprunteur emprunteur, LocalDate dateEmprunt, LocalDate dateReteur) {
        this.livres = livre;
        this.emprunteur = emprunteur;
        DateEmprunt = dateEmprunt;
        DateReteur = dateReteur;
        //System.out.println(livre.getISBN());
        System.out.println(emprunteur.getNom());
        System.out.println(dateEmprunt);
        System.out.println(dateReteur);
    }

    public EmprunteurLivre(){

    }

    public List<Livre> getLivre() {
        return livres;
    }

    public void addLivre(Livre livre) {
        livres.add(livre); // Add Livre objects to the list
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


}
