/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Personas_perfil;
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
public interface Personas_perfilRepository extends JpaRepository<Personas_perfil, String>{
    
    @Query(value = "select * from Personas_perfil where upper(codigope) like upper('%'||:search||'%') or upper(nombres) like upper('%'||:search||'%') or upper(apellidos) like upper('%'||:search||'%') or upper(genero) like upper('%'||:search||'%') or upper(fech_nacimiento) like upper('%'||:search||'%')",
            countQuery = "select count(*) from Personas_perfil where upper(codigope) like upper('%'||:search||'%') or upper(nombres) like upper('%'||:search||'%') or upper(apellidos) like upper('%'||:search||'%') or upper(genero) like upper('%'||:search||'%') or upper(fech_nacimiento) like upper('%'||:search||'%')",
            nativeQuery = true)
    public Page<Personas_perfil> buscarPersonas_perfilPorCoincidencia(@Param("search") String search, Pageable pageable);
    
}
