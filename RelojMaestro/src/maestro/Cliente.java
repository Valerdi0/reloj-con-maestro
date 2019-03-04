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
public class Cliente {
    final String HOST = "192.168.1.66";
    final int PUERTO=5000;
    Socket sc;
    //DataOutputStream mensaje;
    BufferedReader entrada;
    PrintStream mensaje;
    //Cliente

    public void initClient(){ /*ejecuta este metodo para correr el cliente */
        try{
            sc = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/
            //creamos el flujo de datos por el que se enviara un mensaje
            //mensaje = new DataOutputStream(sc.getOutputStream());
            //enviamos el mensaje
            //mensaje.writeUTF("hola que tal!!");
            mensaje = new PrintStream(sc.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            
            mensaje.println("Hola que tal");

            System.out.println(entrada.readLine());
            //cerramos la conexión
            sc.close();
            entrada.close();
            mensaje.close();
        }catch(Exception e ){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public static void main(String[] args){
        Cliente c = new Cliente();
        c.initClient();
    }
}
