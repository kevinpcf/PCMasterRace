package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AddressDS 
{
	private static DataSource ds;

	static 
	{
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/PCMasterRace");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "indirizzi";

	public synchronized void doSave(AddressBean address) throws SQLException 
	{

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String inserimento = "INSERT INTO " + TABLE_NAME + " (utente, via, civico, CAP, citta, provincia) VALUES (?, ?, ?, ?, ?, ?)";

		    connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(inserimento);
			preparedStatement.setString(1, address.getUtente());
			preparedStatement.setString(2, address.getVia());
			preparedStatement.setString(3, address.getCivico());
			preparedStatement.setString(4, address.getCAP());
			preparedStatement.setString(5, address.getCitta());
			preparedStatement.setString(6, address.getProvincia());

			preparedStatement.executeUpdate();

			//connection.commit();
			if (preparedStatement != null) preparedStatement.close();
			if (connection != null) connection.close();
	}
	
	public synchronized ArrayList<AddressBean> doRetrieveByUser(String username) throws SQLException, ParseException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<AddressBean> indirizzi = new ArrayList<AddressBean>();

		String selezione = "SELECT * FROM " + TABLE_NAME + " WHERE utente = ?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selezione);
		preparedStatement.setString(1, username);
		
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			AddressBean bean = new AddressBean();
			
			bean.setCodice(rs.getInt("codice"));
		    bean.setUtente(rs.getString("utente"));
			bean.setVia(rs.getString("via"));
			bean.setCivico(rs.getString("civico"));
			bean.setCAP(rs.getString("CAP"));
			bean.setCitta(rs.getString("citta"));
			bean.setProvincia(rs.getString("provincia"));
			
			indirizzi.add(bean);
		}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return indirizzi;
	}
	
	public synchronized ArrayList<AddressBean> doRetrieveById(int code) throws SQLException, ParseException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<AddressBean> indirizzi = new ArrayList<AddressBean>();

		String selezione = "SELECT * FROM " + TABLE_NAME + " WHERE codice = ?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selezione);
		preparedStatement.setInt(1, code);
		
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			AddressBean bean = new AddressBean();
			
			bean.setCodice(rs.getInt("codice"));
		    bean.setUtente(rs.getString("utente"));
			bean.setVia(rs.getString("via"));
			bean.setCivico(rs.getString("civico"));
			bean.setCAP(rs.getString("CAP"));
			bean.setCitta(rs.getString("citta"));
			bean.setProvincia(rs.getString("provincia"));
			
			indirizzi.add(bean);
		}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return indirizzi;
	}
	

	public synchronized boolean doDelete(int codice) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteProduct = "DELETE FROM " + TABLE_NAME + " WHERE codice=?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteProduct);
		preparedStatement.setInt(1, codice);

		result = preparedStatement.executeUpdate();

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return (result != 0);
	}
}
