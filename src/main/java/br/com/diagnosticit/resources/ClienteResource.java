/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources;

import br.com.diagnosticit.resources.domain.Cliente;
import br.com.diagnosticit.resources.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> buscar( @PathVariable Long id ) throws Throwable{
        Cliente cliente = clienteService.buscar( id );
        return ResponseEntity.ok().body( cliente );
    }
    
}
