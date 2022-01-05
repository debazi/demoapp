/**
 * 
 */
package com.debazi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author marc-assie
 */
@Entity
@Table(name = "customer")
public class Customer {

    /**
     * Identifiant technique du client.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Nom du client.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Age du client.
     */
    @Column(name = "age", nullable = true)
    private int age;

    /**
     * <code>TRUE</code> si le client est actif ou <code>FALSE</code> s'il est inactif.
     */
    @Column(name = "active")
    private boolean active;

    /**
     * Constructeur.
     */
    public Customer () {
        super();
    }

    /**
     * Constructeur.
     * @param name nom du client (requis);
     * @param age age du client (requis);
     */
    public Customer (String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * Retourne l'identifiant du client.
     * @return Identifiant du client (Jamais <code>NULL</code>)
     */
    public long getId () {
        return this.id;
    }

    /**
     * Change le nom du client.
     * @param name Nom du client (requis).
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Retourne le nom du client.
     * @return Nom du client (Jamais <code>NULL</code>)
     */
    public String getName () {
        return this.name;
    }

    /**
     * Change l'âge du client.
     * @param age Age du client (requis).
     */
    public void setAge (int age) {
        this.age = age;
    }

    /**
     * Retourne l'âge du client.
     * @return Age du client (<code>NULL</code>)
     */
    public int getAge () {
        return this.age;
    }

    /**
     * Retourne si le client est actif ou inactif.
     * @return <code>TRUE</code> si client actif <code>FALSE</code> si client inactif.
     */
    public boolean isActive () {
        return this.active;
    }

    /**
     * Change le client en actif ou inactif.
     * @param active active ou inactive (requise)
     */
    public void setActive (boolean active) {
        this.active = active;
    }

    @Override
    public String toString () {
        return "Customer [id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", active=" + this.active + "]";
    }

}
