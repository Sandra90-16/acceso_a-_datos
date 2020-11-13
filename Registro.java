package AccesoFicherosAleatorios;

import java.io.Serializable;

public class Registro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int referencia;
	private String descripcion;
	private float precio;
	boolean borrado;
	
	public Registro(int referencia, String descripcion, float precio, boolean borrado) {
		super();
		this.referencia = referencia;
		this.descripcion = descripcion;
		this.precio = precio;
		this.borrado = borrado;
	}
	public int getReferencia() {
		return referencia;
	}
	public Registro() {
	}
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return referencia + " " + descripcion +" "+ precio +" " + borrado;
	}
	
	
}
