package Entidades;

/**
 *
 * @author Carlos
 */

import java.io.Serializable;

public class Cabuso implements Serializable {
   
    private Integer idAbuso;
    private String desAbuso;
    private int idUsu;

    public Cabuso() {
    }

    public Cabuso(Integer idAbuso) {
        this.idAbuso = idAbuso;
    }

    public Cabuso(Integer idAbuso, String desAbuso) {
        this.idAbuso = idAbuso;
        this.desAbuso = desAbuso;
    }
    
    public Cabuso(Integer idAbuso, String desAbuso, Integer idUsu) {
        this.idAbuso = idAbuso;
        this.desAbuso = desAbuso;
        this.idUsu = idUsu;
    }

    public Integer getIdAbuso() {
        return idAbuso;
    }

    public void setIdAbuso(Integer idAbuso) {
        this.idAbuso = idAbuso;
    }

    public String getDesAbuso() {
        return desAbuso;
    }

    public void setDesAbuso(String desAbuso) {
        this.desAbuso = desAbuso;
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
        hash += (idAbuso != null ? idAbuso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabuso)) {
            return false;
        }
        Cabuso other = (Cabuso) object;
        if ((this.idAbuso == null && other.idAbuso != null) || (this.idAbuso != null && !this.idAbuso.equals(other.idAbuso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cabuso[ idAbuso=" + idAbuso + " ]";
    }    
}