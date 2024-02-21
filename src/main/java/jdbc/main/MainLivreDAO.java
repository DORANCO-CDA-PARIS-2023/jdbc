package jdbc.main;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc.dao.ILivreDAO;
import jdbc.dao.LivreDAOImpl;

/**
 * Hello world!
 *
 */
public class MainLivreDAO 
{
    public static void main( String[] args ) {
    	ILivreDAO livreDAO = new LivreDAOImpl();
    	int choice = 0;
    	Scanner searchscan = new Scanner(System.in);
    	while(choice != 5) {
    	System.out.println("Quelle action souhaitez-vous effectuer ?");
    	System.out.println("1 - Afficher la liste de tous les livres");
    	System.out.println("2 - Rechercher un livre par ID");
    	System.out.println("3 - Ajouter un livre");
    	System.out.println("4 - Supprimer un livre");
    	System.out.println("5 - Fermer application");
    	System.out.print("Votre choix : ");
    	choice = searchscan.nextInt();
    	switch(choice) {
    	case 1:
        	try {
    			livreDAO.findAll();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		break;
    	case 2:
        	try {
    			livreDAO.getById();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		break;
    	case 3:
        	try {
    			livreDAO.add();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		break;
    	case 4:
        	try {
    			livreDAO.remove();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		break;
    	case 5:
    		System.out.println("Fermeture du programme ...");
        	searchscan.close();
    		break;
    	default:
    		System.out.println("Choix invalide");
    	}
    	System.out.println("Action effectuée.");
    	}

    }
}
