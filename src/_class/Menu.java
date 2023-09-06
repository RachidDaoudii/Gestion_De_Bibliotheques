package _class;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;
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
                    6 _ Afficher Les livre empruntes\s
                    7 _ Emprunt un livre\s
                    8 _ Retourner un livre\s
                    9 _ Rapport Satistique\s
                    10 _ Exit
            """
        );
    }

    public Menu() {
    }

    public void direBonjourAvecSwitch(String choix){
        Livre livre = new Livre();
        Emprunteur emprunteur = new Emprunteur();

        Scanner in = new Scanner(System.in);

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

                    if (!isbn.isEmpty()) {
                        livre.setISBN(isbn);
                        livre.Modifier_livre(livre.getISBN());
                    } else {
                        System.out.println("ISBN ne peut pas être vide. Réessayez.");
                    }

                } while (isbn.isEmpty());
            }
            case "4" -> {
                System.out.println("Supprimer un livre");
                livre.Supprimer_livre();
            }
            case "5" -> livre.LivresDisponible();
            case "6" -> livre.LivresEmprunt();
            case "7" -> {
                System.out.println("Emprunt un livre");

                String isbn;
                String Cin;
                String NomEmprunteur;
                String PrenomEmprunteur;

                do {
                    System.out.println("Donner ISBN du livre : ");
                    isbn = in.nextLine();
                    if(livre.VerifierLivres(isbn)){
                        do {
                            System.out.println("Donner Cin Emprunteur : ");
                            Cin = in.nextLine();
                        } while (Cin.isEmpty());

                        do {
                            System.out.println("Donner Nom Emprunteur : ");
                            NomEmprunteur = in.nextLine();
                        } while (NomEmprunteur.isEmpty());

                        do {
                            System.out.println("Donner Prenom Emprunteur : ");
                            PrenomEmprunteur = in.nextLine();
                        } while (PrenomEmprunteur.isEmpty());

                        emprunteur.setCin(Cin);
                        emprunteur.setNom(NomEmprunteur);
                        emprunteur.setPrenom(PrenomEmprunteur);
                        LocalDate dateEmprunt = LocalDate.now();
                        LocalDate dateReteur = dateEmprunt.plusDays(20);

                        EmprunteurLivre emprunteurLivre = new EmprunteurLivre((List<Livre>) livre,emprunteur,dateEmprunt,dateReteur);


                    }else {
                        System.out.println("Aucun livre trouvé avec cet ISBN.");
                    }

                    /** if (!isbn.isEmpty()) {
                        livre.setISBN(isbn);

                    } else {
                        System.out.println("ISBN ne peut pas être vide. Réessayez.");
                    }**/

                } while (isbn.isEmpty());
            }
            case "8" -> System.out.println("Retourner un livre");
            case "9" -> {
                System.out.println("Rapport Satistique");
                String Titre;
                do {
                    System.out.println("Donner Tiire de livre : ");
                    Titre = in.nextLine();
                    livre.setTitre(Titre);
                }while (Titre.isEmpty());
                livre.Rapprot();
            }
            default -> {
            }
        }

    }
}
