/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services;

import br.com.diagnosticit.dto.ClienteDTO;
import br.com.diagnosticit.resources.domain.Cliente;
import br.com.diagnosticit.resources.domain.enums.TipoCliente;
import br.com.diagnosticit.resources.repositories.ClienteRepository;
import br.com.diagnosticit.resources.services.exceptions.IntegrityViolationException;
import br.com.diagnosticit.resources.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristiano
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente find( Long id ) throws Throwable{
        Optional<Cliente> optional = clienteRepository.findById( id );
        return optional.orElseThrow( () ->{
            throw new ObjectNotFoundException( "Objeto não encontrado: Id: " + id + ", Tipo: " + Cliente.class.getName());
        });        
    }

    public Cliente fromClienteDTO(ClienteDTO objDTO) {
        return new Cliente( objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null );
    }

    public Cliente update(Cliente obj) throws Throwable {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }
    
    private void updateData( Cliente newObj, Cliente obj ){
        newObj.setNome (obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(long id) throws Throwable {
        find( id );
        try{
            clienteRepository.deleteById(id);
        }
        catch( DataIntegrityViolationException ex ){
            throw new IntegrityViolationException("Não pode remover o cliente por que há relacionamentos");
        }
    }
    
    
}
