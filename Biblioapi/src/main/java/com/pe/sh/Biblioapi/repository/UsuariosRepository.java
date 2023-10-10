/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.repository;

import com.pe.sh.Biblioapi.model.Usuarios;
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
public interface UsuariosRepository extends JpaRepository<Usuarios, String>{
    
    public Optional<Usuarios> findByUsername(String username);
    
    public Optional<Usuarios> findByUsernameOrEmail(String username, String email);
    
    public Boolean existsByUsername(String username);
    
    @Query(value = "select * from Usuarios where upper(codigous) like upper('%'||:search||'%') or upper(username) like upper('%'||:search||'%') or upper(email) like upper('%'||:search||'%')",
            countQuery = "select count(*) from Usuarios where upper(codigous) like upper('%'||:search||'%') or upper(username) like upper('%'||:search||'%') or upper(email) like upper('%'||:search||'%')",
            nativeQuery = true)
    public Page<Usuarios> buscarUsuariosPorCoincidencia(@Param("search") String search, Pageable pageable);
    
}
