package doranco;

import java.sql.SQLException;

import doranco.service.CommandLine;

public class App {
    public static void main(String[] args) {
        try {
            // we use the CommandLine class
            CommandLine cli = new CommandLine();
            cli.start();
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de l'initialisation de l'interface en ligne de commande.");
        }
    }
}
