package _class;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        for (Livre livre : livres) {
            sb.append("  Livre=").append(livre.getISBN()).append("\n");
            sb.append("  Livre=").append(livre.getTitre()).append("\n");
        }

        sb.append("  Emprunteur=").append(emprunteur.getCin()).append(" ").append(emprunteur.getNom()).append("\n");
        sb.append("  DateEmprunt=").append(DateEmprunt).append("\n");
        sb.append("  DateReteur=").append(DateReteur).append("\n");



        return sb.toString();
    }
}
