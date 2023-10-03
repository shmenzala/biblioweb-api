/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Autores;
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
public interface AutoresRepository extends JpaRepository<Autores, String>{
    
    @Query(value = "select * from Autores where upper(codigoaut) like upper('%'||:search||'%') or upper(nombres) like upper('%'||:search||'%') or upper(apellidos) like upper('%'||:search||'%') or upper(fech_nacimiento) like upper('%'||:search||'%')",
            countQuery = "select count(*) from Autores where upper(codigoaut) like upper('%'||:search||'%') or upper(nombres) like upper('%'||:search||'%') or upper(apellidos) like upper('%'||:search||'%') or upper(fech_nacimiento) like upper('%'||:search||'%')",
            nativeQuery = true)
        public Page<Autores> buscarAutoresPorCoincidencia(@Param("search") String search, Pageable pageable);
    
}
