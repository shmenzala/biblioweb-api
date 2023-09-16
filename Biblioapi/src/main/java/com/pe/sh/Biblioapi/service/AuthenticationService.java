/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.dto.JwtAuthResponseDto_;
import com.pe.sh.Biblioapi.dto.LoginDto_;
import com.pe.sh.Biblioapi.dto.RegisterDto_;

/**
 *
 * @author shmen
 */
public interface AuthenticationService {
    
    public JwtAuthResponseDto_ register(RegisterDto_ regDto);

    public JwtAuthResponseDto_ authenticate(LoginDto_ logDto);
    
}
