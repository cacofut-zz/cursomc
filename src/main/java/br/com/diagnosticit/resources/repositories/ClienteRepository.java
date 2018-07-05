/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.repositories;

import br.com.diagnosticit.resources.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cristianoca
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{    
    
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
