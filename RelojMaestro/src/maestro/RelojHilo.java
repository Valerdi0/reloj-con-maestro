/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maestro;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;

/**
 *
 * @author Rubén Guerra
 */
public class RelojHilo {
    
    Minutos reloj1;
    Minutos reloj2;
    Minutos reloj3;
    Minutos reloj4;

    public RelojHilo(JLabel reloj1, JLabel reloj2, JLabel reloj3, JLabel reloj4) {
        this.reloj1 = new Minutos(1, reloj1);
        this.reloj2 = new Minutos(2, reloj2);
        this.reloj3 = new Minutos(3, reloj3);
        this.reloj4 = new Minutos(4, reloj4);
        initComponents();
    }
    
    private void initComponents(){
        Calendar cal = new GregorianCalendar();
        Date fecha = new Date();
        cal.setTime(fecha);
        
        
        reloj1.setHora(cal.get(Calendar.HOUR_OF_DAY));
        reloj1.setMinuto(cal.get(Calendar.MINUTE));
        reloj1.setSegundo(cal.get(Calendar.SECOND));
        reloj1.setVelocidad(1000);
        
        
        reloj2.setHora((int) (Math.random() * 24));
        reloj2.setMinuto((int) (Math.random() * 60));
        reloj2.setVelocidad(1000);
        
        reloj3.setHora((int) (Math.random() * 24));
        reloj3.setMinuto((int) (Math.random() * 60));
        reloj3.setVelocidad(1000);
        
        reloj4.setHora((int) (Math.random() * 24));
        reloj4.setMinuto((int) (Math.random() * 60));
        reloj4.setVelocidad(1000);       
        
        reloj1.start();
        reloj2.start();
        reloj3.start();
        reloj4.start();
    }

}
