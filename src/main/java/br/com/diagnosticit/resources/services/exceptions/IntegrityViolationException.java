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
public class IntegrityViolationException extends RuntimeException {

    public IntegrityViolationException(String mensagem) {
        super(mensagem);
    }

    public IntegrityViolationException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
