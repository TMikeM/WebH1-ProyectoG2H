/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica;

import java.util.List;

/**
 *
 * @author Lizeth
 */
public interface InterfazProveedor {
    
    public boolean guardarProveedor();

    public boolean eliminarProveedor();

    public boolean actualizarProveedor();

    public List<Proveedor> listarProveedores();
    
    public Proveedor getProveedor();
}
