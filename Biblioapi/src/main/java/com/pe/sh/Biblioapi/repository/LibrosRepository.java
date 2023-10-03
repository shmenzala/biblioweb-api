/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Libros;
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
public interface LibrosRepository extends JpaRepository<Libros, String> {

    @Query(value = """
                   select l.*, a.NOMBRES from libros l
                   inner join libros_autores lb on l.codigolib = lb.codigolib
                   inner join autores a on lb.codigoaut = a.codigoaut
                   where upper(l.codigolib) like upper('%'||:search||'%') or upper(l.nombre) like upper('%'||:search||'%') or upper(a.nombres) like upper ('%'||:search||'%')""",
            countQuery = """
                   select count(*) from libros l
                   inner join libros_autores lb on l.codigolib = lb.codigolib
                   inner join autores a on lb.codigoaut = a.codigoaut
                   where upper(l.codigolib) like upper('%'||:search||'%') or upper(l.nombre) like upper('%'||:search||'%') or upper(a.nombres) like upper ('%'||:search||'%')""",
            nativeQuery = true)
    public Page<Libros> buscarLibrosPorCoincidencia(@Param("search") String search, Pageable pageable);

}
