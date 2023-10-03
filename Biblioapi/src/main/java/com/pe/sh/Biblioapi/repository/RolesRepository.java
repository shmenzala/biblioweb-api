/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Roles;
import java.util.Optional;
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
public interface RolesRepository extends JpaRepository<Roles, String>{
 
    public Optional<Roles> findByNombre(String nombre);
    
    @Query(value = "select * from Roles where upper(codigorol) like upper('%'||:search||'%') or upper(nombre) like upper('%'||:search||'%')",
            countQuery = "select count(*) from Roles where upper(codigorol) like upper('%'||:search||'%') or upper(nombre) like upper('%'||:search||'%')",
            nativeQuery = true)
    public Page<Roles> buscarRolesPorCoincidencia(@Param("search") String search, Pageable pageable);
    
}
