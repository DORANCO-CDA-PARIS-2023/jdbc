package doranco;

import doranco.service.CommandLine;

import java.sql.SQLException;

public class App
{

    public static void main( String[] args ) {
        try {
            CommandLine commandLine = new CommandLine();
            commandLine.start();
        } catch (SQLException e) {
            System.err.println("Error database connection");
        }
    }
}
