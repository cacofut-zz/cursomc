/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services.validation;

import br.com.diagnosticit.dto.ClienteDTO;
import br.com.diagnosticit.dto.ClienteNewDTO;
import br.com.diagnosticit.resources.domain.Cliente;
import br.com.diagnosticit.resources.domain.enums.TipoCliente;
import br.com.diagnosticit.resources.exceptions.FieldMessage;
import br.com.diagnosticit.resources.repositories.ClienteRepository;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

/**
 *
 * @author cristianoca
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private HttpServletRequest request;
    
    /**
     * 
     * @param constraintAnnotation 
     */
    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
        
    }
       
    /**
     * 
     * @param objDTO
     * @param context
     * @return 
     */
    @Override
    public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
        
        List<FieldMessage> list = new ArrayList<>();        
        Map<String, String> map = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        
        Long id = Long.parseLong( map.get("id"));   
        Cliente cliente = clienteRepository.findByEmail( objDTO.getEmail() );
        
        if( cliente != null && cliente.getId() != id )
            list.add( new FieldMessage("email", "Email já cadastrado!"));
            
        for( FieldMessage err : list ){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(err.getMessage())
                    .addPropertyNode(err.getFiledName()).addConstraintViolation();
        }
        
        return list.isEmpty();
    }
    
}
