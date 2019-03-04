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
    ServerSocket sc;
    int cont;
    //SERVIDOR

    public void initServer(Window ventana){
        try{
            sc = new ServerSocket(PUERTO );/* crea socket servidor que escuchara en puerto 5000*/
            cont = 0;           
            while(true){
                Thread t;
                System.out.println("Esperando una conexión en el puerto:" + PUERTO);
                Socket esclavo = sc.accept();
                cont++;
                switch(cont){
                    case 1:
                        t = new ThreadHandle(esclavo, ventana.LabelReloj1);
                        break;
                    case 2:
                        t = new ThreadHandle(esclavo, ventana.LabelReloj2);
                        break;
                    case 3:
                        t = new ThreadHandle(esclavo, ventana.LabelReloj3);
                        break;
                    case 4:
                        t = new ThreadHandle(esclavo, ventana.LabelReloj4);
                        break;
                    default:
                        t = new ThreadHandle(esclavo, ventana.LabelReloj1);
                        break;
                }
                t.start();
                if(cont == 4)
                    cont = 0;
                
            }            
        }catch(Exception e ){
            System.out.println("Error: "+e.getMessage());
        }
    }
}

