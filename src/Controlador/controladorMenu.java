//Francisco Javier Portillo Pineda
package Controlador;

import Principal.Estadistico2;
import Vista.CorrelacionLineal;
import Vista.Covarianza;
import Vista.Desviacion;
import Vista.MenuPrincipal;
import Vista.RegresionMuestral;
import Vista.Varianza;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class controladorMenu implements ActionListener {
    
    private MenuPrincipal vistaMenu;
    
    public controladorMenu(MenuPrincipal vistaMenu) {
        this.vistaMenu = vistaMenu;        
        this.vistaMenu.jButton_Varianza.addActionListener(this);
        this.vistaMenu.jButton_Desviasion.addActionListener(this);
        this.vistaMenu.jButton4.addActionListener(this);
        this.vistaMenu.jButton_Correlacion.addActionListener(this);
        this.vistaMenu.jButton_Covarianza.addActionListener(this);
        this.vistaMenu.jButton_Regresion.addActionListener(this);
        this.vistaMenu.jMenuItem_Correlacion.addActionListener(this);
        this.vistaMenu.jMenuItem_Covarianza.addActionListener(this);
        this.vistaMenu.jMenuItem_Regresion.addActionListener(this);
        this.vistaMenu.jMenuItem_Salir.addActionListener(this);
        this.vistaMenu.jMenuItem_acerca.addActionListener(this);
        this.vistaMenu.jMenuItem_Varianza.addActionListener(this);
        this.vistaMenu.jMenuItem_Desviasion.addActionListener(this);
    }
    
    public void iniciar() {
        vistaMenu.setIconImage(new ImageIcon(getClass().getResource("/Recursos/calculadora.png")).getImage());
        vistaMenu.setLocationRelativeTo(null);
        vistaMenu.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaMenu.jButton4) {
            System.exit(0);
        }
        if(e.getSource() == vistaMenu.jButton_Varianza){
            Varianza vistaVarianza = new Varianza();
            controladorVarianza controladorVarianza = new controladorVarianza(vistaVarianza);
            controladorVarianza.iniciar();
            vistaMenu.dispose();
            vistaVarianza.setVisible(true);
        }
        if(e.getSource() == vistaMenu.jButton_Desviasion){
            Desviacion vistaDesviacion = new Desviacion();
            controladorDesviacion controladorDesviacion = new controladorDesviacion(vistaDesviacion);
            controladorDesviacion.iniciar();
            vistaMenu.dispose();
            vistaDesviacion.setVisible(true);
        }
        if (e.getSource() == vistaMenu.jButton_Correlacion) {
            CorrelacionLineal vistaCorrelacion = new CorrelacionLineal();
            controladorCorrelacion controladorCorre = new controladorCorrelacion(vistaCorrelacion);
            controladorCorre.iniciar();
            vistaMenu.dispose();
            vistaCorrelacion.setVisible(true);
        }
        if (e.getSource() == vistaMenu.jButton_Covarianza) {
            Covarianza vistaCovarianza = new Covarianza();
            controladorCovarianza controladorCovarianza = new controladorCovarianza(vistaCovarianza);
            controladorCovarianza.iniciar();
            vistaMenu.dispose();
            vistaCovarianza.setVisible(true);
        }
        if (e.getSource() == vistaMenu.jButton_Regresion) {
            RegresionMuestral vistaregrecion = new RegresionMuestral();
            controladorRegrecion controladorRe = new controladorRegrecion(vistaregrecion);
            controladorRe.iniciar();
            vistaMenu.dispose();
            vistaregrecion.setVisible(true);
        }
        if(e.getSource() == vistaMenu.jMenuItem_Desviasion){
            vistaMenu.jButton_Desviasion.doClick();
        }
        if(e.getSource() == vistaMenu.jMenuItem_Varianza){
            vistaMenu.jButton_Varianza.doClick();
        }
        if (e.getSource() == vistaMenu.jMenuItem_Salir) {
            vistaMenu.jButton4.doClick();
        }
        if (e.getSource() == vistaMenu.jMenuItem_Correlacion) {
            vistaMenu.jButton_Correlacion.doClick();
        }
        if (e.getSource() == vistaMenu.jMenuItem_Covarianza) {
            vistaMenu.jButton_Covarianza.doClick();
        }
        if (e.getSource() == vistaMenu.jMenuItem_Regresion) {
            vistaMenu.jButton_Regresion.doClick();
        }
        if (e.getSource() == vistaMenu.jMenuItem_acerca) {
            Estadistico2.acerca.setLocationRelativeTo(null);
            Estadistico2.acerca.setVisible(true);            
        }
    }
    
}
