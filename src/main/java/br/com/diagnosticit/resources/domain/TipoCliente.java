/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.domain;

/**
 *
 * @author cristianoca
 */
public enum TipoCliente {
    
    PESSOA_FISICA,
    PESSOA_JURIDICA;
    
    private int codigo;
    private String nome;

    private TipoCliente(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    
    
}
