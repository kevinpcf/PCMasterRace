package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDS 
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

	private static final String TABLE_NAME = "utenti";

	public synchronized void doSave(UserBean user) throws SQLException 
	{

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String inserimento = "INSERT INTO " + TABLE_NAME + " (username, password, nome, cognome, admin) VALUES (?, MD5(?), ?, ?, ?)";

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(inserimento);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getNome());
			preparedStatement.setString(4, user.getCognome());
			preparedStatement.setBoolean(5, user.isAdmin());

			preparedStatement.executeUpdate();

			//connection.commit();
			if (preparedStatement != null) preparedStatement.close();
			if (connection != null) connection.close();
	}
        
        public synchronized List<UserBean> doRetrieveByUsername(String username) throws SQLException
        {   
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
            
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection != null ? connection.prepareStatement(query) : null;
            
            List<UserBean> list = new ArrayList<>();
            
            if (null != preparedStatement)
            {
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();
                
                while (rs.next())
                {
                    UserBean bean = new UserBean();
                    bean.setUsername(rs.getString("username"));
                    bean.setPassword(rs.getString("password"));
                    bean.setNome(rs.getString("nome"));
                    bean.setCognome(rs.getString("cognome"));
                    bean.setAdmin(rs.getBoolean("admin"));
                    list.add(bean);
                }
                
                preparedStatement.close();
                connection.close();
            }
            return list;
        }
	
	public synchronized UserBean doRetrieveByKey(String username, String password) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? and password = MD5(?)";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) 
		{
			bean.setUsername(rs.getString("username"));
			bean.setPassword(rs.getString("password"));
			bean.setNome(rs.getString("nome"));
			bean.setCognome(rs.getString("cognome"));
			bean.setAdmin(rs.getBoolean("admin"));
		}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return bean;
	}
	
	
	public synchronized boolean doDelete(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteProduct = "DELETE FROM " + TABLE_NAME + " WHERE username=?";

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteProduct);
		preparedStatement.setString(1, username);

		result = preparedStatement.executeUpdate();

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return (result != 0);
	}
	
	
	public synchronized Collection<UserBean> doRetrieveAll() throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<UserBean> utenti = new ArrayList<UserBean>();

		String query = "SELECT * FROM " + TABLE_NAME;

		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(query);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				UserBean bean = new UserBean();

				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setAdmin(rs.getBoolean("admin"));
				utenti.add(bean);
			}

		if (preparedStatement != null) preparedStatement.close();
		if (connection != null) connection.close();
		return utenti;
	}
}
