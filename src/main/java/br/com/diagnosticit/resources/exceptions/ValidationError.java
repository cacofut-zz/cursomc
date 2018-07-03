/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristianoca
 */
public class ValidationError extends StandardError{
    
    private List<FieldMessage> error = new ArrayList<>();
    
    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }
    
    public void addError( String fieldName,  String message ){
        error.add( new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getError() {
        return error;
    }

    public void setError(List<FieldMessage> error) {
        this.error = error;
    }
    
    
}
