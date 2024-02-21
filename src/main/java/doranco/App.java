package doranco;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import doranco.dao.BookDao;
import doranco.entity.Book;
import doranco.service.CommandLine;


public class App 
{
    public static void main( String[] args ) throws Exception {
    	try {
            CommandLine commandLine = new CommandLine();
            commandLine.start();
        } catch (SQLException e) {
            System.err.println("Error database connection");
        }
    }
}