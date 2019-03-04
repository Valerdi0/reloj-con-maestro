/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maestro;

/**
 *
 * @author Daniel
 */
public class Main {
      
    public static void main(String[] args){
        Window maestro = new Window();
        Servidor conexion = new Servidor();
        RelojHilo contador = new RelojHilo(maestro.LabelReloj1,
                maestro.LabelReloj2, maestro.LabelReloj3, maestro.LabelReloj4);
        maestro.relojHilo = contador;
        maestro.setVisible(true);
        conexion.initServer(maestro);
    }
}
