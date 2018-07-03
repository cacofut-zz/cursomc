/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources;

import br.com.diagnosticit.dto.CategoriaDTO;
import br.com.diagnosticit.resources.domain.Categoria;
import br.com.diagnosticit.resources.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author cristianoca
 */
@RestController
@RequestMapping(value="/categorias" )
public class CategoriaResource {
    
    @Autowired
    private CategoriaService service;
    
    @RequestMapping( value = "/{id}", method=RequestMethod.GET )
    public ResponseEntity<?> buscar( @PathVariable long id ) throws Throwable{        
        Categoria c1 = service.find( id );        
        return ResponseEntity.ok().body( c1 );
        
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert( @Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = service.fromCategoriaDTO(categoriaDTO);
        categoria = service.insert( categoria );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update( @Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable long id ) throws Throwable{
        Categoria categoria = service.fromCategoriaDTO(categoriaDTO);
        categoria.setId(id);
        service.update(categoria);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete( @PathVariable long id ) throws Throwable{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){        
        List<CategoriaDTO> list = service.findAll().stream()
                .map( cat -> new CategoriaDTO(cat)).collect( Collectors.toList());
        return ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page",         defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy",      defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction",    defaultValue = "ASC") String direction ){
        Page<Categoria> list = service.findPage( page, linesPerPage, orderBy, direction );
        Page<CategoriaDTO> listDTO = list.map( obj -> new CategoriaDTO(obj) );
        return ResponseEntity.ok().body(listDTO);
    }
    
}
