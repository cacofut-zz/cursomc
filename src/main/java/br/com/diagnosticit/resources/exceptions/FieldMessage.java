/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.resources.exceptions;

import java.io.Serializable;

/**
 *
 * @author cristianoca
 */
public class FieldMessage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String filedName;
    private String message;
    
    public FieldMessage(){
        
    }

    public FieldMessage(String filedName, String message) {
        super();
        this.filedName = filedName;
        this.message = message;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
