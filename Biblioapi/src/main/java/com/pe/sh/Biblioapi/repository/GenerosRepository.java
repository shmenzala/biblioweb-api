/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Generos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shmen
 */
@Repository
public interface GenerosRepository extends JpaRepository<Generos, String>{
    
}
