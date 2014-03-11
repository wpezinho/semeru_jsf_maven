package com.semeru.controller;

import com.semeru.model.dao.HibernateDAO;
import com.semeru.model.dao.InterfaceDAO;
import com.semeru.model.entities.Endereco;
import com.semeru.model.entities.Pessoa;
import com.semeru.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MbPessoa implements Serializable{
    
     private static final long serialVersionUID = 1L;
     
     private Pessoa pessoa = new Pessoa();
     private Endereco endereco = new Endereco();
     private List<Pessoa> pessoas;
     private List<Endereco> enderecos;

    public MbPessoa() {
    }
     
     private InterfaceDAO<Pessoa> pessoaDAO(){
         InterfaceDAO<Pessoa> pessoaDAO = new HibernateDAO<Pessoa>(Pessoa.class, FacesContextUtil.getRequestSession());
         return pessoaDAO;
     }
      private InterfaceDAO<Endereco> enderecoDAO(){
         InterfaceDAO<Endereco> enderecoDAO = new HibernateDAO<Endereco>(Endereco.class, FacesContextUtil.getRequestSession());
         return enderecoDAO;
     }
      public String limpPesssoa(){
          pessoa = new Pessoa();
          endereco = new Endereco();
          return editPesssoa();
      }
       public String editPesssoa(){
          return "/Restrict/cadastrarpessoa.faces";
      }
      public String addPessoa(){
          if(pessoa.getIdPessoa() == null || pessoa.getIdPessoa() == 0){
              insertPessoa();
          }else{
              updatePessoa();
          }
          return null;
      }

    private void insertPessoa() {
        pessoaDAO().save(pessoa);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!",""));
    }

    private void updatePessoa() {
        pessoaDAO().update(pessoa);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!",""));
    }
    public String deletePessoa(){
        pessoaDAO().revome(pessoa);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Regisro excluido com sucesso!",""));
        
        return null; 
    }

    public List<Pessoa> getPessoas() {
        pessoas = pessoaDAO().getEntites();
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoDAO().getEntites();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
