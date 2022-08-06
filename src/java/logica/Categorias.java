/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ConexionBD;

/**
 *
 * @author CENTIC
 */
public class Categorias implements InterfaceCategorias {

    private int id_Categoria;
    private String nombre;
    private String descripcion;

    public Categorias() {
    }

    public Categorias(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public Categorias(int id_Categoria, String nombre, String descripcion) {
        this.id_Categoria = id_Categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categorias{" + "id_Categoria=" + id_Categoria + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    

    @Override
    public boolean guardarCategorias() {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO zapateria.categorias\n"
                + "(id_Categoria, nombre, descripcion)\n"
                + "VALUES('" + this.id_Categoria + "','" + this.nombre + "', '" + this.descripcion + "');";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                exito = true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
            }
        } else {
            conexion.cerrarConexion();
        }

        return exito;
    }

    @Override
    public boolean eliminarCategorias() {
        boolean exito = false;
        String sql = "DELETE FROM zapateria.categorias\n"
                + "WHERE id_Categoria=" + this.id_Categoria + ";";
        ConexionBD conexion = new ConexionBD();
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                exito = true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
            }
        } else {
            conexion.cerrarConexion();
        }

        return exito;
    }

    @Override
    public boolean actualizarCategorias() {
        boolean exito = false;
        String sql = "UPDATE zapateria.categorias\n"
                + "SET nombre='" + this.nombre + "', descripcion='" + this.descripcion + "'\n"
                + "WHERE id_Categoria=" + this.id_Categoria + ";";
        ConexionBD conexion = new ConexionBD();
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                exito = true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
            }
        } else {
            conexion.cerrarConexion();
        }

        return exito;
    }

    @Override
    public List<Categorias> listarCategorias() {

        List<Categorias> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias;";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            Categorias c;
            while (rs.next()) {
                c = new Categorias();
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setId_Categoria(rs.getInt("id_Categoria"));
                categorias.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }

        return categorias;
    }

    @Override
    public Categorias getCategorias() {
        String sql = "SELECT * FROM zapateria.categorias WHERE id_Categoria="+this.id_Categoria+";";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            if (rs.next()) {
                this.id_Categoria = rs.getInt("id_Categoria");
                this.descripcion = rs.getString("descripcion");
                this.nombre = rs.getString("nombre");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return this;
    }
  }
