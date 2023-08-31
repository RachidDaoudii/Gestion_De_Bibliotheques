import _class.Livres;
import db.DatabaseConnection;
import _class.Menu;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        //DatabaseConnection dbConnection = new DatabaseConnection();

        //Livres livres = new Livres();

        //livres.retrieveUsers();

        //dbConnection.closeConnection();
        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();
        menu.ListMenu();
        //String choix = in.nextLine();
        //menu.direBonjourAvecSwitch(choix);

    }
}