/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.services.exceptions;

/**
 *
 * @author cristianoca
 */
public class ObjectNotFoundException extends RuntimeException{

    
    public ObjectNotFoundException( String mensagem ) {
        super( mensagem );
    }
    
    public ObjectNotFoundException( String mensagem, Throwable cause ){
        super( mensagem, cause );
    }
    
    
}
