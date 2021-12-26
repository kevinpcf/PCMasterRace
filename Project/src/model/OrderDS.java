package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDS 
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

	private static final String TABLE_NAME = "ordini";

	public synchronized void doSave(OrderBean order) throws SQLException 
	{

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String inserimento = "INSERT INTO " + TABLE_NAME + " (username, codice, data, quantita, prezzo, codiceIndirizzo, codicePagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";

		GregorianCalendar g = order.getData();
		String data = g.get(Calendar.YEAR)+"-"+(g.get(Calendar.MONTH)+1)+"-"+g.get(Calendar.DAY_OF_MONTH);
		
		    connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(inserimento);
			preparedStatement.setString(1, order.getUsername());
			preparedStatement.setInt(2, order.getCodice());
			preparedStatement.setString(3, data);
			preparedStatement.setInt(4, order.getQuantita());
			preparedStatement.setDouble(5, order.getPrezzo());
			preparedStatement.setInt(6, order.getCodiceIndirizzo());
			preparedStatement.setInt(7, order.getCodicePagamento());

			preparedStatement.executeUpdate();

			//connection.commit();
			if (preparedStatement != null) preparedStatement.close();
			if (connection != null) connection.close();
	}
	
	public synchronized OrderBean doRetrieveById(int id) throws SQLException, ParseException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean = new OrderBean();

		String selezione = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selezione);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		String data = "";
		
	    bean.setId(rs.getInt("id"));
		bean.setUsername(rs.getString("username"));
		bean.setCodice(rs.getInt("codice"));
		data = rs.getString("data");
		bean.setQuantita(rs.getInt("quantita"));
		bean.setPrezzo(rs.getDouble("prezzo"));
		bean.setCodiceIndirizzo(rs.getInt("codiceIndirizzo"));
		bean.setCodicePagamento(rs.getInt("codicePagamento"));

		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(d);
		bean.setData(g);
		
		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return bean;
	}
	
	public synchronized Collection<OrderBean> doRetrieveByUsername(String username) throws SQLException, ParseException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrderBean> ordini = new ArrayList<OrderBean>();
		
		String selezione = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selezione);
		preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			String data = "";
			Date d = null;
			
			while (rs.next()) 
			{
				OrderBean bean = new OrderBean();

				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setCodice(rs.getInt("codice"));
				data = rs.getString("data");
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setCodiceIndirizzo(rs.getInt("codiceIndirizzo"));
				bean.setCodicePagamento(rs.getInt("codicePagamento"));
				
				d = new SimpleDateFormat("yyyy-MM-dd").parse(data);
				GregorianCalendar g = new GregorianCalendar();
				g.setTime(d);
				bean.setData(g);
				
				ordini.add(bean);
			}
			
        if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return ordini;
	}
	
	public synchronized boolean doDelete(int id) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteProduct = "DELETE FROM " + TABLE_NAME + " WHERE id=?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteProduct);
		preparedStatement.setInt(1, id);

		result = preparedStatement.executeUpdate();

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return (result != 0);
	}
	
	
	public synchronized Collection<OrderBean> doRetrieveAll() throws SQLException, ParseException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrderBean> ordini = new ArrayList<OrderBean>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			String data = "";
			Date d = null;

			while (rs.next()) 
			{
				OrderBean bean = new OrderBean();

				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setCodice(rs.getInt("codice"));
				data = rs.getString("data");
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setCodiceIndirizzo(rs.getInt("codiceIndirizzo"));
				bean.setCodicePagamento(rs.getInt("codicePagamento"));
				
				d = new SimpleDateFormat("yyyy-MM-dd").parse(data);
				GregorianCalendar g = new GregorianCalendar();
				g.setTime(d);
				bean.setData(g);
				
				ordini.add(bean);
			}
			
        if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return ordini;
	}
	
	public synchronized Collection<OrderBean> doRetrieveBetweenDates(String dataInizio, String dataFine, String username) throws SQLException, ParseException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<OrderBean> ordini = new ArrayList<OrderBean>();
		
		String query = "";
		
		if(username != null)
		{
			query = " SELECT * FROM "+ OrderDS.TABLE_NAME + " WHERE (data BETWEEN '"+dataInizio+"' and '"+dataFine+"') and username = '"+username+"'";
		}
		else
		{
			query = " SELECT * FROM "+ OrderDS.TABLE_NAME + " WHERE (data BETWEEN '"+dataInizio+"' and '"+dataFine+"')";
		}
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(query);

			ResultSet rs = preparedStatement.executeQuery();
			String data = "";
			Date d = null;

			while (rs.next()) 
			{
				OrderBean bean = new OrderBean();

				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setCodice(rs.getInt("codice"));
				data = rs.getString("data");
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setCodiceIndirizzo(rs.getInt("codiceIndirizzo"));
				bean.setCodicePagamento(rs.getInt("codicePagamento"));
				
				d = new SimpleDateFormat("yyyy-MM-dd").parse(data);
				GregorianCalendar g = new GregorianCalendar();
				g.setTime(d);
				bean.setData(g);
				ordini.add(bean);
			}
        if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return ordini;
	}
}
