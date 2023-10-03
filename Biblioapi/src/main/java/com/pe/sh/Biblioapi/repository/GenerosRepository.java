/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Generos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shmen
 */
@Repository
public interface GenerosRepository extends JpaRepository<Generos, String>{
    
    @Query(value = "select * from Generos where upper(codigogen) like upper('%'||:search||'%') or upper(nombre) like upper('%'||:search||'%')",
            countQuery = "select count(*) from Generos where upper(codigogen) like upper('%'||:search||'%') or upper(nombre) like upper('%'||:search||'%')",
            nativeQuery = true)
    public Page<Generos> buscarGenerosPorCoincidencia(@Param("search") String search, Pageable pageable);
    
}
