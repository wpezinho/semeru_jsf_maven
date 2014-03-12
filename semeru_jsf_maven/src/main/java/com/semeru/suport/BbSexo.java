package com.semeru.suport;

import com.semeru.model.dao.HibernateDAO;
import com.semeru.model.dao.InterfaceDAO;
import com.semeru.model.entities.Sexo;
import com.semeru.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="bbSexo")
@SessionScoped
public class BbSexo implements Serializable{
    
     private static final long serialVersionUID = 1L;
     
     public List<Sexo> getSexos(){
         InterfaceDAO<Sexo> sexoDAO = new HibernateDAO<Sexo>(Sexo.class, FacesContextUtil.getRequestSession());
         return sexoDAO.getEntites();
     }
    
}
