/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author shmen
 */
public class LoginDto_ {

    @NotEmpty(message = "El campo username no debe estar vacío")
    private String username;
    
    @NotEmpty(message = "El campo password no debe estar vacío")
    private String password;

    public LoginDto_() {
    }

    public LoginDto_(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
