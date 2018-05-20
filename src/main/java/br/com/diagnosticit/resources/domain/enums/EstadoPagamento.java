/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.domain.enums;

/**
 *
 * @author cristiano
 */
public enum EstadoPagamento {
 
    PENDENTE (1, "Pendente"),
    QUITADO  (2, "Quitado"),
    CANCELADO(3, "Cancelado");
    
    private final Integer codigo;
    private final String descricao;

    private EstadoPagamento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
        
    public static EstadoPagamento toEnum( Integer codigo ){
        
        if( codigo == null ){
            return null;
        }
        
        for( EstadoPagamento estado :  EstadoPagamento.values()){
            if( codigo.equals( estado.getCodigo())){
                return estado;
            }
        }
        
        throw new IllegalArgumentException( "Id inválido " + codigo );
    }
    
}
