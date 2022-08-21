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
public class Usuario implements interfaceUsuario{
    
    private int id_Usuario;
    private String nombre;
    private String apellido;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public Usuario(int idUsuario, String nombre, String apellido, String contraseña) {
        this.id_Usuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
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

    public String getcontraseña() {
        return contraseña;
    }

    public void setcontraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + id_Usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", contraseña=" + contraseña + '}';
    }

    @Override
    public boolean guardarUsuario() {

        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO zapateria.usuario\n" +
                     "(id_Usuario, nombre, apellido, contraseña)\n" +
                     "VALUES('"+this.id_Usuario+"', '"+this.nombre+"', '"+this.apellido+"', '"+this.contraseña+"');";
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
        String sql = "DELETE FROM zapateria.usuario\n" +
                     "WHERE id_Usuario='"+this.id_Usuario+"';";
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
        String sql = "UPDATE zapateria.usuario\n" +
                     "SET nombre='"+this.nombre+"', apellido='"+this.apellido+"', contraseña='"+this.contraseña+"'\n" +
                     "WHERE id_Usuario='"+this.id_Usuario+"';";
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

        List<Usuario> Usuarioes = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            Usuario p;
            while (rs.next()) {
                p = new Usuario();
                p.setIdUsuario(rs.getInt("id_Usuario"));
                p.setNombre(rs.getString("nombre"));
                p.setapellido(rs.getString("apellido"));
                p.setcontraseña(rs.getString("contraseña"));
                Usuarioes.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }

        return Usuarioes;


    }

    @Override
    public Usuario getUsuario() {

        String sql = "SELECT * FROM Usuarioes WHERE id_Usuario='"+this.id_Usuario+"';";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            Usuario p;
            if (rs.next()) {
                this.id_Usuario=rs.getInt("id_Usuario");
                this.nombre=(rs.getString("nombre"));
                this.apellido=(rs.getString("apellido"));
                this.contraseña=(rs.getString("contraseña"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return this;
        
    }
    
    
}
