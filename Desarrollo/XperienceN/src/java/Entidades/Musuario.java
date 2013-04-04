/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;

public class Musuario implements Serializable {
   
    private Integer bajaUsu;    
    private Integer idUsu;  
    private String nickUsu; 
    private String emailUsu;   
    private String pasUsu;

    public Musuario() {
    }

    public Musuario(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public Musuario(Integer idUsu, String nickUsu, String emailUsu, String pasUsu) {
        this.idUsu = idUsu;
        this.nickUsu = nickUsu;
        this.emailUsu = emailUsu;
        this.pasUsu = pasUsu;
    }

    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public String getNickUsu() {
        return nickUsu;
    }

    public void setNickUsu(String nickUsu) {
        this.nickUsu = nickUsu;
    }

    public String getEmailUsu() {
        return emailUsu;
    }

    public void setEmailUsu(String emailUsu) {
        this.emailUsu = emailUsu;
    }

    public String getPasUsu() {
        return pasUsu;
    }

    public void setPasUsu(String pasUsu) {
        this.pasUsu = pasUsu;
    }

   
    public Integer getBajaUsu() {
        return bajaUsu;
    }

    public void setBajaUsu(Integer bajaUsu) {
        this.bajaUsu = bajaUsu;
    }
    
}
