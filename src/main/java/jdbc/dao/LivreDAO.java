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

public class LivreDAO implements ILivreDAO {

	@Override
	public void getLivres() throws Exception {
		List<Livre> livres = new ArrayList();
		Connection connection = null;
		ResultSet rs= null;
		
		try {
		String requete = "SELECT * FROM book";
		connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement(requete);
		rs = ps.executeQuery();
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
				connection.close();
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
	public void getLivre(int id) throws Exception {
		Livre livre = null;
		Connection connection = null;
		ResultSet rs= null;
		
		try {
		String requete = "SELECT * FROM book WHERE id =?";
		connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement(requete);
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
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
			System.out.println(livre.toString());
		
	}

	@Override
	public void addLivre() throws Exception {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Titre : ");
	    String titre = scanner.nextLine();
	    System.out.print("Date de publication : ");
	    int annee = scanner.nextInt();
		String requete = "INSERT INTO book (title, year_publish) " + "VALUES(?, ?)";
		Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, titre);
		ps.setInt(2, annee);		
		ps.executeUpdate();
	  }
	

	@Override
	public void removeLivre(int id) throws Exception {
		if (id <= 0) {
			throw new IllegalArgumentException("L'id doit être > 0");
		}
		Connection connection = null;
		String requete = "DELETE FROM book WHERE id =?";
		connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement(requete);
		ps.setInt(1, id);
		ps.executeUpdate();
		System.out.println("Le livre numéro " + id + " a bien été supprimé.");
	}



}
