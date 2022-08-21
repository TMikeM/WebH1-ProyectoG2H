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
public interface interfaceUsuario {
    
    public boolean guardarUsuario();

    public boolean eliminarUsuario();

    public boolean actualizarUsuario();

    public List<Usuario> listarUsuarios();
    public Usuario getUsuario();
}
