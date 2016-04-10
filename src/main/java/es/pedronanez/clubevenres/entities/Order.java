package es.pedronanez.clubevenres.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents an order an all of its individual orders. This is the final order
 * to be asked to the restaurant.
 * 
 * @author Pedro
 */
@Entity
@Table(name = "todayorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Date of this order
     */
    @Column
    private Calendar date;

    /**
     * List of diners
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_diners")
    private List<Diner> diners;

    /**
     * Indicate if this order is active or closed.
     */
    @Column(nullable=false)
    private boolean active;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * @return the diners
     */
    public List<Diner> getDiners() {
        return diners;
    }

    /**
     * @param diners
     *            the diners to set
     */
    public void setDiners(List<Diner> diners) {
        this.diners = diners;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return active;
    }

}
