/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.model;

import com.pe.sh.Biblioapi.configuration.StringKeyGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author shmen
 */
@Entity
@Table(name = "PERSONAS_PERFIL")
public class Personas_perfil implements Serializable{
    
    @Id
    @Column(name = "codigope")
    @GeneratedValue(generator = "inc_seqPpl")
    @GenericGenerator(name = "inc_seqPpl", type = StringKeyGenerator.class,
            parameters = {@Parameter(name = "sqcName", value = "PERSONAS_PERFIL_SEQ"),
                          @Parameter(name = "identificator_id", value = "PP")})
    @SequenceGenerator(name = "inc_seqPpl", sequenceName = "PERSONAS_PERFIL_SEQ", initialValue = 1, allocationSize = 1)
    private String id;
    
    @Column(name = "nombres")
    private String nombres;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "genero")
    private String genero;
    
    @Column(name = "fech_nacimiento")
    private String fech_nacimiento;
    
    @Column(name = "fotografia")
    private String fotografia;

    public Personas_perfil() {
    }

    public Personas_perfil(String id, String nombres, String apellidos, String genero, String fech_nacimiento, String fotografia) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fech_nacimiento = fech_nacimiento;
        this.fotografia = fotografia;
    }

    public String getCodigope() {
        return id;
    }

    public void setCodigope(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFech_nacimiento() {
        return fech_nacimiento;
    }

    public void setFech_nacimiento(String fech_nacimiento) {
        this.fech_nacimiento = fech_nacimiento;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
    
    
    
}
