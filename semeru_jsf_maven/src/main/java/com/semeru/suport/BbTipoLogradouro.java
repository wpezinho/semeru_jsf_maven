package com.semeru.suport;

import com.semeru.model.dao.HibernateDAO;
import com.semeru.model.dao.InterfaceDAO;
import com.semeru.model.entities.TipoLogradouro;
import com.semeru.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbTipoLogradouro")
@RequestScoped
public class BbTipoLogradouro  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public List<TipoLogradouro> getTipoLogradouros(){
        InterfaceDAO<TipoLogradouro> tipoLogradouroDAO = new HibernateDAO<TipoLogradouro>(TipoLogradouro.class, 
                FacesContextUtil.getRequestSession());
        return tipoLogradouroDAO.getEntites();
    
    }
    
}
