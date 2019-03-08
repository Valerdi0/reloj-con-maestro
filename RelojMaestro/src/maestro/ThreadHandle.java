/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maestro;
import java.net.*;
import java.io.*;
/**
 *
 * @author Daniel
 */
public class ThreadHandle extends Thread {
    Socket peticion;
    javax.swing.JLabel label;
    boolean enviar;
    
    public ThreadHandle(Socket peticion,javax.swing.JLabel label, boolean enviar){
        this.peticion = peticion;
        this.label = label;
        this.enviar = enviar;
    }
    
    @Override
    public void run(){
        BufferedReader entrada;
        DataOutputStream salida;
        String mensajeRecibido;
        try{
            System.out.println("Un cliente se ha conectado.");
            //Canales de entrada y salida de datos
            entrada = new BufferedReader(new InputStreamReader(peticion.getInputStream()));
            salida = new DataOutputStream(peticion.getOutputStream());
            mensajeRecibido = entrada.readLine();
            salida.writeUTF(label.getText());
            while(this.enviar){
            //Recepcion de mensaje
                mensajeRecibido = entrada.readLine();
            //System.out.println(mensajeRecibido);
                System.out.println("entra");
                salida.writeUTF(label.getText());
                this.enviar = false;
                //System.out.println("Cerrando conexión...");
            //entrada.close();
            //salida.close();
            //peticion.close();//Aqui se cierra la conexión con el cliente
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
