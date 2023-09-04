package _class;

import java.util.Date;

public class EmprunteurLivre {
    private Livre livre;
    private Emprunteur emprunteur;
    private Date DateEmprunt;
    private Date DateReteur;

    public EmprunteurLivre(Livre livre, Emprunteur emprunteur, Date dateEmprunt, Date dateReteur) {
        this.livre = livre;
        this.emprunteur = emprunteur;
        DateEmprunt = dateEmprunt;
        DateReteur = dateReteur;
    }

    public EmprunteurLivre(){

    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Date getDateEmprunt() {
        return DateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        DateEmprunt = dateEmprunt;
    }

    public Date getDateReteur() {
        return DateReteur;
    }

    public void setDateReteur(Date dateReteur) {
        DateReteur = dateReteur;
    }


}
