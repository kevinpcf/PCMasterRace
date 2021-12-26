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

public class PaymentDS 
{
	private static DataSource ds;

	static 
	{
		try 
		{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/PCMasterRace");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "pagamenti";

	public synchronized void doSave(PaymentBean payment) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String inserimento = "INSERT INTO " + TABLE_NAME + " (utente, numero, CVV, scadenza) VALUES (?, ?, ?, ?)";

		    connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(inserimento);
			preparedStatement.setString(1, payment.getUtente());
			preparedStatement.setString(2, payment.getNumero());
			preparedStatement.setInt(3, payment.getCVV());
			preparedStatement.setString(4, payment.getScadenza());

			preparedStatement.executeUpdate();

			//connection.commit();
			if (preparedStatement != null) preparedStatement.close();
			if (connection != null) connection.close();
	}
	
	public synchronized ArrayList<PaymentBean> doRetrieveByUser(String username) throws SQLException, ParseException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<PaymentBean> pagamenti = new ArrayList<PaymentBean>();

		String selezione = "SELECT * FROM " + TABLE_NAME + " WHERE utente = ?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selezione);
		preparedStatement.setString(1, username);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			PaymentBean bean = new PaymentBean();
			
			bean.setCodice(rs.getInt("codice"));
		    bean.setUtente(rs.getString("utente"));
			bean.setNumero(rs.getString("numero"));
			bean.setCVV(rs.getInt("CVV"));
			bean.setScadenza(rs.getString("scadenza"));
			
			pagamenti.add(bean);
		}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return pagamenti;
	}
	
	public synchronized ArrayList<PaymentBean> doRetrieveById(int code) throws SQLException, ParseException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<PaymentBean> pagamenti = new ArrayList<PaymentBean>();

		String selezione = "SELECT * FROM " + TABLE_NAME + " WHERE codice = ?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selezione);
		preparedStatement.setInt(1, code);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			PaymentBean bean = new PaymentBean();
			
			bean.setCodice(rs.getInt("codice"));
		    bean.setUtente(rs.getString("utente"));
			bean.setNumero(rs.getString("numero"));
			bean.setCVV(rs.getInt("CVV"));
			bean.setScadenza(rs.getString("scadenza"));
			
			pagamenti.add(bean);
		}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return pagamenti;
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
