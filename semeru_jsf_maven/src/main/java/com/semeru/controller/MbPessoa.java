package com.semeru.controller;

import com.semeru.conversores.ConverterSHA1;
import com.semeru.model.dao.HibernateDAO;
import com.semeru.model.dao.InterfaceDAO;
import com.semeru.model.entities.Endereco;
import com.semeru.model.entities.Pessoa;
import com.semeru.model.entities.Sexo;
import com.semeru.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MbPessoa implements Serializable{
    
     private static final long serialVersionUID = 1L;
     
     private String confereSenha; 
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
          Date date = new Date();
          if(pessoa.getIdPessoa() == 0 || pessoa.getIdPessoa() == null){
              pessoa.setDataDeCadastro(date);
              insertPessoa();
          }else{
              updatePessoa();
          }
          return null;
      }

    private void insertPessoa() {
        pessoa.setSenha(ConverterSHA1.cipher(pessoa.getSenha()));
        if (pessoa.getSenha() == null ? confereSenha == null : pessoa.getSenha().equals(ConverterSHA1.cipher(confereSenha))) {
            pessoa.setPermissao("ROLE_ADMIN");
        pessoaDAO().save(pessoa);
          endereco.setPessoa(pessoa);
        enderecoDAO().save(endereco);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!",""));
        }else{
              FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
        }
        }

    private void updatePessoa() {
        pessoaDAO().update(pessoa);
        enderecoDAO().update(endereco);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!",""));
    }
    public String deletePessoa(){
        pessoaDAO().revome(pessoa);
        enderecoDAO().revome(endereco);
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

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }
    
    
}
