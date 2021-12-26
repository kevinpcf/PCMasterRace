package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductModelDS implements ProductModel {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/PCMasterRace");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "prodotti";

	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO " + ProductModelDS.TABLE_NAME
				+ " (nome, descrizione, categoria, prezzo, quantita, IVA, immagine) VALUES (?, ?, ?, ?, ?, ?, ?)";

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getDescrizione());
			preparedStatement.setString(3, product.getCategoria());
			preparedStatement.setDouble(4, product.getPrezzo());
			preparedStatement.setInt(5, product.getQuantita());
			preparedStatement.setInt(6, product.getIVA());
			preparedStatement.setString(7, product.getImmagine());

			preparedStatement.executeUpdate();

			//connection.commit();
			if (preparedStatement != null) preparedStatement.close();
			if (connection != null) connection.close();
	}

	@Override
	public synchronized ProductBean doRetrieveByKey(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME + " WHERE codice = ?";

		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setInt(1, codice);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			bean.setCodice(rs.getInt("codice"));
			bean.setNome(rs.getString("nome"));
			bean.setDescrizione(rs.getString("descrizione"));
			bean.setCategoria(rs.getString("categoria"));
			bean.setPrezzoBase(rs.getDouble("prezzo"));
			bean.setQuantita(rs.getInt("quantita"));
			bean.setIVA(rs.getInt("IVA"));
			bean.setImmagine(rs.getString("immagine"));
		}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return bean;
	}

	@Override
	public synchronized boolean doDelete(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String deleteProduct = "UPDATE " + ProductModelDS.TABLE_NAME + " SET quantita = quantita-1 WHERE codice=?";

	
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteProduct);
		preparedStatement.setInt(1, codice);

		result = preparedStatement.executeUpdate();

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return (result != 0);
	}

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME;

		if (ordine != null && !ordine.equals("")) {
			selectSQL += " ORDER BY " + ordine;
		}

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) 
		{
			ProductBean bean = new ProductBean();

			bean.setCodice(rs.getInt("codice"));
			bean.setNome(rs.getString("nome"));
			bean.setDescrizione(rs.getString("descrizione"));
			bean.setCategoria(rs.getString("categoria"));
			bean.setPrezzoBase(rs.getDouble("prezzo"));
			bean.setQuantita(rs.getInt("quantita"));
			bean.setIVA(rs.getInt("IVA"));
			bean.setImmagine(rs.getString("immagine"));
			products.add(bean);
		}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return products;
	}
	
	public synchronized boolean doDeleteAll(int codice) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String deleteProduct = "DELETE FROM " + ProductModelDS.TABLE_NAME + " WHERE codice = ?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteProduct);
		preparedStatement.setInt(1, codice);

		result = preparedStatement.executeUpdate();

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return (result != 0);
	}
	
	public synchronized boolean doUpdate(ProductBean bean) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String query = " UPDATE " + ProductModelDS.TABLE_NAME + " SET nome = ?, descrizione = ?, categoria = ?, prezzo = ?, quantita = ?, IVA = ? WHERE codice = ?";
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, bean.getNome());
		preparedStatement.setString(2, bean.getDescrizione());
		preparedStatement.setString(3, bean.getCategoria());
		preparedStatement.setDouble(4, bean.getPrezzoBase());
		preparedStatement.setInt(5, bean.getQuantita());
		preparedStatement.setInt(6, bean.getIVA());
		preparedStatement.setInt(7, bean.getCodice());

		result = preparedStatement.executeUpdate();
		
		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return (result != 0);
	}
	
	public synchronized Collection<ProductBean> doRetrieveByName(String ricerca) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ricerca = "%"+ricerca+"%";
		String query = "SELECT * FROM " + ProductModelDS.TABLE_NAME + " WHERE nome LIKE ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, ricerca);
		
		ArrayList<ProductBean> products = new ArrayList<ProductBean>();
		
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) 
		{
			ProductBean bean = new ProductBean();

			bean.setCodice(rs.getInt("codice"));
			bean.setNome(rs.getString("nome"));
			bean.setDescrizione(rs.getString("descrizione"));
			bean.setCategoria(rs.getString("categoria"));
			bean.setPrezzoBase(rs.getDouble("prezzo"));
			bean.setQuantita(rs.getInt("quantita"));
			bean.setIVA(rs.getInt("IVA"));
			bean.setImmagine(rs.getString("immagine"));
			products.add(bean);
		}
		
		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return products;
	}
	
	public synchronized Collection<ProductBean> doRetrieveByCategory(String categoria) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String query = "SELECT * FROM " + ProductModelDS.TABLE_NAME + " WHERE categoria = ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, categoria);
		ArrayList<ProductBean> products = new ArrayList<ProductBean>();
		
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) 
		{
			ProductBean bean = new ProductBean();

			bean.setCodice(rs.getInt("codice"));
			bean.setNome(rs.getString("nome"));
			bean.setDescrizione(rs.getString("descrizione"));
			bean.setCategoria(rs.getString("categoria"));
			bean.setPrezzoBase(rs.getDouble("prezzo"));
			bean.setQuantita(rs.getInt("quantita"));
			bean.setIVA(rs.getInt("IVA"));
			bean.setImmagine(rs.getString("immagine"));
			products.add(bean);
		}
		
		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return products;
	}
	
	
}