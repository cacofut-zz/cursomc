/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services;

import br.com.diagnosticit.resources.domain.Cliente;
import br.com.diagnosticit.resources.repositories.ClienteRepository;
import br.com.diagnosticit.resources.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristiano
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente buscar( Long id ) throws Throwable{
        Optional<Cliente> optional = clienteRepository.findById( id );
        return optional.orElseThrow( () ->{
            throw new ObjectNotFoundException( "Objeto não encontrado: Id: " + id + ", Tipo: " + Cliente.class.getName());
        });
        
    }
}
