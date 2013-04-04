/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Edatosusuario implements Serializable {
  
    private Integer idDusu;
   
    private String edUsu;
   
    private String sexUsu;
  
    private String bioUsu;
    
    private Integer idUsu;

    public Edatosusuario() {
    }

    public Edatosusuario(Integer idDusu) {
        this.idDusu = idDusu;
    }

    public Edatosusuario(Integer idDusu, String edUsu, String sexUsu) {
        this.idDusu = idDusu;
        this.edUsu = edUsu;
        this.sexUsu = sexUsu;
    }

    public Integer getIdDusu() {
        return idDusu;
    }

    public void setIdDusu(Integer idDusu) {
        this.idDusu = idDusu;
    }

    public String getEdUsu() {
        return edUsu;
    }

    public void setEdUsu(String edUsu) {
        this.edUsu = edUsu;
    }

    public String getSexUsu() {
        return sexUsu;
    }

    public void setSexUsu(String sexUsu) {
        this.sexUsu = sexUsu;
    }

    public String getBioUsu() {
        return bioUsu;
    }

    public void setBioUsu(String bioUsu) {
        this.bioUsu = bioUsu;
    }

  

    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

   
    
}
