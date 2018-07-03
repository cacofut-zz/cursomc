/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.dto;

import br.com.diagnosticit.resources.domain.Categoria;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 *
 * @author cristiano
 */
public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(min = 5, max = 80, message = "O tamanho deve estar entre 5 e 80")
    private String nome;

    public CategoriaDTO() {
    }
    
    public CategoriaDTO( Categoria categoria ){
        id = categoria.getId();
        nome = categoria.getNome();
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
            
    
}
