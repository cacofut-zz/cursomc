/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services;

import br.com.diagnosticit.resources.domain.Categoria;
import br.com.diagnosticit.resources.repositories.CategoriaRepository;
import br.com.diagnosticit.resources.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristianoca
 */
@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repositoty;
    
    public Categoria buscar( Long id ) throws Throwable{
        Optional<Categoria> optional = repositoty.findById( id );
        return optional.orElseThrow( () -> {
            throw new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName() );
        });
    }
    
}
