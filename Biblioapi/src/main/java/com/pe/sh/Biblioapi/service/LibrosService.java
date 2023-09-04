/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.dto.LibrosDto;

/**
 *
 * @author shmen
 */
public interface LibrosService extends GenericService<LibrosDto>{
    
    public LibrosDto create(LibrosDto dto, String codigoedi);
    
    public LibrosDto update(LibrosDto dto, String id, String codigoedi);
    
    public LibrosDto addAutores(String id, String codigoaut);
    
    public LibrosDto removeAutores(String id, String codigoaut);
    
    public LibrosDto addGeneros(String id, String codigogen);
    
    public LibrosDto removeGeneros(String id, String codigogen);
    
}
