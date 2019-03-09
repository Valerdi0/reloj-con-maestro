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
 * @author admin
 */
public class Servidor {
    final int PUERTO=5000;
    int maximoConexiones = 4; // Maximo de conexiones simultaneas
    ServerSocket sc;
    int cont = 0;
    //SERVIDOR

    public void initServer(Window ventana){
        try{
            sc = new ServerSocket(PUERTO, maximoConexiones);/* crea socket servidor que escuchara en puerto 5000*/
            MensajesChat mensajes = new MensajesChat();
            
            while(true){
                System.out.println("Esperando una conexión en el puerto:" + PUERTO);
                Socket esclavo = sc.accept();
                cont++;
                System.out.println("Cliente con la IP: " + esclavo.getInetAddress().getHostName() + " conectado.");
                switch(cont){
                    case 1:
                        ThreadHandle t1 = new ThreadHandle(esclavo, ventana.LabelReloj1, mensajes,ventana.relojHilo.reloj1);
                        t1.start();
                        break;
                    case 2:
                        ThreadHandle t2 = new ThreadHandle(esclavo, ventana.LabelReloj2, mensajes,ventana.relojHilo.reloj2);
                        t2.start();
                        break;
                    case 3:
                        ThreadHandle t3 = new ThreadHandle(esclavo, ventana.LabelReloj3, mensajes,ventana.relojHilo.reloj3);
                        t3.start();
                        break;
                    case 4:
                        ThreadHandle t4 = new ThreadHandle(esclavo, ventana.LabelReloj4, mensajes,ventana.relojHilo.reloj4);
                        t4.start();
                        break;
                }
                
                                
            }            
        }catch(Exception e ){
            System.out.println("Error: "+e.getMessage());
        }
    }
}

