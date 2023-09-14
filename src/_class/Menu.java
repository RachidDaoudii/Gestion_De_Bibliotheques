package _class;

import java.util.*;
import java.time.LocalDate;

public class Menu {

    public void ListMenu(){
        System.out.println("""
            --------------------------------------
                             Menu        \s
            --------------------------------------
                    1 _ Ajouter un livre\s
                    2 _ Rechercher un livre\s
                    3 _ Modifier un livre\s
                    4 _ Supprimer un livre\s
                    5 _ Afficher Les livre disponibles\s
                    6 _ Emprunt un livre\s
                    7 _ Retourner un livre\s
                    8 _ Rapport Satistique\s
                    9 _ Exit
            """
        );
    }

    public Menu() {
    }
    Livre livre = new Livre();

    Scanner in = new Scanner(System.in);
    public void direBonjourAvecSwitch(String choix){


        switch (choix) {
            case "1" -> {
                System.out.println("Ajouter un livre");
                livre.Ajouter_livre();
            }
            case "2" -> {
                System.out.println("""
                        1_ Rechercher avec Auteur :
                        2_ Rechercher avec Titre :
                        """);
                String _2emechoix = in.nextLine();
                switch (_2emechoix) {
                    case "1" -> {
                        System.out.println("Donner l' auteur :");
                        String auteur = in.nextLine();
                        livre.setAuteur(auteur);
                        livre.setTitre("");
                        livre.Rechercher_livre(livre.getTitre(), livre.getAuteur());
                    }
                    case "2" -> {
                        System.out.println("Donner le titre :");
                        String titre = in.nextLine();
                        livre.setAuteur("");
                        livre.setTitre(titre);
                        livre.Rechercher_livre(livre.getTitre(), livre.getAuteur());
                    }
                    default -> System.out.println("Choix non valide");
                }

            }
            case "3" -> {
                String isbn;

                System.out.println("Modifier un livre : ");
                do {
                    System.out.println("Donner ISBN du livre : ");
                    isbn = in.nextLine();

                    if(livre.VerifierLivres(isbn)){
                            livre.Modifier_livre(isbn);
                    }else {
                        System.out.println("Aucun livre trouvé avec cet ISBN.");
                    }

                } while (isbn.isEmpty());
            }
            case "4" -> {
                livre.Supprimer_livre();
            }
            case "5" -> livre.LivresDisponible();
            case "6" -> {
                System.out.println("Emprunt un livre");
                String isbn;
                String Cin;
                String NomEmprunteur;
                String PrenomEmprunteur;
                Emprunteur emprunteur = new Emprunteur();
                EmprunteurLivre emprunteurLivre = new EmprunteurLivre();
                do {
                    System.out.println("Donner ISBN du livre : ");
                    isbn = in.nextLine();
                    if(livre.VerifierLivres(isbn)){
                        do {
                            System.out.println("Donner Cin Emprunteur : ");
                            Cin = in.nextLine();
                            if(emprunteur.VerifierEmprunteur(Cin)){
                                emprunteur.setCin(Cin);
                            }
                            else {
                                emprunteur.setCin(Cin);
                                do {
                                    System.out.println("Donner Nom Emprunteur : ");
                                    NomEmprunteur = in.nextLine();
                                    emprunteur.setNom(NomEmprunteur);

                                } while (NomEmprunteur.isEmpty());

                                do {
                                    System.out.println("Donner Prenom Emprunteur : ");
                                    PrenomEmprunteur = in.nextLine();
                                    emprunteur.setPrenom(PrenomEmprunteur);
                                } while (PrenomEmprunteur.isEmpty());
                            }
                        } while (Cin.isEmpty());

                        LocalDate dateEmprunt = LocalDate.now();
                        LocalDate dateReteur = dateEmprunt.plusDays(20);
                        emprunteurLivre.addLivre(livre);
                        emprunteurLivre.setEmprunteur(emprunteur);
                        emprunteurLivre.setDateEmprunt(dateEmprunt);
                        emprunteurLivre.setDateReteur(dateReteur);
                        emprunteur.Ajouter_Emprunteur();
                        emprunteurLivre.add_EmprunteurLivre();

                    }else {
                        System.out.println("Aucun livre trouvé avec cet ISBN.");
                    }

                } while (isbn.isEmpty());
            }
            case "7" -> {
                String isbn;
                String Cin;
                EmprunteurLivre emprunteurLivre = new EmprunteurLivre();
                do {
                    System.out.println("Donner ISBN du livre : ");
                    isbn = in.nextLine();
                    if (livre.VerifierLivres(isbn)) {
                        do{
                            System.out.println("Donner Cin Emprunteur : ");
                            Cin = in.nextLine();
                        }while (Cin.isEmpty());

                        if(emprunteurLivre.VerifierLivresEmprunt(isbn,Cin)){
                            emprunteurLivre.supprimerEmprunteur(isbn,Cin);
                        }else {
                            System.out.println("Le livre n'est pas emprunté dans ce numéro cin");
                        }
                    }else {
                        System.out.println("Aucun livre trouvé avec cet ISBN.");
                    }
                }while (isbn.isEmpty());
            }
            case "8" -> {
                livre.Rapprot();
            }
            default -> {
                System.out.println();
            }
        }

    }
}
