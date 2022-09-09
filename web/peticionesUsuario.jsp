<%-- 
    Document   : Archivo de peticiones
    Created on : dd/mm/yyyy, hh:mm: AM/PM
    Author     : nombre autor
--%>

<%@page import="logica.Usuario"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="application/json;charset=iso-8859-1" language="java" pageEncoding="iso-8859-1" session="true"%>

<%    // Iniciando respuesta JSON.
    String respuesta = "{";
    //Lista de procesos o tareas a realizar 
    List<String> tareas = Arrays.asList(new String[]{
        "guardar",
        "eliminar",
        "actualizar",
        "listar",
        "iniciarsesion"
	
    });
    String proceso = "" + request.getParameter("proceso");
    // Validación de parámetros utilizados en todos los procesos.
    if (tareas.contains(proceso)) {
        respuesta += "\"ok\": true,";
        // ------------------------------------------------------------------------------------- //
        // -----------------------------------INICIO PROCESOS----------------------------------- //
        // ------------------------------------------------------------------------------------- //
        if (proceso.equals("guardar")) {
            //Solicitud de parámetros enviados desde el frontned
            //, uso de request.getParameter("nombre parametro")
            // creación de objeto y llamado a método guardar           
            //int id_Usuario = Integer.parseInt(request.getParameter("id_Usuario"));
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String contrasena=request.getParameter("contrasena");
            Usuario a = new Usuario(nombre, apellido, contrasena);
            if (a.guardarUsuario()) { 
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } else if (proceso.equals("eliminar")) {
        //Solicitud de parámetros enviados desde el frontned
            //, uso de request.getParameter("nombre parametro")
            //creación de objeto y llamado a método eliminar
            int id_Usuario = Integer.parseInt(request.getParameter("id_Usuario"));        
            Usuario a = new Usuario(id_Usuario);
            if (a.eliminarUsuario()) {
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } else if (proceso.equals("listar")) {
        //Solicitud de parámetros enviados desde el frontned
            //, uso de request.getParameter("nombre parametro")
           //creación de objeto y llamado al metodo listar
            try {
                List<Usuario> lista = new Usuario().listarUsuarios();
                respuesta += "\"" + proceso + "\": true,\"Usuarios\":" + new Gson().toJson(lista);
            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": true,\"Usuarios\":[]";
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (proceso.equals("actualizar")) {
            //creación de objeto y llamado al metodo actualizar
            int id_Usuario = Integer.parseInt(request.getParameter("id_Usuario"));
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String contrasena=request.getParameter("contrasena");
            Usuario a = new Usuario(id_Usuario, nombre, apellido, contrasena);
            if (a.actualizarUsuario()) {                     
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } 
        else if(proceso.equals("iniciarsesion")){
        try {
                Usuario b = new Usuario();
                String nombre=request.getParameter("nombre");
                String apellido=request.getParameter("apellido");
                String contrasena=request.getParameter("contrasena");
                Usuario a = new Usuario(nombre, apellido, contrasena);
                b = a.getUsuario();
                respuesta += "\"" + proceso + "\": true,\"Usuarios\":" + new Gson().toJson(b);

            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": true,\"Usuarios\":[]";
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
 
        // ------------------------------------------------------------------------------------- //
        // -----------------------------------FIN PROCESOS-------------------------------------- //
        // ------------------------------------------------------------------------------------- //
        // Proceso desconocido.
    } else {
        respuesta += "\"ok\": false,";
        respuesta += "\"error\": \"INVALID\",";
        respuesta += "\"errorMsg\": \"Lo sentimos, los datos que ha enviado,"
                + " son inválidos. Corrijalos y vuelva a intentar por favor.\"";
    }    
    // Responder como objeto JSON codificación ISO 8859-1.
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
%>
