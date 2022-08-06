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
public interface InterfaceCategorias {
       
    public boolean guardarCategorias();

    public boolean eliminarCategorias();

    public boolean actualizarCategorias();

    public List<Categorias> listarCategorias();
    
    public Categorias getCategorias();
}
