/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

public class Epost implements Serializable {
   
    private Date fecPost;
    
    private Integer idPost;
    
    private String desPost;

    private Integer idImg;
   
    private int idUsu;

    public Epost() {
    }

    public Epost(Integer idPost) {
        this.idPost = idPost;
    }

    public Epost(Integer idPost, String desPost) {
        this.idPost = idPost;
        this.desPost = desPost;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getDesPost() {
        return desPost;
    }

    public void setDesPost(String desPost) {
        this.desPost = desPost;
    }

    public Date getFecPost() {
        return fecPost;
    }

    public void setFecPost(Date fecPost) {
        this.fecPost = fecPost;
    }

    public Integer getIdImg() {
        return idImg;
    }

    public void setIdImg(Integer idImg) {
        this.idImg = idImg;
    }    
    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPost != null ? idPost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Epost)) {
            return false;
        }
        Epost other = (Epost) object;
        if ((this.idPost == null && other.idPost != null) || (this.idPost != null && !this.idPost.equals(other.idPost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Epost[ idPost=" + idPost + " ]";
    }    
}
