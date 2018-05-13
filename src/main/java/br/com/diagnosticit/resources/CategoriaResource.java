/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources;

import br.com.diagnosticit.resources.domain.Categoria;
import java.util.ArrayList;
import java.util.List;
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
    
    @RequestMapping(method=RequestMethod.GET)
    public List<Categoria> listar(){
        
        Categoria c1 = new Categoria(1L, "informática");
        Categoria c2 = new Categoria(2L, "escritório");
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(c1);
        categorias.add(c2);
        return categorias;
        
    }
}
