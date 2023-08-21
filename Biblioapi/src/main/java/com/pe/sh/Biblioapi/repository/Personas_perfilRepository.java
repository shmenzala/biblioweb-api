/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Personas_perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shmen
 */
@Repository
public interface Personas_perfilRepository extends JpaRepository<Personas_perfil, String>{
    
}
