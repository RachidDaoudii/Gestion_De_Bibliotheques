package _class;

import java.util.Scanner;

public class Menu {

    public void ListMenu(){
        System.out.println("""
                        --------------------------------------
                                         Menu         
                        --------------------------------------
                                1 _ Ajouter un livre 
                                2 _ Rechercher un livre 
                                3 _ Modifier un livre 
                                4 _ Supprimer un livre 
                                5 _ Afficher Les livre disponibles 
                                6 _ Afficher Les livre empruntes 
                                7 _ Emprunt un livre 
                                8 _ Retourner un livre 
                                9 _ Exit
                        """
        );
    }



    public Menu() {
    }

    public void direBonjourAvecSwitch(String choix){
        Livre livre = new Livre();
        Scanner in = new Scanner(System.in);

        switch(choix){

            case "1":
                System.out.println("Ajouter un livre");
                break;

            case "2":
                System.out.println("""
                        1_ Rechercher avec Auteur :
                        2_ Rechercher avec Titre :
                        """);
                String _2emechoix = in.nextLine();
                switch (_2emechoix) {
                    case "1":
                        System.out.println("Donner l' auteur :");
                        String auteur = in.nextLine();
                        livre.setAuteur(auteur);
                        livre.setTitre("");
                        livre.Rechercher_livre(livre.getTitre(), livre.getAuteur());
                        break;

                    case "2":
                        System.out.println("Donner le titre :");
                        String titre = in.nextLine();
                        livre.setAuteur("");
                        livre.setTitre(titre);
                        livre.Rechercher_livre(livre.getTitre(), livre.getAuteur());
                        break;

                    default:
                        System.out.println("Choix non valide");
                        break;
                }

                break;

            case "3":
                String isbn;
                System.out.println("Modifier un livre : ");
                System.out.println("Donner isbn de livre : ");
                isbn = in.nextLine();
                livre.setISBN(isbn);

                livre.Modifier_livre(livre.getISBN());
                break;

            case "4":
                System.out.println("Supprimer un livre");
                break;

            case "5":
                livre.Afficher_livre_disponible();
                break;

            case "6":
                livre.Afficher_livre_empruntes();
                break;

            case "7":
                System.out.println("Emprunt un livre");
                break;

            case "8":
                System.out.println("Retourner un livre");
                break;
            default:

                break;
        }

    }
}
