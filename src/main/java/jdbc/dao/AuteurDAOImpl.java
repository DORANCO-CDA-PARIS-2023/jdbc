package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import jdbc.connection.DataSource;
import jdbc.utils.Dates;

public class AuteurDAOImpl implements IAuteurDAO {
	
    private final Connection connection;
    private final Statement statement;

    public AuteurDAOImpl() throws Exception {
        connection = DataSource.getInstance().getConnection();
        statement = connection.createStatement();
    }
    

	@Override
	public void findAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getById() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add() throws SQLException, Exception {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Nom : ");
	    String name = scanner.nextLine();
	    System.out.print("Prénom : ");
	    String firstname = scanner.nextLine();
	    System.out.print("Date de naissance (format dd/MM/YYYY) : ");
	    String annee = scanner.nextLine();
		String requete = "INSERT INTO author (name, firstname, birthday) " + "VALUES(?, ?)";
		PreparedStatement ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, name);
		ps.setString(2, firstname);
		ps.setDate(3, Dates.convertDateUtilToSql(Dates.convertStringToDate(annee)));		
		ps.executeUpdate();
//		scanner.close();		
	}

	@Override
	public void remove() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
