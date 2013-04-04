package Conexion;
import Entidades.Mprofesion;
import Entidades.Epost;
import Entidades.DatosVideo;
import Entidades.Musuario;
import Entidades.Dcomentario;
import Entidades.SHA1;
import Entidades.Edatosusuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionSQL {
    public static String URL="jdbc:mysql://localhost:3306/sozial_replica";
    public static String user="user";
    public static String pass="sozial";    
    private static Statement sentenciaSQL;
    private Statement sentenciaSQL2;
    private String dbname;
    private static Connection connection;    
    private static ResultSet cdr;
    public boolean RegistraUsuario(Musuario m,Edatosusuario edusu,Mprofesion profesion){
        try{
           String insertaMusuario = "INSERT INTO MUSUARIO (nick_usu,email_usu,pas_usu) VALUES (";
           insertaMusuario+="'"+m.getNickUsu()+"','"+m.getEmailUsu()+"','"+m.getPasUsu()+"')";
           connection = DriverManager.getConnection(URL,user,pass);
           sentenciaSQL = connection.createStatement();
           sentenciaSQL.execute(insertaMusuario);  
           String obtenIdusu="SELECT id_usu FROM musuario WHERE nick_usu='"+m.getNickUsu()+"' AND email_usu='"+m.getEmailUsu()+"'";
           cdr = sentenciaSQL.executeQuery(obtenIdusu);
           
           while(cdr.next()){
               if(cdr.getInt("id_usu") != 0){
                   m.setIdUsu(cdr.getInt("id_usu"));
               }
           }
           
           String insertaDatosUsuario="INSERT INTO edatosusuario (id_usu,ed_usu,sex_usu,bio_usu) values(";
           insertaDatosUsuario+=m.getIdUsu()+",'"+edusu.getEdUsu()+"','"+edusu.getSexUsu()+"','"+edusu.getBioUsu()+"')";
           sentenciaSQL.execute(insertaDatosUsuario);
           //Falta agregar insert de la profesion
           connection.close();    
            
            return true;
        }
        catch(Exception ex){
            return false;
        }
        
    }
    /* Código Nuevo*/
    public static int InsertaVideo(int Idusu,String nombre){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            String insertaPost = "INSERT INTO EPOST(DES_POST,ID_USU) VALUES (''," +Idusu + ")";
            sentenciaSQL.execute(insertaPost);
            String lastindex = "SELECT LAST_INSERT_ID()";
            cdr= sentenciaSQL.executeQuery(lastindex);
            int idpost = 0;
            while(cdr.next()){
                idpost = cdr.getInt(1);
            }            
            String inserta="INSERT INTO DVIDEO(SRC_VID,ID_POST) VALUES('" + nombre + "',"+idpost+")";
            sentenciaSQL.execute(inserta);
            connection.close();    
            return idpost;
        }
        catch(Exception ex){
            return -1;
        }
    }
    public static int ModificaPostConVideo(int idpost,String desc){
        try{
            String insertaPost = "UPDATE EPOST SET DES_POST='"+desc+"' WHERE ID_POST="+idpost+"";           
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            sentenciaSQL.execute(insertaPost);           
            connection.close(); 
            return 1;
        }
        catch(Exception ex){
            return -1;
        }
    }
    public static int InsertaImagen(int Idusu,String nombre){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            String insertaPost = "INSERT INTO EPOST(DES_POST,ID_USU) VALUES (''," +Idusu + ")";
            sentenciaSQL.execute(insertaPost);
            String lastindex = "SELECT LAST_INSERT_ID()";
            cdr= sentenciaSQL.executeQuery(lastindex);
            int idpost = 0;
            while(cdr.next()){
                idpost = cdr.getInt(1);
            }
            String inserta="INSERT INTO DIMAGEN(IMG_IMG,ID_POST) VALUES('" + nombre + "',"+idpost+")";
            sentenciaSQL.execute(inserta);
            connection.close();    
            return idpost;
        }
        catch(Exception ex){
            return -1;
        }
    }
    public static int ModificaPostConImagen(int idpost,String desc){
        try{
            String insertaPost = "UPDATE EPOST SET DES_POST='"+desc+"' WHERE ID_POST="+idpost+"";           
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            sentenciaSQL.execute(insertaPost);           
            connection.close(); 
            return 1;
        }
        catch(Exception ex){
            return -1;
        }
    }
    public static int ReportarAbuso(int idusu,int idrep,int idab,String descr){
        try{
            String reportaAbuso ="INSERT INTO EABUSO(ID_REP,ID_USUR,DES_ABU,ID_ABUSO) VALUES ("+ idrep+","+idusu+",'"+descr+"',"+idab+")";
            Class.forName("com.mysql.jdbc.Driver").newInstance();            
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();            
            sentenciaSQL.execute(reportaAbuso); 
            return 1;
        }
        catch(Exception ex){
            return -1;
        }
    
    }
    
    /*Fin del código nuevo*/
    public static int Postear(Epost e) throws SQLException{
        try{
            String insertaPost = "INSERT INTO EPOST(DES_POST,ID_USU) VALUES('";
            insertaPost+=e.getDesPost()+"',";
            insertaPost+=e.getIdUsu()+")";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            sentenciaSQL.execute(insertaPost);           
            connection.close();                 
            return 0;
           
        }
        catch(Exception ex){
            connection.close();
            return -1;
        }   
    }
    public static void RegistraAcceso(Musuario m){
        try{
            String registraAcceso = SentenciasEstadistica.getSentenciaRegistroAcceso(m.getIdUsu());
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            sentenciaSQL.execute(registraAcceso);           
            connection.close();             
        }
        catch(Exception ex){
            
        }

    }
    public static ArrayList<DatosVideo> ObtieneVideos(Musuario m){
        try{
            String cadVid = "SELECT SRC_VID,NICK_USU,ID_VID,ID_POST,FEC_VID"
                    + " FROM DVIDEO NATURAL JOIN EPOST NATURAL JOIN MUSUARIO";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            cdr = sentenciaSQL.executeQuery(cadVid);           
            ArrayList<DatosVideo> vid = new ArrayList<DatosVideo>();
            while(cdr.next()){                
                vid.add(new DatosVideo(cdr.getInt("ID_POST"), cdr.getInt("ID_VID"),cdr.getString("NICK_USU"), cdr.getString("SRC_VID"), cdr.getDate("FEC_VID")));
            }
            connection.close();
            return vid;
        }
        catch(Exception ex){
            return new ArrayList<DatosVideo>();
        }
    
    }
    
    
    public static int Login(Musuario m ){
        try{
            String getId = "SELECT ID_USU FROM MUSUARIO WHERE NICK_USU='"+m.getNickUsu()+"' AND PAS_USU='"+m.getPasUsu()+"'";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            cdr =sentenciaSQL.executeQuery(getId);
            int login=-1;            
            while(cdr.next()){
                login=cdr.getInt("ID_USU");
            }
            return login;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    public static void RegistraSalida(Musuario m){
        try{
            String registraSalida = SentenciasEstadistica.getSentenciaRegistroSalida(m.getIdUsu());
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
            sentenciaSQL.execute(registraSalida);           
            connection.close();             
        }
        catch(Exception ex){
            
        }
    }
    public int Comentar(Dcomentario com){
        try{
            String insertaComentario = "INSERT INTO DCOMENTARIO(ID_POST,ID_USU,DES_COM) VALUES(";
            insertaComentario+=com.getIdPost()+",";
            insertaComentario+=com.getIdUsu()+",'";
            insertaComentario+=com.getDesCom()+"'";
            boolean insertado = sentenciaSQL.execute(insertaComentario);
            if(insertado){
                return 1;
            }
            return 0;
        }
        catch(Exception ex){
            return -1;
        }
    }
    
    public ConexionSQL(){}
    //codigo de carlos
	 public ArrayList<String> DatosUsuarioC(int idu) throws Exception{
     
            StringBuilder sb = new StringBuilder();
            sb.append("select bio_usu,ed_usu,sex_usu,des_prof,email_usu from musuario natural join edatosusuario natural join dprofesiones natural join mprofesion where id_usu=");
            sb.append(idu); 
            this.open();
            cdr=sentenciaSQL.executeQuery(sb.toString());
            ArrayList<String> as = new ArrayList<String>();  
            cdr.first();
            as.add(cdr.getString("bio_usu"));            
            as.add(cdr.getString("ed_usu"));
            as.add(cdr.getString("sex_usu"));
            as.add(cdr.getString("des_prof"));
            as.add(cdr.getString("email_usu"));
            return as;
    }
	
	
	public ArrayList<String> DatosUsuariobyProf(String prof) throws Exception{
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("select nick_usu,bio_usu,ed_usu,sex_usu,des_prof,email_usu from musuario natural join edatosusuario natural join dprofesiones natural join mprofesion where des_prof='");
            sb.append(prof); 
            sb.append("'");
            this.open();
            System.out.println(sb.toString());
            cdr=sentenciaSQL.executeQuery(sb.toString());
            ArrayList<String> as = new ArrayList<String>();  
            cdr.first();
            as.add(cdr.getString("bio_usu"));//0            
            as.add(cdr.getString("ed_usu"));//1
            as.add(cdr.getString("sex_usu"));//2
            as.add(cdr.getString("des_prof"));//3
            as.add(cdr.getString("email_usu"));//4
            as.add(cdr.getString("nick_usu")); //5
            return as;     
     }
     catch(Exception e){
         e.printStackTrace();
         return null;
     }
    }
        
        
	public boolean insertarimg(String nombre,int idusu){            
            try{           
            StringBuilder sb2 = new StringBuilder();
            sb2.append("insert into epost (id_usu,des_post) values(");
            sb2.append(idusu);
            sb2.append(",'");
            sb2.append(nombre);
            sb2.append("')");
            this.open();
            sentenciaSQL.execute(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("select * from epost where des_post='");
            sb3.append(nombre);
            sb3.append("'");
            this.open();
            int idp=0;
            cdr=sentenciaSQL.executeQuery(sb3.toString());
            while(cdr.next()){            
                idp=cdr.getInt("id_post");
            }
            System.out.println("buscado por post");
            StringBuilder sb = new StringBuilder();
            sb.append("insert into dimagen (img_img,id_post) values('");
            sb.append(nombre);
            sb.append("',");
            sb.append(idp);
            sb.append(")");
            this.open();
            sentenciaSQL.execute(sb.toString());
            return true;
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
	
        public ArrayList<String> imgamigos(int idu){
           try{
               ArrayList<Integer> in =this.ListaAmigos(idu);
               ArrayList<String> la  = new ArrayList<String>();
               for(int w:in){
                   la.add(String.valueOf(w));
               }               
               
               System.out.println("Terminada la lista de amigos");
               
               ArrayList<Epost> ep =this.postAmigos(la, idu);
               StringBuilder b = new StringBuilder();
               b.append("select img_img from Dimagen where id_post=");
               b.append(ep.get(0).getIdPost());
               for(int i=1;i<ep.size();i++){
                   b.append(" or id_post=");
                   b.append(ep.get(i).getIdPost());
               }
               
               System.out.println(b.toString());
               
               
               cdr=sentenciaSQL.executeQuery(b.toString());
               ArrayList<String> amigos = new ArrayList<String>();
               while(cdr.next()){
                amigos.add(cdr.getString("img_img"));
               }
                              
               System.out.println("imagenes buscadas.");
               
               
               return amigos;
           }
           catch(Exception e){
           return null;
           }            
        }
        
	 public String cambiarDatosC(int idu,String bio,String pass,int edad){
    try{
          StringBuilder sb2 = new StringBuilder();
          sb2.append("update edatosusuario set bio_usu='");
          sb2.append(bio);
          sb2.append("' where id_usu=");
          sb2.append(idu);
          this.open();          
          sentenciaSQL.execute(sb2.toString());
          StringBuilder sb3 = new StringBuilder();
          sb3.append("update edatosusuario set ed_usu='");
          sb3.append(edad);
          sb3.append("' where id_usu=");
          sb3.append(idu);
          this.open();
          sentenciaSQL.execute(sb3.toString());
          if(!pass.isEmpty()){              
                StringBuilder sb = new StringBuilder();
                sb.append("update musuario set pas_usu='");
                sb.append(SHA1.SHA1(pass));
                sb.append("' where id_usu=");
                sb.append(idu);
                this.open();
                sentenciaSQL.execute(sb.toString());
                return "Password cambiado";
          }  
          else{
        return "Datos cambiados";}
     }
     catch(Exception e){
         e.printStackTrace();
         return "nada";
     }

    }
	
	
	//fin del codigo de carlos
    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public void open()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL,user,pass);
            sentenciaSQL = connection.createStatement();
           sentenciaSQL2 = connection.createStatement();
            System.out.println(connection.isClosed());
        }
        catch (Exception ex)
        {
           System.out.println(ex.toString());
        }
    }
    
    public void close( )
    {
        try
        {            
            connection.close( );
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
            
    }
    public ArrayList<String> mensajesEntre(int x,int w){
    try{
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT msg_men from Dmensajes where id_emi =");
        sb.append(x); 
        sb.append(" AND id_rec=");
        sb.append(w); 
        sb.append(" OR id_rec=");
        sb.append(x); 
        sb.append(" AND id_emi=");
        sb.append(w); 
        sb.append(" ORDER BY id_men DESC");
        this.open();
        cdr=sentenciaSQL.executeQuery(sb.toString());
        ArrayList<String> as = new ArrayList<String>();
        while(cdr.next()){
            as.add(cdr.getString("msg_men"));
        }
        return as;
    }
    catch(Exception e){
        return null;
    }
    }
    public ArrayList<Integer> emiMensajesentre(int x,int w){
    try{
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id_emi from Dmensajes where id_emi =");
        sb.append(x); 
        sb.append(" AND id_rec=");
        sb.append(w); 
        sb.append(" OR id_rec=");
        sb.append(x); 
        sb.append(" AND id_emi=");
        sb.append(w); 
        sb.append(" ORDER BY id_men DESC");
        this.open();
        cdr=sentenciaSQL.executeQuery(sb.toString());
        ArrayList<Integer> as = new ArrayList<Integer>();
        while(cdr.next()){
            as.add(cdr.getInt("id_emi"));
        }
        return as;
    }
    catch(Exception e){
        return null;
    }
    }
    public ArrayList<String> mensajes(int x){
    try{
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT msg_men from Dmensajes where id_emi =");
        sb.append(x); 
        sb.append(" OR id_rec=");
        sb.append(x); 
        sb.append(" ORDER BY id_men DESC");
        this.open();
        cdr=sentenciaSQL.executeQuery(sb.toString());
        ArrayList<String> as = new ArrayList<String>();
        while(cdr.next()){
            as.add(cdr.getString("msg_men"));
        }
      /* StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT msg_men from Dmensajes where id_rec =");
        sb2.append(x); 
        cdr=sentenciaSQL.executeQuery(sb2.toString());
        while(cdr.next()){
            as.add(cdr.getString("msg_men"));
        }*/
        return as;
    }
    catch(Exception e){return null;}
    }
    public ArrayList<String> Remi(int x){
    try{
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id_emi from Dmensajes where id_emi =");
        sb.append(x); 
        sb.append(" OR id_rec=");
        sb.append(x); 
        sb.append(" ORDER BY id_men DESC");
        this.open();
        cdr=sentenciaSQL.executeQuery(sb.toString());
        ArrayList<Integer> as = new ArrayList<Integer>();
        while(cdr.next()){
            as.add(cdr.getInt("id_emi"));
        }
        ArrayList<String> remitentes = new ArrayList<String>();
        for(int w:as){
            remitentes.add(this.GetnickUsu(w));
        }        
        
      /*StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT id_emi from Dmensajes where id_rec =");
        sb2.append(x); 
        as.clear();
        cdr=sentenciaSQL.executeQuery(sb2.toString());
        while(cdr.next()){
            as.add(cdr.getInt("id_emi"));
        }
        for(int xa:as){
            remitentes.add(this.GetnickUsu(xa));
        }*/
        return remitentes;
    }
    catch(Exception e){return null;}
    }
    public ArrayList<String> comentarios(int idp){
     try{
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT des_com from Dcomentario where id_post =");
            sb.append(idp); 
            this.open();
            ArrayList<String> d = new ArrayList<String>();
            sentenciaSQL.executeQuery(sb.toString());
            while(cdr.next()){
                d.add(cdr.getString("des_com"));
            }
            return d;
        }
        catch(Exception e){  
            System.out.println(e.toString());
            return null;
        }    
    
    }
    public ArrayList<Integer> ListaAmigos(int idusu){
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT distinct id_ami2 from Drelaciones where id_ami1 =");
            sb.append(idusu); 
            sb.append(" and baja_usu=0"); 
            this.open();
            cdr=sentenciaSQL.executeQuery(sb.toString());            
            ArrayList<Integer> al= new ArrayList<Integer>();
            while(cdr.next()){
                al.add(cdr.getInt("id_ami2"));
            }
            return al;
        }
        catch(Exception e){
            System.out.println(e.toString());
        return null;
        }    
       
    }
    public int GetIdUsu(String nk){
    try{
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id_usu from Musuario where nick_usu =");
        sb.append("'"); 
        sb.append(nk);
        sb.append("'");
        this.open();
        cdr=sentenciaSQL.executeQuery(sb.toString());                    
        cdr.first();
        return cdr.getInt("id_usu");
    }
    catch(Exception e){
        System.out.println(e.toString());
        return 0;}
    }
    public String GetnickUsu(int x){
    try{
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT nick_usu from Musuario where id_usu =");
            sb.append(x); 
            this.open();
            cdr=sentenciaSQL.executeQuery(sb.toString());
            cdr.first();
            return cdr.getString("nick_usu");
        }
        catch(Exception e){  
            System.out.println(e.toString());
            return null;
        }    
    }
    public boolean reportarusuario(int rep,int ab,String abuso){
    try{
      StringBuilder sb = new StringBuilder();
      sb.append("insert into eabuso (id_rep,id_usur,des_abu) values (");
      sb.append(rep); 
      sb.append(",");
      sb.append(ab); 
      sb.append(",'");
      sb.append(abuso);
      sb.append("')");
      this.open();
      sentenciaSQL.execute(sb.toString());      
          StringBuilder sb2 = new StringBuilder();
          sb2.append("update drelaciones set baja_usu=1 where id_ami1=");
          sb2.append(ab);
          sb2.append(" or id_ami2=");
          sb2.append(ab);
          this.open();
        sentenciaSQL.execute(sb2.toString());
          
      return true;
    }
    catch(Exception e){
        return false;
    }
    }
    public ArrayList<String> DatosUsuario(int idu) throws Exception{
     
            StringBuilder sb = new StringBuilder();
            sb.append("select bio_usu,ed_usu from musuario natural join edatosusuario where id_usu=");
            sb.append(idu); 
            this.open();
            cdr=sentenciaSQL.executeQuery(sb.toString());
            ArrayList<String> as = new ArrayList<String>();  
            cdr.first();
            as.add(cdr.getString("bio_usu"));            
            as.add(cdr.getString("ed_usu"));
            return as;}     

	
			
    public String cambiarDatos(int idu,String bio,String pass){
    try{
          StringBuilder sb2 = new StringBuilder();
          sb2.append("update edatosusuario set bio_usu='");
          sb2.append(bio);
          sb2.append("' where id_usu=");
          sb2.append(idu);
          this.open();
          if(!pass.isEmpty()){              
                StringBuilder sb = new StringBuilder();
                sb.append("update musuario set pas_usu='");
                sb.append(SHA1.SHA1(pass));
                sb.append("' where id_usu=");
                sb.append(idu);
                this.open();
                sentenciaSQL.execute(sb.toString());
                return "Password cambiado";
          }  
          else{
        return "Datos cambiados";}
    }
     catch(Exception e){
         return "nada";
     }
    }    
    public boolean hacerfavorito(int idu,int idp){
    try{
        StringBuilder sb2 = new StringBuilder();
        sb2.append("insert into efavorito (id_pfav,id_usf) values (");
        sb2.append(idp);
        sb2.append(",");
        sb2.append(idu);
        sb2.append(")");
        this.open();
        sentenciaSQL.execute(sb2.toString());
        return true;
    }
     catch(Exception e){
         e.printStackTrace();
         return false;
     }
    }
    
    public ArrayList<Epost> postAmigos(List<String> amigos,int ids){
    try{
        StringBuilder sb2 = new StringBuilder();
        sb2.append("select * from epost where id_usu=");
        sb2.append(ids);        
        /*int id =this.GetIdUsu(amigos.get(0));
        sb2.append(id);*/
        for(int i=0;i<amigos.size();i++){
            int idu=this.GetIdUsu(amigos.get(i));
            sb2.append( " or id_usu="+idu);
        }
        sb2.append(" order by id_post desc");
        System.out.printf(sb2.toString());
        cdr=sentenciaSQL.executeQuery(sb2.toString());
        ArrayList<Epost> ami = new ArrayList<Epost>();
        while(cdr.next()){
            Epost e = new Epost();
            e.setIdUsu(cdr.getInt("id_usu"));
            e.setDesPost(cdr.getString("des_post"));
            e.setIdImg(cdr.getInt("id_img"));
            e.setFecPost(null);
            e.setIdPost(cdr.getInt("id_post"));
            ami.add(e);
        }
        System.out.println("PostAmigos completada exitosamente.");
        return ami;
    }
    catch(Exception ex){
         ex.printStackTrace();
         return null;
     }
    }
    
   public ArrayList<Epost> favoritos(int ids){
    try{
        StringBuilder sb2 = new StringBuilder();
        sb2.append("select * from efavorito where id_usf=");
        sb2.append(ids);        
        /*int id =this.GetIdUsu(amigos.get(0));
        sb2.append(id);*/
        ResultSet rs;
        this.open();
        int i=0;
        
        cdr=sentenciaSQL.executeQuery(sb2.toString());
        ArrayList<Epost> ami = new ArrayList<Epost>();
        while(cdr.next()){
            Epost e = new Epost();  
            e.setIdPost(cdr.getInt("id_pfav"));
            StringBuilder sb3 = new StringBuilder();
            sb3.append("select * from epost where id_post=");
            sb3.append(cdr.getInt("id_pfav"));
            rs=sentenciaSQL2.executeQuery(sb3.toString());
            rs.next();
            e.setIdUsu(rs.getInt("id_usu"));
            e.setDesPost(rs.getString("des_post"));
            e.setIdImg(rs.getInt("id_img"));
            e.setFecPost(null);
            e.setIdPost(rs.getInt("id_post"));           
            ami.add(e);
            System.out.println(i);
            i++;
            this.open();
        }
        return ami;
    }
    catch(Exception ex){
         ex.printStackTrace();
         return null;
     }
    }
}
