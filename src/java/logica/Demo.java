
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;

/**
 *
 * @author CENTIC
 */
public class Demo {

    public static void main(String[] args) {
        
        List<Zapato> lista = new Zapato().listarZapatos();
        for (Zapato categoria : lista) {
            System.out.println(categoria);
        }
        
        
//          Zapato z = new Zapato(2, 2, 2, "Tenis Nou", "Amarillo", 33,8888);
//          System.out.println(z.guardarZapato());
          
          
        
        
//        Proveedor p = new Proveedor(2, "Proveedor 2", "Calle 2", "3116128142");
//        System.out.println(p.guardarProveedor());
//        System.out.println("Siiiuuuu :D");
//        
        
        
//        Autor a= new Autor("4321", "Maria", "Colombiana");
//        System.out.println(a.guardarAutor());

//           Autor a= new Autor();
//           a.setCodigo("1234");
//           a.setNombre("Maria");
//           a.setNacionalidad("Peruana");
//           System.out.println(a.actualizarAutor());

    }

}
