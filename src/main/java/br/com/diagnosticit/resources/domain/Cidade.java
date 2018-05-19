/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author cristianoca
 */
@Entity
public class Cidade {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String nome;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn( name = "estado_id" )
    private Estado estado;

    public Cidade() {
    }   
    
    public Cidade(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

        
    public Cidade(Long id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }        

    public Cidade(String são_Paulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
