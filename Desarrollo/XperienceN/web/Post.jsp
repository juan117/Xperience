<%@page import="Entidades.SHA1"%>
<%@page import="Entidades.Musuario"%>
<%@page import="Entidades.DatosVideo"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.GOTO"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.util.Date"%>
<%@page import="Conexion.ConexionSQL"%>
<%@page import="Entidades.Dcomentario"%>
<%@page import="Entidades.Epost"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% 
    //Valida el acceso, si la sesión es nula redirecciona a la página de Login
    if(session.getAttribute("user") ==null)
        response.sendRedirect("Login.jsp");
    %>
    <head>
                <link href="css/modern.css" rel="stylesheet">
		<link href="css/modern-responsive.css" rel="stylesheet">
                <link href="css/theme-dark.css" rel="stylesheet">
		<link href="css/site.css" rel="stylesheet" type="text/css">
		<link href="js/google-code-prettify/prettify.css" rel="stylesheet" type="text/css">
		<script src="js/jquery-1.8.2.min.js"></script>
		<script src="js/google-analytics.js"></script>
		<script src="js/google-code-prettify/prettify.js"></script>
		<script src="js/github.info.js"></script>
                <script src="js/accordion.js"></script>
                <script src="js/pagecontrol.js"></script>
                <script type="text/javascript" src="js/carousel.js"></script>
                <script type="text/javascript" src="js/jquery.js"></script>
                <script type="text/javascript" src="js/jquery.MultiFile.js"></script>
                <script type="text/javascript" src="js/jquery.form.js"></script>
                <script type="text/javascript" src="js/jquery.MetaData.js"></script>
                <script type="text/javascript" src="js/jquery.blockUI.js"></script>
                <script type="text/javascript" src="js/jquery.MultiFile.pack.js"></script>
                <script type="text/javascript" src="js/jquery.validate.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
<style type="text/css">
body
{
background-image: url(img/black.jpg);
background-repeat: no-repeat;
background-attachment: fixed;
background-size: 100% 100%;
}
</style>        
        
        <title>Mi Perfil</title>
    </head>
    <body class="modern-ui" bgcolor="blue" onload="prettyPrint()">
    <center>
       
    <div class="page">
        <h1>Mi Perfil</h1>
        <div class="page-region">
        <div class="page-region-content">
            <center>
                <form action="Post.jsp" Method="POST">
                    <button type="submit">Cerrar sesión</button>
                    <input type="hidden" id="logout" name="logout" value="1">
                </form>
                <% 
                    if(request.getParameter("logout")!=null){
                        Musuario m =(Musuario)session.getAttribute("user");
                        ConexionSQL.RegistraSalida(m);
                        /*Limpia la sesión */
                        session.removeAttribute("user");
                        /*Asignar la ruta a regresar (Inicio)
                        response.sendRedirect("Inicio.jsp");  */
                    }
            %>
                
                
                <form action="Post.jsp" Method="POST">
                    Nick:<input type="text" id="nick" name="nick">
                    Password:<input type="password" id="password" name="password">
                    <button type="submit">Login</button>
                        
                </form>
                <% 
                    if(request.getParameter("password")!=null){
                        Musuario m = new Musuario();
                        m.setPasUsu(SHA1.SHA1(request.getParameter("password")));
                        m.setNickUsu(request.getParameter("nick"));
                        m.setIdUsu(ConexionSQL.Login(m));
                        if(m.getIdUsu()!=-1){
                            ConexionSQL.RegistraAcceso(m);
                            %>
                            <br>Login exitoso
                        <%
                        session.setAttribute("user", m);
                        }
                    }
                %>
                <form action="Post.jsp">
                    IdUsuario:<input id="idusu" name="idusu" type="text">   
                    Post:<input id="post" type="text" name="post">         
                    <input type="submit" title="Enviar">
                </form>              
                <%
                try{
                   if(request.getParameter("idusu")!=null && request.getParameter("post") !=""){
                        Epost e = new Epost();
                        e.setIdUsu(Integer.parseInt(request.getParameter("idusu")));
                        e.setDesPost(request.getParameter("post"));
                        
                        if(ConexionSQL.Postear(e)>=0){%>
                        Insertado!
               <%
                     }
                    }}
                catch(Exception ex){
                }
                %>
                <% 
                try{                  
                    int idpost = Integer.parseInt(request.getParameter("int"));
                    if(ConexionSQL.ModificaPostConVideo(idpost,request.getParameter("desc"))!=-1){%>
                        Descripción del video añadida exitosamente.
               <%
                     }
                    }
                catch(Exception ex){
                    out.write(ex.toString());
                }
                %>
                
                <form enctype="multipart/form-data" method="POST" id="frmVideo"  >
                    Comparte un video:<input type="file" name="files[]" class="multi" maxlength="1" accept="mp4|mpg">
                    <button type="submit">Subir Video</button>
                    <input type="hidden" name="vid" id="vid" value="vid">
                </form>
                <%
                    if(ServletFileUpload.isMultipartContent(request)){
                        
                        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());  
                        FileItemIterator it = servletFileUpload.getItemIterator(request);
                        while (it.hasNext()){                            
                            FileItemStream item = it.next();
                            String fileName = item.getName();                            
                            if(!item.isFormField()){
                                    //Analiza el tipo de archivo (img/vid)
                                         String dirName;
                                         
                                    if(request.getParameter("img")!=null){
                                        dirName=request.getContextPath();
                                        //dirName=  "C:\\Users\\Juan\\Documents\\XpImg\\";  
                                    }
                                    else{
                                        //dirName =  "C:\\Users\\Juan\\Documents\\XpVid\\";  
                                             dirName=request.getContextPath();
                                    }
                                    Musuario m = (Musuario)session.getAttribute("user");
                                        
                                    dirName +=m.getNickUsu()+"\\";
                                    File folder = new File(dirName);
                                    if(!folder.exists()){
                                       folder.mkdir();
                                    }                                            
                                    File saveTo = new File( dirName+fileName);                                    
                                    OutputStream fo = new FileOutputStream(saveTo);                              
                                    try {
                                        
                                        out.println("File field " + fileName + " with file name "
                                         + item.getName() + " detected.");
                                        InputStream read = new FileInputStream(saveTo);
                                        int counter =0;
                                        read = item.openStream();  
                                       byte[] buf =new byte[1024];
                                        int len;
                                       while((len=read.read(buf))>0){
                                            fo.write(buf,0,len);
                                        }
                                        fo.flush();
                                        fo.close();  
                                        int idpost=0;    
                                        
                                        
                                        idpost=ConexionSQL.InsertaVideo(m.getIdUsu(),fileName);
                                        if(idpost!=-1){
                                            //Se despliega un form para añadir información del video
                                        
                                        %>
                                        <form name="frmVideo" method="POST">
                                            Añade algo sobre tu video:
                                            <br>
                                            <input type="text" id="desc" name="desc">
                                            <input type="hidden" id="int" value="<%=idpost%>" name="int">
                                             <button type="submit">Agregar Descripcion</button>
                                        </form>
                                        
                                        
                                        <%}
                                      
                                        }                                 
                                    
                                        catch (Exception e){
                                    %>

                                    <b>Ha ocurrido un error mientras subimos tu video. Intentalo mas tarde.</b>

                                    <%
                                    
                                        fo.flush();
                                        fo.close();  
                                    
                                    }
                                    
                            }
                        }
                    }
                    %>
                    <%
                    String dirName =  "C:\\Users\\Juan\\Documents\\XpVid\\";  
                    for(DatosVideo video:ConexionSQL.ObtieneVideos((Musuario)session.getAttribute("user"))){ 
                    
                    %>
                    <video src="<%=dirName+video.nickUsu+"\\"+video.srcVid%>" controls="true">
                    </video>
                    <%} %>
            </center>
        </div>
        </div>
        
    </div>
    </center>
    </body>
</html>
