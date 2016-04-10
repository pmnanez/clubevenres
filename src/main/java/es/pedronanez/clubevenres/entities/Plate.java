package es.pedronanez.clubevenres.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="plate")
public class Plate {
	
	@Id
	private Integer id;
	
	/**
	 * Name of the plate
	 */
	@Column
	private String name;
	/**
	 * Indentifies the main ingredient of this plate. This ingredient cannot be removed by the diner.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Ingredient baseIngredient;
	
	/**
	 * All the other ingredients. Any of this ingredients can be removed by the diner in the order page.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="plate_ingredients")
	private List<Ingredient> ingredients;
	
	/**
	 * Price
	 */
	@Column
	private Double price;
	
	/**
	 * Indicates if this plate is a complement. e.g. french fries.
	 */
	@Column
	private Boolean complement;
	
	public void setComplementos(List<Ingredient> complementos) {
		this.ingredients = complementos;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setIngredienteBase(Ingredient ingredienteBase) {
		this.baseIngredient = ingredienteBase;
	}
	
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	
	public void setPrecio(Double precio) {
		this.price = precio;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return name;
	}
	
	public Ingredient getIngredienteBase() {
		return baseIngredient;
	}
	
	public List<Ingredient> getComplementos() {
		return ingredients;
	}
	
	public Double getPrecio() {
		return price;
	}

}
