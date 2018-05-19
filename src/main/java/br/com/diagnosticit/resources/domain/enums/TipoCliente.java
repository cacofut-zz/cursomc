/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.domain.enums;

/**
 *
 * @author cristianoca
 */
public enum TipoCliente {
    
    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");
    
    private int codigo;
    private String nome;

    private TipoCliente(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public static TipoCliente toEnum( Integer codigo ){
        
        if( codigo == null )
            return null;
        
        for( TipoCliente tipo : TipoCliente.values() ){
            if( codigo.equals(tipo.getCodigo()))
                return tipo;
        }
        
        throw new IllegalArgumentException("Id inválido " + codigo );
    }
    
    
}
