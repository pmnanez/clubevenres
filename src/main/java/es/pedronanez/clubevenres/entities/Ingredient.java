package es.pedronanez.clubevenres.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	private Integer id;
	@Column
	private String name;
	@Column
	private Double ingredientWithPrice;
	@Column
	private Boolean sauce;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public Boolean isSauce() {
		return sauce;
	}
	public Double getPrice() {
		return ingredientWithPrice;
	}

	/**
	 * @return the ingredientWithPrice
	 */
	public Double getIngredientWithPrice() {
		return ingredientWithPrice;
	}

	/**
	 * @param ingredientWithPrice the ingredientWithPrice to set
	 */
	public void setIngredientWithPrice(Double ingredientWithPrice) {
		this.ingredientWithPrice = ingredientWithPrice;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param sauce the sauce to set
	 */
	public void setSauce(Boolean sauce) {
		this.sauce = sauce;
	}

}
