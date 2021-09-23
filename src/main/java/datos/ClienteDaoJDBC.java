package datos;

import java.sql.*;
import java.util.*;

import dominio.Cliente;

public class ClienteDaoJDBC implements IClienteDAOImpl{

	private static final String SQL_SELECT ="SELECT * FROM cliente";
	private static final String SQL_SELECT_ID ="SELECT * FROM cliente WHERE idcliente = ?";
	private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) VALUES(?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE idcliente=?";
	private static final String SQL_DELETE = "DELETE FROM cliente WHERE idcliente=?";
	
	public List<Cliente> all(){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = null;
		List<Cliente> clientes = new ArrayList<>();
		
		try {
			
			con = Connexion.getConnection();
			ps = con.prepareStatement(SQL_SELECT);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				var idCliente = rs.getInt(1);
				var nombre = rs.getString(2);
				var apellido = rs.getString(3);
				var email = rs.getString(4);
				var telefono = rs.getString(5);
				var saldo = rs.getDouble(6);
				
				cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
				clientes.add(cliente);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {			
			Connexion.close(rs);
			Connexion.close(ps);
			Connexion.close(con);
		}
		return clientes;	
	}
	
	public Cliente find(Cliente cliente) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connexion.getConnection();
			ps = con.prepareStatement(SQL_SELECT_ID);
			ps.setInt(1, cliente.getIdcliente());
			rs = ps.executeQuery();
			rs.absolute(1);
			
				var nombre = rs.getString(2);
				var apellido = rs.getString(3);
				var email = rs.getString(4);
				var telefono = rs.getString(5);
				var saldo = rs.getDouble(6);
				
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setEmail(email);
				cliente.setTelefono(telefono);
				cliente.setSaldo(saldo);
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {			
			Connexion.close(rs);
			Connexion.close(ps);
			Connexion.close(con);
		}
		return cliente;
	}
	
	public int insert(Cliente cliente) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0;
		
		try {
			
			con = Connexion.getConnection();
			ps = con.prepareStatement(SQL_INSERT);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefono());
			ps.setDouble(5, cliente.getSaldo());
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {			
			Connexion.close(ps);
			Connexion.close(con);
		}
		return rows;
	}
	
	public int update(Cliente cliente) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0;
		
		try {
			
			con = Connexion.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefono());
			ps.setDouble(5, cliente.getSaldo());
			ps.setInt(6, cliente.getIdcliente());
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {			
			Connexion.close(ps);
			Connexion.close(con);
		}
		return rows;
	}

	public int delete(Cliente cliente) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0;
		
		try {
			
			con = Connexion.getConnection();
			ps = con.prepareStatement(SQL_DELETE);			
			ps.setInt(1, cliente.getIdcliente());
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {			
			Connexion.close(ps);
			Connexion.close(con);
		}
		return rows;

	}
}
