package main.com.planon.facilitymanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	private static String userName = "l";
	private static String password = "l";
	private static String url = "jdbc:sqlserver://PC09362;instanceName=SQLEXPRESS";
	private static Connection connection;

	/**
	 * method Connection() creates a connection using Driver Manager by passing URL
	 * for JDBC SQL server and username and password.
	 * 
	 * @return connection, gets the connection from Driver Manager.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection openConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		connection = DriverManager.getConnection(url, userName, password);
		connection.setAutoCommit(false);
		return connection;

	}

	/**
	 * method closeConnection() closes the connection for
	 * resultset,preparedstatement and connection.
	 * 
	 * @param resultSet,         a resultSet object maintains a cursor pointing to
	 *                           its current row of data.
	 * @param preparedStatement, an object that represents a precompiled SQL
	 *                           statement.
	 * @param connection,        a connection (session) with a specificdatabase.
	 * @throws SQLException, retrieves the exception chained to this SQLException
	 *                       object.
	 */
	public static void closeConnection(ResultSet aResultSet, PreparedStatement aPreparedStatement,
			Connection aConnection) throws SQLException {

		if (aResultSet != null) {
			aResultSet.close();
		}
		if (aPreparedStatement != null) {
			aPreparedStatement.close();
		}

		if (aConnection != null) {
			aConnection.close();
		}
	}
}
