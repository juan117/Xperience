
package Entidades;

import java.util.Date;

public class DatosVideo{
    public DatosVideo(int idp, int idv,String nick,String src,Date fec ){
        this.idPost=idp;
        this.idVid=idv;
        this.fecVid=fec;
        this.srcVid=src;
        this.nickUsu=nick;
    }
    public int idPost;
    public int idVid;
    public String nickUsu;
    public String srcVid;
    public Date fecVid;
    
}
