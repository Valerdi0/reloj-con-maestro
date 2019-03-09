
package relojes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextField;

/**
 * Esta clase gestiona el envio de datos entre el cliente y el servidor.
 * 
 * @author Ivan Salas Corrales <http://programando-o-intentandolo.blogspot.com.es/>
 */
public class ConexionServidor implements ActionListener {
    
    private Socket socket; 
    private String mensaje;
    private DataOutputStream salidaDatos;
    javax.swing.JTextField hora;
    javax.swing.JTextField minutos;
    
    public ConexionServidor(Socket socket, javax.swing.JTextField hora, javax.swing.JTextField minutos) {
        this.socket = socket;
        this.hora = hora;
        this.minutos = minutos;
        try {
            this.salidaDatos = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear el stream de salida : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no se creo correctamente. ");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.mensaje = hora.getText() + ":" + minutos.getText();
            salidaDatos.writeUTF(mensaje);
            mensaje = "";
        } catch (IOException ex) {
            System.out.println("Error al intentar enviar un mensaje: " + ex.getMessage());
        }
    }

}