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
 * @author Lizeth
 */
public class Usuario implements interfaceUsuario {

    private int id_Usuario;
    private String nombre;
    private String apellido;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public Usuario(String nombre, String apellido, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }

    public Usuario(int id_Usuario, String nombre, String apellido, String contrasena) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return id_Usuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.id_Usuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getapellido() {
        return apellido;
    }

    public void setapellido(String apellido) {
        this.apellido = apellido;
    }

    public String getcontrasena() {
        return contrasena;
    }

    public void setcontrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + id_Usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasena=" + contrasena + '}';
    }

    @Override
    public boolean guardarUsuario() {

        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO zapateria.usuario\n"
                + "(nombre, apellido, contrasena)\n"
                + "VALUES('" + this.nombre + "', '" + this.apellido + "', '" + this.contrasena + "');";
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
    public boolean eliminarUsuario() {

        boolean exito = false;
        String sql = "DELETE FROM zapateria.usuario\n"
                + "WHERE id_Usuario='" + this.id_Usuario + "';";
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
    public boolean actualizarUsuario() {

        boolean exito = false;
        String sql = "UPDATE zapateria.usuario\n"
                + "SET nombre='" + this.nombre + "', apellido='" + this.apellido + "', contrasena='" + this.contrasena + "'\n"
                + "WHERE id_Usuario='" + this.id_Usuario + "';";
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
    public List<Usuario> listarUsuarios() {

        List<Usuario> Usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            Usuario p;
            while (rs.next()) {
                p = new Usuario();
                p.setId_Usuario(rs.getInt("id_Usuario"));
                p.setNombre(rs.getString("nombre"));
                p.setapellido(rs.getString("apellido"));
                p.setcontrasena(rs.getString("contrasena"));
                Usuarios.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }

        return Usuarios;

    }

    @Override
    public Usuario getUsuario() {

        String sql = "SELECT * FROM usuario WHERE nombre='" + this.nombre + "' and apellido='" + this.apellido + "' and contrasena='" + this.contrasena + "';";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);
        Usuario p = null;
        try {
            if (rs.next()) {
                this.id_Usuario = rs.getInt("id_Usuario");
                this.nombre = (rs.getString("nombre"));
                this.apellido = (rs.getString("apellido"));
                // this.contrasena=(rs.getString("contrasena"));
                p = this;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return p;
    }

}
