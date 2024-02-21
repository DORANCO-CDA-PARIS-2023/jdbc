package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdbc.connection.DataSource;
import jdbc.entity.Livre;

public class LivreDAOImpl implements ILivreDAO {
	
    private final Connection connection;
    private final Statement statement;

    public LivreDAOImpl() throws Exception {
        connection = DataSource.getInstance().getConnection();
        statement = connection.createStatement();
    }
    
	
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void findAll() throws Exception {
		List<Livre> livres = new ArrayList();
		ResultSet rs= null;
		
		try {
		String requete = "SELECT * FROM book";
		rs = statement.executeQuery(requete);
		while (rs != null && rs.next()) {
			Livre livre = null;
			livre = new Livre();
			livre.setId(rs.getInt("id"));
			livre.setTitle(rs.getString("title"));
			livre.setYear_publish(rs.getInt("year_publish"));
//			livre.setId_author(rs.getInt("id_author"));
			livres.add(livre);
			}	
		} finally {
			if(connection != null && !connection.isClosed()) {
//				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		for(Livre livre : livres) {
			System.out.println(livre.toString());
		}
	}

	@Override
	public void getById() throws Exception {
		Livre livre = null;
		ResultSet rs= null;
		
		System.out.print("Entrer un ID à rechercher : ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
//		sc.close();
		if (id <= 0) {
			throw new IllegalArgumentException("L'id doit être > 0");
		}
		
		try {
		String requete = "SELECT * FROM book WHERE id =?";
		PreparedStatement ps = connection.prepareStatement(requete);
		ps.setInt(1, id);		
		rs = ps.executeQuery();
		while (rs != null && rs.next()) {
			livre = new Livre();
			livre.setId(rs.getInt("id"));
			livre.setTitle(rs.getString("title"));
			livre.setYear_publish(rs.getInt("year_publish"));
//			livre.setId_author(rs.getInt("id_author"));
			}	
		} finally {
			if(connection != null && !connection.isClosed()) {
//				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
			System.out.println(livre.toString());
		
	}

	@Override
	public void add() throws Exception {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Titre : ");
	    String titre = scanner.nextLine();
	    //Attention : dates entre 1901 et 2055
	    System.out.print("Date de publication (minimum : 1901) : ");
	    int annee = scanner.nextInt();
		String requete = "INSERT INTO book (title, year_publish) " + "VALUES(?, ?)";
		PreparedStatement ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, titre);
		ps.setInt(2, annee);		
		ps.executeUpdate();
//		scanner.close();
	  }
	

	@Override
	public void remove() throws Exception {
		
		System.out.println("Entrer un ID à supprimer : ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
//		sc.close();
		
		if (id <= 0) {
			throw new IllegalArgumentException("L'id doit être > 0");
		}
		
		String requete = "DELETE FROM book WHERE id =?";
		PreparedStatement ps = connection.prepareStatement(requete);
		ps.setInt(1, id);
		ps.executeUpdate();
		System.out.println("Le livre numéro " + id + " a bien été supprimé.");
	}

	@Override
	public void findByTitle() throws Exception {
		List<Livre> livres = new ArrayList();
		ResultSet rs= null;
		
		System.out.print("Entrer un titre partiel ou complet : ");
		Scanner sc = new Scanner(System.in);
		String recherche = sc.nextLine();
//		sc.close();
		try {
		String requete = "SELECT * FROM book WHERE title LIKE ?";
		PreparedStatement ps = connection.prepareStatement(requete);
		ps.setString(1, "%" + recherche + "%");		
		rs = ps.executeQuery();
		while (rs != null && rs.next()) {
			Livre livre = new Livre();
			livre.setId(rs.getInt("id"));
			livre.setTitle(rs.getString("title"));
			livre.setYear_publish(rs.getInt("year_publish"));
//			livre.setId_author(rs.getInt("id_author"));
			livres.add(livre);
			}	
		} finally {
			if(connection != null && !connection.isClosed()) {
//				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		if(livres.isEmpty()) {
			System.out.println("Aucun résultat");
		}
		else {
			for(Livre livre : livres) {
				System.out.println(livre.toString());
			}
		}

	}



}
