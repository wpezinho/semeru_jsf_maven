package com.semeru.controller;

import com.semeru.model.dao.HibernateDAO;
import com.semeru.model.dao.InterfaceDAO;
import com.semeru.model.entities.Cidade;
import com.semeru.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="mbCidade")
@RequestScoped
public class MbCidade implements Serializable{
    
     private static final long serialVersionUID = 1L;

     private Cidade cidade = new Cidade();
     private List<Cidade> cidades;

    public MbCidade() {
        
    }
     
     private InterfaceDAO<Cidade> cidadeDAO(){
         InterfaceDAO<Cidade> cidadeDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
         return cidadeDAO;
     }
      public String limpCidade() {
          cidade = new Cidade();
          return editCidade();
      }
     public String editCidade(){
         return "/Restrict/cadastrarcidade.faces";
     }
     public String addCidade(){
         if(cidade.getIdCidade() == 0 || cidade.getIdCidade() == null){
          insertCidade();
         }else{
         updateCidade();
         }
         limpCidade();
         return null;
     }

    private void insertCidade() {
        cidadeDAO().save(cidade);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!",""));
    }

    private void updateCidade() {
        cidadeDAO().update(cidade);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!",""));
    }
    public void deleteCidade(){ 
        cidadeDAO().revome(cidade);
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        try {
          cidades = cidadeDAO().getEntites();
            return cidades;  
        } catch (Exception e) {
            System.out.println("###################### Falho porra: "+e.getMessage());
        }return null;

    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    
   
}
