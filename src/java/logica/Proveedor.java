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
public class Proveedor implements InterfazProveedor{
    
    private int id_Proveedor;
    private String nombre;
    private String direccion;
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(int id_Proveedor) {
        this.id_Proveedor = id_Proveedor;
    }

    public Proveedor(int idProveedor, String nombre, String direccion, String telefono) {
        this.id_Proveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdProveedor() {
        return id_Proveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.id_Proveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "idProveedor=" + id_Proveedor + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }

    @Override
    public boolean guardarProveedor() {

        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO zapateria.proveedores\n" +
                     "(id_Proveedor, nombre, direccion, telefono)\n" +
                     "VALUES('"+this.id_Proveedor+"', '"+this.nombre+"', '"+this.direccion+"', '"+this.telefono+"');";
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
    public boolean eliminarProveedor() {

                boolean exito = false;
        String sql = "DELETE FROM zapateria.proveedores\n" +
                     "WHERE id_Proveedor='"+this.id_Proveedor+"';";
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
    public boolean actualizarProveedor() {

        boolean exito = false;
        String sql = "UPDATE zapateria.proveedores\n" +
                     "SET nombre='"+this.nombre+"', direccion='"+this.direccion+"', telefono='"+this.telefono+"'\n" +
                     "WHERE id_Proveedor='"+this.id_Proveedor+"';";
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
    public List<Proveedor> listarProveedores() {

        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores;";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            Proveedor p;
            while (rs.next()) {
                p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_Proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                proveedores.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }

        return proveedores;


    }

    @Override
    public Proveedor getProveedor() {

        String sql = "SELECT * FROM proveedores WHERE id_Proveedor='"+this.id_Proveedor+"';";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            Proveedor p;
            if (rs.next()) {
                this.id_Proveedor=rs.getInt("id_Proveedor");
                this.nombre=(rs.getString("nombre"));
                this.direccion=(rs.getString("direccion"));
                this.telefono=(rs.getString("telefono"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return this;
        
    }
    
    
}
