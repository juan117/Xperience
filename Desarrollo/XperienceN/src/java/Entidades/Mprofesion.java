/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;

public class Mprofesion implements Serializable {
   
    private Integer idProf;
   
    private String desProf;
    
    private Integer idArea;

    public Mprofesion() {
    }

    public Mprofesion(Integer idProf) {
        this.idProf = idProf;
    }

    public Mprofesion(Integer idProf, String desProf) {
        this.idProf = idProf;
        this.desProf = desProf;
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public String getDesProf() {
        return desProf;
    }

    public void setDesProf(String desProf) {
        this.desProf = desProf;
    }

  
    
}
