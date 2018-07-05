/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diagnosticit.dto;

import br.com.diagnosticit.resources.services.validation.CpfOrCnpj;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author cristianoca
 */
@CpfOrCnpj
public class ClienteNewDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "O tamanho deve estar entre 5 e 120")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;
    
    @NotEmpty(message = "Preenchimento obrigatório")    
    private String cpfOuCnpj;
       
    private Integer tipo;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String complemento;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String bairro;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;
    
    private Long cidadeId;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone1;
    
    
    private String telefone2;
    private String telefone3;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

}
