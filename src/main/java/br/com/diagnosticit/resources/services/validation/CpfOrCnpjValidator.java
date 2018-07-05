/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services.validation;

import br.com.diagnosticit.dto.ClienteNewDTO;
import br.com.diagnosticit.resources.domain.enums.TipoCliente;
import br.com.diagnosticit.resources.exceptions.FieldMessage;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author cristianoca
 */
public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, ClienteNewDTO>{

    /**
     * 
     * @param constraintAnnotation 
     */
    @Override
    public void initialize(CpfOrCnpj constraintAnnotation) {
        
    }
       
    /**
     * 
     * @param objDTO
     * @param context
     * @return 
     */
    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
        
        List<FieldMessage> list = new ArrayList<>();
        TipoCliente tipoCliente = TipoCliente.toEnum(objDTO.getTipo());
        System.out.println(tipoCliente);
        if( tipoCliente == TipoCliente.PESSOA_FISICA ){
            FieldMessage erro1 = new FieldMessage("cpfOuCnpj", "CPF inválido");
            list.add( erro1 );
        }
        
        if( tipoCliente == TipoCliente.PESSOA_JURIDICA ){
            FieldMessage erro2 = new FieldMessage("cpfOuCnpj", "CNPJ inválido");
            list.add( erro2 );
        }
        
        for( FieldMessage err : list ){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(err.getMessage())
                    .addPropertyNode(err.getFiledName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
    
}
