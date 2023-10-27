/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.exceptions;

import java.util.Date;

/**
 *
 * @author shmen
 */
public class ErrorFields {
    
    protected Date marcaDeTiempo;
    protected String detalles;

    public ErrorFields() {
    }

    public Date getMarcaDeTiempo() {
        return marcaDeTiempo;
    }

    public void setMarcaDeTiempo(Date marcaDeTiempo) {
        this.marcaDeTiempo = marcaDeTiempo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

}
