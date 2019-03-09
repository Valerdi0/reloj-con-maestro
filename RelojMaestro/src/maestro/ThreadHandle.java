/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maestro;
import java.net.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author Daniel
 */
public class ThreadHandle extends Thread implements Observer {
    Socket peticion;
    Minutos rh;
    javax.swing.JLabel label;
    private MensajesChat mensajes;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    
    public ThreadHandle(Socket peticion,javax.swing.JLabel label, MensajesChat mensajes, Minutos rh){
        this.peticion = peticion;
        this.label = label;
        this.mensajes = mensajes;
        this.rh = rh;
        
        try {
            entradaDatos = new DataInputStream(peticion.getInputStream());
            salidaDatos = new DataOutputStream(peticion.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
    }
    
    @Override
    public void run(){
        String mensajeRecibido;
        boolean conectado = true;
        // Se apunta a la lista de observadores de mensajes
        mensajes.addObserver(this);
        
        while(conectado){
            try{
                // Lee un mensaje enviado por el cliente
                mensajeRecibido = entradaDatos.readUTF();
                // Pone el mensaje recibido en mensajes para que se notifique 
                // a sus observadores que hay un nuevo mensaje.
                mensajes.setMensaje(mensajeRecibido);
            } catch (IOException ex) {
                System.out.println("Cliente con la IP " + peticion.getInetAddress().getHostName() + " desconectado.");
                conectado = false; 
                // Si se ha producido un error al recibir datos del cliente se cierra la conexion con el.
                try {
                    entradaDatos.close();
                    salidaDatos.close();
                } catch (IOException ex2) {
                    System.out.println("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
                }
            }
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            // Envia el mensaje al cliente
            System.out.println(arg.toString());
            String mensajes = arg.toString();
            if(mensajes.equalsIgnoreCase(":"))
                salidaDatos.writeUTF(label.getText());
            else{
                System.out.println(mensajes);
                String[] numeros = mensajes.split(":");
                rh.setHora(Integer.parseInt(numeros[0]));
                rh.setMinuto(Integer.parseInt(numeros[1]));
                salidaDatos.writeUTF(label.getText());
            }
            
            
            
            
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
    }
}
