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
public interface interfaceZapato {
    
    public boolean guardarZapato();

    public boolean eliminarZapato();

    public boolean actualizarZapato();

    public List<Zapato> listarZapatos();
    public Zapato getZapato();
}
