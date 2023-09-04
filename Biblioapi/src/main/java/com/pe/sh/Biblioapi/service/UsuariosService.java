/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.dto.UsuariosDto;

/**
 *
 * @author shmen
 */
public interface UsuariosService extends GenericService<UsuariosDto>{
    
    public UsuariosDto create(UsuariosDto dto, String codigorol);
    
    public UsuariosDto addRoles(String id, String codigorol);
    
    public UsuariosDto removeRoles(String id, String codigorol);
    
    public UsuariosDto addLibrosFavoritos(String id, String codigolib);
    
    public UsuariosDto removeLibrosFavoritos(String id, String codigolib);
    
}
