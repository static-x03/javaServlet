package datos;

import java.sql.*;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connexion {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowpublicKeyRetrieval=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "admin";
	private static BasicDataSource datasource;
	
	public static DataSource getDataSource() {
		if(datasource == null) {
			datasource = new BasicDataSource();
			datasource.setUrl(JDBC_URL);
			datasource.setUsername(JDBC_USER);
			datasource.setPassword(JDBC_PASSWORD);
			datasource.setInitialSize(50);			
		}
		return datasource;
	}
	
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(Connection cn) {
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
}
