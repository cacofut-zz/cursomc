/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources;

import br.com.diagnosticit.dto.ClienteDTO;
import br.com.diagnosticit.dto.ClienteNewDTO;
import br.com.diagnosticit.resources.domain.Cliente;
import br.com.diagnosticit.resources.services.ClienteService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author cristiano
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService clienteService;
    
    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find( @PathVariable Long id ) throws Throwable{
        Cliente cliente = clienteService.find( id );
        return ResponseEntity.ok().body( cliente );
    }
    
    /**
     * 
     * @param objDTO
     * @param id
     * @return
     * @throws Throwable 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update( @Valid @RequestBody ClienteDTO objDTO, 
            @PathVariable long id ) throws Throwable{
        objDTO.setId(id);
        Cliente obj = clienteService.fromClienteDTO( objDTO );
        obj = clienteService.update( obj );
        return ResponseEntity.noContent().build();
    }
    
    /**
     * 
     * @param objDTO
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert( @RequestBody ClienteNewDTO objDTO ){
        Cliente obj = clienteService.fromClienteDTO(objDTO);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws Throwable 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete( @PathVariable long id ) throws Throwable{
        clienteService.delete( id );
        return ResponseEntity.noContent().build();
    }
    
}
