/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Editoriales;
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
public interface EditorialesRepository extends JpaRepository<Editoriales, String> {

    @Query(value = "select * from Editoriales where upper(codigoedi) like upper('%'||:search||'%') or upper(nombre) like upper('%'||:search||'%')",
            countQuery = "select count(*) from Editoriales where upper(codigoedi) like upper('%'||:search||'%') or upper(nombre) like upper('%'||:search||'%')",
            nativeQuery = true)
    public Page<Editoriales> buscarEditorialPorCoincidencia(@Param("search") String search, Pageable pageable);
    //public Page<Editoriales> findAllByIdContainingIgnoreCaseOrNombreContainingIgnoreCase(String search, String search2, Pageable pageable);

}
