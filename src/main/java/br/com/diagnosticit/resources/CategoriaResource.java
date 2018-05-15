/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources;

import br.com.diagnosticit.resources.domain.Categoria;
import br.com.diagnosticit.resources.services.CategoriaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cristianoca
 */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaService service;
    
    @RequestMapping(method=RequestMethod.GET)
    public List<Categoria> listar(){
        
        Categoria c1 = service.buscar( 1L );
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(c1);

        return categorias;
        
    }
}
