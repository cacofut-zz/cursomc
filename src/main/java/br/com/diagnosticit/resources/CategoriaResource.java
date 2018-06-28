/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources;

import br.com.diagnosticit.resources.domain.Categoria;
import br.com.diagnosticit.resources.services.CategoriaService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
 * @author cristianoca
 */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaService service;
    
    @RequestMapping( value = "/{id}", method=RequestMethod.GET )
    public ResponseEntity<?> buscar( @PathVariable long id ) throws Throwable{        
        Categoria c1 = service.buscar( id );        
        return ResponseEntity.ok().body( c1 );
        
    }
    
    @RequestMapping( method = RequestMethod.PUT )
    public ResponseEntity<Void> insert( @RequestBody Categoria obj ){
        obj = service.insert( obj );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();        
        return ResponseEntity.created(uri).build();
    }
    
}
