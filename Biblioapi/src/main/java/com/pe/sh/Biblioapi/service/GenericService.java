/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import java.util.List;

/**
 *
 * @author shmen
 * @param <E>
 */
public interface GenericService<E> {

    public E create(E dto);

    public List<E> findAll();

    public E findById(String id);

    public E update(E dto, String id);

    public void delete(String id);
    
    public PageableDataDto findAllPagination(int pageNo, int pageSize, String orderBy, String sortDir);

}
