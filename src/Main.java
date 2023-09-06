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
        }while (!choix.equals("10"));

        //boolean er = livres.get_All_Isbn_Livres();
        /*if (er) {
            System.out.println("exist");
        } else {
            System.out.println("n exist pas");
        }*/

    }
}