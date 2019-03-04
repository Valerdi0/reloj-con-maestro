/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojes;

import javax.swing.JLabel;

/**
 *
 * @author Rubén Guerra
 */
public class RelojHilo {
    
    Minutos reloj1;

    public RelojHilo(JLabel reloj1) {
        this.reloj1 = new Minutos(1, reloj1);
        initComponents();
    }
    
    private void initComponents(){
        reloj1.setHora((int) (Math.random() * 24));
        reloj1.setMinuto((int) (Math.random() * 60));
        reloj1.setSegundo((int) (Math.random() * 60));
        reloj1.setVelocidad(1000);   
        
        reloj1.start();
    }

}
