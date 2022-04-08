package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_categorias")
public class Categoria {

	@Id
	@Column(name="idcategoria")
	private int categoria;
	
	@Column(name="descripcion")
	private String descripcion;

	@Override
	public String toString() {
		return "Categoria [categoria=" + categoria + ", descripcion=" + descripcion + "]";
	}

	public Categoria(int categoria, String descripcion) {
		super();
		this.categoria = categoria;
		this.descripcion = descripcion;
	}
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
