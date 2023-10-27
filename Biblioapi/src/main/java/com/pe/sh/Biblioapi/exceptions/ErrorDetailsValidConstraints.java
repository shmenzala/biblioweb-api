/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.exceptions;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author shmen
 */
public class ErrorDetailsValidConstraints extends ErrorFields{
    
    private Map<String, String> validConstraintErrors;

    public ErrorDetailsValidConstraints(Date marcaDeTiempo, Map<String, String> validConstraintErrors, String detalles) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.validConstraintErrors = validConstraintErrors;
        this.detalles = detalles;
    }

    public Map<String, String> getValidConstraintErrors() {
        return validConstraintErrors;
    }

    public void setValidConstraintErrors(Map<String, String> validConstraintErrors) {
        this.validConstraintErrors = validConstraintErrors;
    }

}
