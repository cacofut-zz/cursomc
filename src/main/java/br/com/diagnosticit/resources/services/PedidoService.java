/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services;

import br.com.diagnosticit.resources.domain.Pedido;
import br.com.diagnosticit.resources.repositories.PedidoRepository;
import br.com.diagnosticit.resources.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristianoca
 */
@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repository;
    
    public Pedido buscar( Long id ) throws Throwable{
        Optional<Pedido> opcional = repository.findById( id );
        return opcional.orElseThrow( () -> {
            throw new ObjectNotFoundException( "Objeto não encontrado: Id: " + id + ", Tipo: " + Pedido.class.getName() );
        });
    }
}
