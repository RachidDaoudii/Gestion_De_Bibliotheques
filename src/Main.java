import _class.Menu;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();
        menu.ListMenu();
        String choix;
        do {
            System.out.println("Enter le choix : ");
            choix = in.nextLine();
            menu.direBonjourAvecSwitch(choix);
        }while (!choix.equals("9"));





        //livres.Afficher_livre_disponible();
        //livres.Afficher_livre_empruntes();
        //livres.Rechercher_livre("","Jane Smith");
        //livres.Modifier_livre("N1");
        //livres.setISBN("978-1234567890");
        //boolean er = livres.get_All_Isbn_Livres();
        /*if (er) {
            System.out.println("exist");
        } else {
            System.out.println("n exist pas");
        }*/

        //dbConnection.closeConnection();
        //Scanner in = new Scanner(System.in);
        //
        //




    }
}