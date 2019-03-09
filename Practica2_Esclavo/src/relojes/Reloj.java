package relojes;

import java.util.*;
import javax.swing.JOptionPane;
import java.net.*;
import java.io.*;

public class Reloj extends javax.swing.JFrame{

    BufferedReader entrada,teclado;
    PrintStream salida;
    String Reloj1;
    Socket socket;
    int puerto;
    String host;
    RelojHilo contador;
      
       
    public Reloj() {
        initComponents();
        setTitle("Reloj Esclavo");
        // Ventana de configuracion inicial
        VentanaConfiguracion vc = new VentanaConfiguracion(this);
        host = vc.getHost();
        puerto = vc.getPuerto();
        System.out.println("Te quieres conectar a " + host + " en el puerto " + puerto);
        this.setVisible(true);
        // Se crea el socket para conectar con el Sevidor del Chat
        try {
            socket = new Socket(host, puerto);
        } catch (UnknownHostException ex) {
            System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        } catch (IOException ex) {
            System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        }
        javax.swing.JTextField nada = new javax.swing.JTextField("");
        // Accion para el boton enviar
        btnPedir.addActionListener(new ConexionServidor(socket, nada, nada));
        btnMandar.addActionListener(new ConexionServidor(socket, hora,minutos));
    }
    
    public void recibirMensajesServidor(){
        // Obtiene el flujo de entrada del socket
        DataInputStream entradaDatos = null;
        String mensaje;
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear el stream de entrada: " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no se creo correctamente. ");
        }
        contador = new RelojHilo(this.Lreloj);
        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {
            try {
                mensaje = entradaDatos.readUTF();
                System.out.println(mensaje);
                
                String[] numeros;
                numeros = mensaje.split(":");
                contador.reloj1.setHora(Integer.parseInt(numeros[0]));
                contador.reloj1.setMinuto(Integer.parseInt(numeros[1]));
                contador.reloj1.setSegundo(Integer.parseInt(numeros[2]) + 1);
            } catch (IOException ex) {
                System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                System.out.println("El socket no se creo correctamente. ");
                conectado = false;
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Lreloj = new javax.swing.JLabel();
        btnPedir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        hora = new javax.swing.JTextField();
        minutos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnMandar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 51));
        setResizable(false);

        Lreloj.setFont(new java.awt.Font("Sitka Text", 1, 48)); // NOI18N
        Lreloj.setText("12:00:00");

        btnPedir.setText("Pedir hora");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Cambiar hora:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText(":");

        btnMandar.setText("Cambiar hora");
        btnMandar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMandarActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minutos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnPedir)
                        .addGap(18, 18, 18)
                        .addComponent(btnMandar)))
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Lreloj, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Lreloj)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPedir)
                    .addComponent(btnMandar))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMandarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMandarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMandarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lreloj;
    private javax.swing.JButton btnMandar;
    private javax.swing.JButton btnPedir;
    private javax.swing.JTextField hora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTextField minutos;
    // End of variables declaration//GEN-END:variables
      
        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reloj().setVisible(true);
            }
        });*/
        
        Reloj reloj = new Reloj();
        reloj.recibirMensajesServidor();
    }
    
}

