package doranco.service;

import java.io.InputStream;
import java.util.Scanner;

public final class DaoCLI {

    private InputStream inputStream;

    public DaoCLI(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void start() {
        Scanner scanner = new Scanner(inputStream);

        do {
            ActionType actionType = getActionType(scanner);
            DataType dataType = getDataType(scanner);
            ActionData actionData = new ActionData(actionType, dataType);
            actionData.execute(scanner);
        } while (getContinue(scanner));

        scanner.close();
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
            System.out.println("Entrez le type de donnée que vous souhaitez manipuler " +
                    "(AUTHOR|BOOK|BOOK_GENRE|BORROW|GENRE|STUDENT):");
            try {
                String input = sc.nextLine();
                dataType = DataType.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException ignored) { }
        } while (dataType == null);

        return dataType;
    }
}
