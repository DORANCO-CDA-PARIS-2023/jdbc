package jdbc.main;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc.dao.ILivreDAO;
import jdbc.dao.LivreDAOImpl;
import jdbc.service.LivreDAOService;

/**
 * Hello world!
 *
 */
public class MainLivreDAO 
{
    public static void main( String[] args ) throws Exception {

    	LivreDAOService livreDAOService = new LivreDAOService();
    	livreDAOService.start();
    }
}
