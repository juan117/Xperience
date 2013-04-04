
package Entidades;

import java.io.Serializable;

public class Dcomentario implements Serializable {
   
    private String desCom;
    
    private Integer idCom;
    
    private int idUsu;
    
    private int idPost;

    public Dcomentario() {
    }

    public Dcomentario(Integer idCom) {
        this.idCom = idCom;
    }

    public Integer getIdCom() {
        return idCom;
    }

    public void setIdCom(Integer idCom) {
        this.idCom = idCom;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        idUsu = idUsu;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(Epost idPost) {
        idPost = idPost;
    }

    

    public String getDesCom() {
        return desCom;
    }

    public void setDesCom(String desCom) {
        this.desCom = desCom;
    }
    
}
