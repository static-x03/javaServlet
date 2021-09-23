package datos;

import java.util.*;

import dominio.Cliente;

public interface IClienteDAOImpl {
	
	public List<Cliente> all();
	public Cliente find(Cliente cliente);
	public int insert(Cliente cliente);
	public int update(Cliente cliente);
	public int delete(Cliente cliente);

}
