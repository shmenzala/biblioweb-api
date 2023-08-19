/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author shmen
 */
@Entity
@Table(name = "GENEROS")
public class Generos implements Serializable{
    
    @Id
    @Column(name = "codigogen")
    private String id;
    
    @Column(name = "nombre")
    private String nombre;
    
    public Generos() {
    }

    public Generos(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getCodigogen() {
        return id;
    }

    public void setCodigogen(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
