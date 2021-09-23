package dominio;

public class Cliente {
	
	private int idcliente;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private Double saldo;
	
	public Cliente() {
	}
	
	public Cliente(int idcliente) {
		this.idcliente = idcliente;
	}
	
	public Cliente(String nombre, String apellido, String email, String telefono, Double saldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.saldo = saldo;
	}

	public Cliente(int idcliente, String nombre, String apellido, String email, String telefono, Double saldo) {
		this.idcliente = idcliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.saldo = saldo;
	}

	public int getIdcliente() {return idcliente;}
	public void setIdcliente(int idcliente) {this.idcliente = idcliente;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getApellido() {return apellido;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getTelefono() {return telefono;}
	public void setTelefono(String telefono) {this.telefono = telefono;}
	public Double getSaldo() {return saldo;}
	public void setSaldo(Double saldo) {this.saldo = saldo;}

	@Override
	public String toString() {
		return "Cliente [idcliente=" + idcliente + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", telefono=" + telefono + ", saldo=" + saldo + "]";
	}
	
}
