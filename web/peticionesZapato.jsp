<%-- 
    Document   : Archivo de peticiones
    Created on : dd/mm/yyyy, hh:mm: AM/PM
    Author     : nombre autor
--%>

<%@page import="logica.Zapato"%>
<%@page import="logica.Categorias"%>
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
        "listar"

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
            int id_Zapato = Integer.parseInt(request.getParameter("id_Zapato"));
            int id_Proveedor = Integer.parseInt(request.getParameter("id_Proveedor"));
            int id_Categoria = Integer.parseInt(request.getParameter("id_Categoria"));
            String nombreZapato = request.getParameter("nombreZapato");
            String color = request.getParameter("color");
            int talla = Integer.parseInt(request.getParameter("talla"));
            int precio = Integer.parseInt(request.getParameter("precio"));
            
            Zapato a = new Zapato(id_Zapato, id_Categoria,id_Proveedor, nombreZapato, color, talla, precio);
            if (a.guardarZapato()) {
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }

        } else if (proceso.equals("eliminar")) {
            //Solicitud de parámetros enviados desde el frontned
            //, uso de request.getParameter("nombre parametro")
            //creación de objeto y llamado a método eliminar
            int id_Zapato = Integer.parseInt(request.getParameter("id_Zapato"));
            Zapato a = new Zapato(id_Zapato);
            if (a.eliminarZapato()) {
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }

        } else if (proceso.equals("listar")) {
            //Solicitud de parámetros enviados desde el frontned
            //, uso de request.getParameter("nombre parametro")
            //creación de objeto y llamado al metodo listar
            try {
                List<Zapato> lista = new Zapato().listarZapatos();
                respuesta += "\"" + proceso + "\": true,\"Categorias\":" + new Gson().toJson(lista);
            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": true,\"Categorias\":[]";
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (proceso.equals("actualizar")) {
            //creación de objeto y llamado al metodo actualizar
            int id_Zapato = Integer.parseInt(request.getParameter("id_Zapato"));
            int id_Proveedor = Integer.parseInt(request.getParameter("id_Proveedor"));
            int id_Categoria = Integer.parseInt(request.getParameter("id_Categoria"));
            String nombreZapato = request.getParameter("nombreZapato");
            String color = request.getParameter("color");
            int talla = Integer.parseInt(request.getParameter("talla"));
            int precio = Integer.parseInt(request.getParameter("precio"));
            
            Zapato a = new Zapato(id_Zapato, id_Categoria,id_Proveedor, nombreZapato, color, talla, precio);
            if (a.actualizarZapato()) {
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
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

