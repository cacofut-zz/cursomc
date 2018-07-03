/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services;

import br.com.diagnosticit.dto.CategoriaDTO;
import br.com.diagnosticit.resources.domain.Categoria;
import br.com.diagnosticit.resources.repositories.CategoriaRepository;
import br.com.diagnosticit.resources.services.exceptions.IntegrityViolationException;
import br.com.diagnosticit.resources.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristianoca
 */
@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repositoty;
    
    public Categoria find( Long id ) throws Throwable{
        Optional<Categoria> optional = repositoty.findById( id );
        return optional.orElseThrow( () -> {
            throw new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName() );
        });
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId( null );
        return repositoty.save( categoria );
    }

    public void update(Categoria categoria) throws Throwable {
        find(categoria.getId());
        repositoty.save(categoria);
    }

    public void delete( Long id ) throws Throwable {
        find(id);
        try{
            repositoty.deleteById(id);
        }
        catch (DataIntegrityViolationException ex) {
            throw new IntegrityViolationException("Não pode remover uma categoria que contenha produtos!");
        }
    }

    public List<Categoria> findAll() {
        return repositoty.findAll();
    }
    
    public Page<Categoria> findPage( Integer page, Integer linesPerPage, String orderBy, String direction ){
        PageRequest pageRequest = PageRequest.of( page, linesPerPage, Sort.Direction.valueOf(direction), orderBy );
        return repositoty.findAll(pageRequest);
    }
    
}
