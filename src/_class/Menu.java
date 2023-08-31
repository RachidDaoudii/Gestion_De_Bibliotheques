package _class;

public class Menu {

    public void ListMenu(){
        System.out.println(
                "--------------------------------------\n" +
                "                 Menu                \n" +
                "--------------------------------------\n"+
                        " 1 _ Ajouter un livre"
        );
    }

    public Menu() {
    }

    public void direBonjourAvecSwitch(String choix){

        switch(choix){

            case "1":
                System.out.println("Ajouter hhhh");
                break;

            case "2":
                System.out.println("Hello");
                break;

            case "3":
                System.out.println("Buenos dias");
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }

    }
}
