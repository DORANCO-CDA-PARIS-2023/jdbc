package doranco;


import java.util.Scanner;

public class App {

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);

        do {
            ActionType actionType = getActionType(scanner);
            DataType dataType = getDataType(scanner);
        } while (getContinue(scanner));

        scanner.close();
    }

    private static void treatActionData() {
        // TODO
    }

    private static boolean getContinue(Scanner sc) {
        String yesOrNot = null;

        do {
            System.out.println("Voulez-vous effectuer une nouvelle action (YES|NO)?");
            try {
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("NO")) {
                    yesOrNot = input;
                }
            } catch (IllegalArgumentException ignored) { }
        } while (yesOrNot == null);

        return yesOrNot.equalsIgnoreCase("yes");
    }

    private static ActionType getActionType(Scanner sc) {
        ActionType actionType = null;

        do {
            System.out.println("Entrez l'action que vous souhaitez effectuer (CREATE|READ|UPDATE|DELETE):");
            try {
                String input = sc.nextLine();
                actionType = ActionType.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException ignored) { }
        } while (actionType == null);

        return actionType;
    }

    private static DataType getDataType(Scanner sc) {
        DataType dataType = null;

        do {
            System.out.println("Entrez le type de donnée que vous souhaitez manipuler (AUTHOR|BOOK|BOOK_GENRE|BORROW|GENRE|STUDENT):");
            try {
                String input = sc.nextLine();
                dataType = DataType.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException ignored) { }
        } while (dataType == null);

        return dataType;
    }

}
