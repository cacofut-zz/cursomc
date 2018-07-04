/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services;

import br.com.diagnosticit.dto.ClienteDTO;
import br.com.diagnosticit.dto.ClienteNewDTO;
import br.com.diagnosticit.resources.domain.Cidade;
import br.com.diagnosticit.resources.domain.Cliente;
import br.com.diagnosticit.resources.domain.Endereco;
import br.com.diagnosticit.resources.domain.enums.TipoCliente;
import br.com.diagnosticit.resources.repositories.ClienteRepository;
import br.com.diagnosticit.resources.repositories.EnderecoRepository;
import br.com.diagnosticit.resources.services.exceptions.IntegrityViolationException;
import br.com.diagnosticit.resources.services.exceptions.ObjectNotFoundException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cristiano
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    public Cliente find( Long id ) throws Throwable{
        Optional<Cliente> optional = clienteRepository.findById( id );
        return optional.orElseThrow( () ->{
            throw new ObjectNotFoundException( "Objeto não encontrado: Id: " + id + ", Tipo: " + Cliente.class.getName());
        });        
    }

    /**
     * 
     * @param objDTO
     * @return 
     */
    public Cliente fromClienteDTO(ClienteDTO objDTO) {
        return new Cliente( objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null );
    }
    
    /**
     * 
     * @param objDTO
     * @return 
     */
    public Cliente fromClienteDTO( ClienteNewDTO objDTO ) {
        Cliente cliente = new Cliente( objDTO.getNome(), objDTO.getEmail(), 
                objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
        
        Set<String> telefones = cliente.getTelefones();
        String tel1 = objDTO.getTelefone1();
        String tel2 = objDTO.getTelefone2();
        String tel3 = objDTO.getTelefone3();
        
        telefones.add(tel1);
        if( tel2 != null )
            telefones.add(tel2);
        if( tel3 != null )
            telefones.add(tel3);
                
        Cidade cidade = new Cidade( objDTO.getCidadeId(), null, null);
        Endereco endereco1 = new Endereco( objDTO.getLogradouro(), 
                objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), 
                objDTO.getCep(), cliente, cidade );
        
        cliente.setEnderecos(Arrays.asList( endereco1 ));
        
        return cliente;
    }

    /**
     * 
     * @param obj
     * @return
     * @throws Throwable 
     */
    public Cliente update(Cliente obj) throws Throwable {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }
    
    /**
     * 
     * @param newObj
     * @param obj 
     */
    private void updateData( Cliente newObj, Cliente obj ){
        newObj.setNome (obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    /**
     * 
     * @param id
     * @throws Throwable 
     */
    public void delete(long id) throws Throwable {
        find( id );
        try{
            clienteRepository.deleteById(id);
        }
        catch( DataIntegrityViolationException ex ){
            throw new IntegrityViolationException("Não pode remover o cliente por que há pedidos relacionados");
        }
    }

    /**
     * 
     * @param obj
     * @return 
     */
    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        clienteRepository.save(obj);
        enderecoRepository.saveAll( obj.getEnderecos() );
        return obj;
    }
    
    
}
