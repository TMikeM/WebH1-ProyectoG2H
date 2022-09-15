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
public class Zapato implements interfaceZapato{
    
    private int id_Zapato;
    private int id_Proveedor;
    private int id_Categoria;
    private String nombreZapato;
    private String color;
    private int talla;
    private int precio;
    private String img;
    private Categorias categoria;
    private Proveedor proveedor;
   

    public Zapato() {
    }

    public Zapato(int id_Zapato) {
        this.id_Zapato = id_Zapato;
    }

    public Zapato(int id_Zapato, int id_Proveedor, int id_Categoria, String nombreZapato, String color, int talla, int precio, String img, Categorias categoria, Proveedor proveedor) {
        this.id_Zapato = id_Zapato;
        this.id_Proveedor = id_Proveedor;
        this.id_Categoria = id_Categoria;
        this.nombreZapato = nombreZapato;
        this.color = color;
        this.talla = talla;
        this.precio = precio;
        this.img = img;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Zapato(int id_Zapato, int id_Proveedor, int id_Categoria, String nombreZapato, String color, int talla, int precio) {
        this.id_Zapato = id_Zapato;
        this.id_Proveedor = id_Proveedor;
        this.id_Categoria = id_Categoria;
        this.nombreZapato = nombreZapato;
        this.color = color;
        this.talla = talla;
        this.precio = precio;
    }

    public int getId_Zapato() {
        return id_Zapato;
    }

    public void setId_Zapato(int id_Zapato) {
        this.id_Zapato = id_Zapato;
    }

    public int getId_Proveedor() {
        return id_Proveedor;
    }

    public void setId_Proveedor(int id_Proveedor) {
        this.id_Proveedor = id_Proveedor;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getNombreZapato() {
        return nombreZapato;
    }

    public void setNombreZapato(String nombreZapato) {
        this.nombreZapato = nombreZapato;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Zapato{" + "id_Zapato=" + id_Zapato + ", id_Proveedor=" + id_Proveedor + ", id_Categoria=" + id_Categoria + ", nombreZapato=" + nombreZapato + ", color=" + color + ", talla=" + talla + ", precio=" + precio + ", img=" + img + ", categoria=" + categoria + ", proveedor=" + proveedor + '}';
    }


    @Override
    public boolean guardarZapato() {
        
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        
        String sql = "INSERT INTO Zapateria.zapatos\n" +
        "(id_Zapato, id_Categoria,id_Proveedor, nombre_Zapato, color, talla, precio)\n" +
        "values('"+this.id_Zapato+"','"+this.id_Categoria+"','"+this.id_Proveedor+"',"
                + "'"+this.nombreZapato+"', '"+this.color+"',"+this.talla+","+this.precio+");";
        
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
    public boolean eliminarZapato() {
        
        boolean exito = false;
        String sql = "DELETE FROM zapateria.zapatos\n" +
                     "WHERE id_Zapato='"+this.id_Zapato+"';";
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

        return exito;    }

    @Override
    public boolean actualizarZapato() {
        
        boolean exito = false;
        String sql = "UPDATE zapateria.zapatos\n" +
                     "SET id_Categoria='"+this.id_Categoria+"', id_Proveedor='"+this.id_Proveedor+"', " +
                     "nombre_Zapato='"+this.nombreZapato+"', color='"+this.color+"', talla='"+this.talla+"', "+ 
                     "precio='"+this.precio+"'\n" +
                     "WHERE id_Zapato='"+this.id_Zapato+"';";
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
    public List<Zapato> listarZapatos() {

        List<Zapato> zapatos = new ArrayList<>();
        String sql = "SELECT * FROM zapateria.zapatos;";
        ConexionBD conexion = new ConexionBD();
        
        ResultSet rs = conexion.consultarBD(sql);

        try {
            Zapato z;
            while (rs.next()) {
                z = new Zapato();
                z.setId_Zapato(rs.getInt("id_Zapato"));
                z.setId_Categoria(rs.getInt("id_Categoria"));
                z.setId_Proveedor(rs.getInt("id_Proveedor"));
                z.setNombreZapato(rs.getString("nombre_Zapato"));
                z.setColor(rs.getString("color"));
                z.setTalla(rs.getInt("talla"));
                z.setPrecio(rs.getInt("precio"));
                z.setImg(rs.getString("img"));
                
                Categorias c = new Categorias(z.getId_Categoria());
                z.setCategoria(c.getCategorias());
                
                Proveedor p = new Proveedor(z.getId_Proveedor());
                z.setProveedor(p.getProveedor());
                
                zapatos.add(z);
            }
        } catch (Exception e) {
            Logger.getLogger(Zapato.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            conexion.cerrarConexion();
        }

        return zapatos;

    }

    @Override
    public Zapato getZapato() {
        String sql = "SELECT * FROM zapateria.zapatos WHERE id_Zapato='"+this.id_Zapato+"';";
        ConexionBD conexion = new ConexionBD();

        ResultSet rs = conexion.consultarBD(sql);

        try {
            Zapato z;
            if (rs.next()) {
                this.id_Zapato =rs.getInt("id_Zapato");
                this.id_Proveedor = rs.getInt("id_Proveedor");
                this.id_Categoria = rs.getInt("id_Categoria");
                this.nombreZapato = rs.getString("nombre_Zapato");
                this.color = rs.getString("color");
                this.talla = rs.getInt("talla");
                this.precio = rs.getInt("precio");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Zapato.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return this;
    }    }
    
    
