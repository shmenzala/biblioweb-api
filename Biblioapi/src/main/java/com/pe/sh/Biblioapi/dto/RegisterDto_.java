/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author shmen
 */
public class RegisterDto_ {

    @NotEmpty(message = "El campo username no debe estar vacío")
    private String username;
    
    @NotEmpty(message = "El campo email no debe estar vacío")
    @Email(message = "El campo email tiene una dirección de correo electrónico con formato INCORRECTO" ,regexp = "(?i)^(([^<>()[\\\\]\\.,;:\\s@\\\\\"]+(\\.[^<>()[\\\\]\\.,;:\\s@\\\\\"]+)*)|(\\\\\".+\\\\\"))@(([^<>()[\\\\]\\.,;:\\s@\\\\\"]+\\.)+[^<>()[\\\\]\\.,;:\\s@\\\\\"]{2,})$")
    private String email;
    // dirección de correo electrónico con formato correcto 
    @NotEmpty(message = "El campo password no debe estar vacío")
    private String password;

    public RegisterDto_() {
    }

    public RegisterDto_(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
