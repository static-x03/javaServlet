package web;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import datos.*;
import dominio.Cliente;

@SuppressWarnings("serial")
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("action");
		if (accion != null) {
			switch (accion) {
			case "editar":
				this.editarCliente(request, response);
				break;
				
			case "borrar":
				this.borrarCliente(request, response);
				break;

			default:
				this.accionDefault(request, response);
				break;
			}
		} else {
			this.accionDefault(request, response);
		}

	}

	private void accionDefault(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IClienteDAOImpl clienteImp = new ClienteDaoJDBC();
		List<Cliente> clientes = clienteImp.all();
		System.out.println("clientes :" + clientes);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("clientes", clientes);
		sesion.setAttribute("totalClientes", clientes.size());
		sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
		// alcance de la informacion solo en la sesion !!!
		// request.getRequestDispatcher("clientes.jsp").forward(request, response);
		response.sendRedirect("clientes.jsp");
	}

	private double calcularSaldoTotal(List<Cliente> clientes) {
		double saldoClientes = 0;
		for (Cliente cliente : clientes) {
			saldoClientes += cliente.getSaldo();
		}
		return saldoClientes;
	}
	
	private void editarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//recuperar id cliente
		int idcliente = Integer.parseInt(request.getParameter("idcliente"));
		IClienteDAOImpl clienteImp = new ClienteDaoJDBC();
		Cliente cliente = clienteImp.find(new Cliente(idcliente));
		request.setAttribute("cliente", cliente);
		String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("action");
		if (accion != null) {
			switch (accion) {
			case "insertar":
				this.insertarCliente(request, response);
				break;
				
			case "modificar":
				this.modificarCliente(request, response);
				break;
				
			default:
				this.accionDefault(request, response);
				break;
			}
		} else {
			this.accionDefault(request, response);
		}
	}

	private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperar los valores del formulario Agregar Cliente
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		double saldo = 0;
		String saldoString = request.getParameter("saldo");

		if (saldoString != null && !"".equals(saldoString)) {
			saldo = Double.parseDouble(saldoString);
		}

		// se crea el obj cliente (modelo)
		Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);

		// insercion en base de datos
		IClienteDAOImpl clienteImp = new ClienteDaoJDBC();
		int registrosModificados = clienteImp.insert(cliente);
		System.out.println("registros Insertados : " + registrosModificados);

		// redireccion accion por default
		this.accionDefault(request, response);
	}
	
	private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// recuperar los valores del formulario Agregar Cliente
		
				int idCliente = Integer.parseInt(request.getParameter("idcliente"));
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String telefono = request.getParameter("telefono");
				double saldo = 0;
				String saldoString = request.getParameter("saldo");

				if (saldoString != null && !"".equals(saldoString)) {
					saldo = Double.parseDouble(saldoString);
				}

				// se crea el obj cliente (modelo)
				Cliente cliente = new Cliente(idCliente,nombre, apellido, email, telefono, saldo);

				// insercion en base de datos
				IClienteDAOImpl clienteImp = new ClienteDaoJDBC();
				int registrosActualizados = clienteImp.update(cliente);
				System.out.println("registros Actualizados : " + registrosActualizados);

				// redireccion accion por default
				this.accionDefault(request, response);
	}
	
	private void borrarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int idCliente = Integer.parseInt(request.getParameter("idcliente"));
		IClienteDAOImpl clienteImp = new ClienteDaoJDBC();
		Cliente cliente = new Cliente(idCliente);
		int registroEliminados = clienteImp.delete(cliente);
		System.out.println("clientes eliminados : "+ registroEliminados); 
		
		this.accionDefault(request, response);
	}
	
}	