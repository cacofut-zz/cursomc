/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.domain;

import br.com.diagnosticit.resources.domain.enums.EstadoPagamento;
import javax.persistence.Entity;





/**
 *
 * @author cristiano
 */
@Entity
public class PagamentoComCartao extends Pagamento{
    
    private static final long serialVersionUID = 1L;
    
    private Integer numeroParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Long id, Integer numeroParcelas, EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }        

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
    
    
}
