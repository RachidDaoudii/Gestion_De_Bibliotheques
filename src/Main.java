import _class.Menu;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();
        String choix;
        do {
            menu.ListMenu();
            System.out.println("Enter le choix : ");
            choix = in.nextLine();
            menu.direBonjourAvecSwitch(choix);
        }while (!choix.equals("10"));

    }
}