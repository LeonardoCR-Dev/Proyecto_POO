//Francisco Javier Portillo Pineda
package Controlador;

import Modelo.EsNumero;
import Modelo.OperacionesEstadisticas;
import Principal.*;
import Vista.MenuPrincipal;
import Vista.RegresionMuestral;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class controladorRegrecion implements ActionListener {

    private ArrayList<Float> listaX = new ArrayList<Float>();
    private ArrayList<Float> listaY = new ArrayList<Float>();

    MenuPrincipal menu = new MenuPrincipal();
    private RegresionMuestral vistaRegrecion;
    OperacionesEstadisticas estadisticas = new OperacionesEstadisticas();
    Modelo.EsNumero esN = new EsNumero();
    private DefaultListModel modeloX = new DefaultListModel();
    private DefaultListModel modeloY = new DefaultListModel();
    private String eleSelectX = "";
    private String eleSelectY = "";

    public controladorRegrecion(RegresionMuestral vistaregrecion) {
        this.vistaRegrecion = vistaregrecion;
        this.vistaRegrecion.jButton_Insertar.addActionListener(this);
        this.vistaRegrecion.jButton_Borrar.addActionListener(this);
        this.vistaRegrecion.jButton_Limpiar.addActionListener(this);
        this.vistaRegrecion.jButton_Calcular.addActionListener(this);
        this.vistaRegrecion.jButton_Salir.addActionListener(this);
        this.vistaRegrecion.jList_X.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeloX.getSize() == 0) {
                } else {
                    eleSelectX = String.valueOf(vistaRegrecion.jList_X.getSelectedIndex());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.vistaRegrecion.jList_Y.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeloY.getSize() == 0) {
                } else {
                    eleSelectY = String.valueOf(vistaRegrecion.jList_Y.getSelectedIndex());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.vistaRegrecion.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Estadistico2.Menu.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public void iniciar() {
        vistaRegrecion.setIconImage(new ImageIcon(getClass().getResource("/Recursos/calculadora.png")).getImage());
        vistaRegrecion.setLocationRelativeTo(null);
        vistaRegrecion.setVisible(true);
        vistaRegrecion.jList_X.setModel(modeloX);
        vistaRegrecion.jList_Y.setModel(modeloY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaRegrecion.jButton_Insertar) {
            if (vistaRegrecion.jTextField_X.getText().equals("") || vistaRegrecion.jTextField_Y.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No puedes ingresar valores nulos");
            } else {
                if (esN.esNumero(vistaRegrecion.jTextField_X.getText()) == true && esN.esNumero(vistaRegrecion.jTextField_Y.getText())) {
                    modeloX.addElement(vistaRegrecion.jTextField_X.getText());
                    modeloY.addElement(vistaRegrecion.jTextField_Y.getText());
                    vistaRegrecion.jTextField_X.setText("");
                    vistaRegrecion.jTextField_Y.setText("");
                    vistaRegrecion.jTextField_X.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo puedes ingresar numeros");
                    vistaRegrecion.jTextField_X.setText("");
                    vistaRegrecion.jTextField_Y.setText("");
                }
            }
        }
        if (e.getSource() == vistaRegrecion.jButton_Borrar) {
            if (eleSelectX.equals("")) {
            } else {
                modeloX.remove(Integer.parseInt(eleSelectX));
                eleSelectX = "";
            }
            if (eleSelectY.equals("")) {
            } else {
                modeloY.remove(Integer.parseInt(eleSelectY));
                eleSelectY = "";
            }
        }
        if (e.getSource() == vistaRegrecion.jButton_Limpiar) {
            modeloX.clear();
            modeloY.clear();
            vistaRegrecion.jTextField_Resultado.setText("");
            vistaRegrecion.jTextField_X.setText("");
            vistaRegrecion.jTextField_Y.setText("");
            vistaRegrecion.jTextField_VarInd.setText("");
        }
        if (e.getSource() == vistaRegrecion.jButton_Calcular) {
            if (modeloX.size() == modeloY.size()) {
                if (modeloX.size() == 0 || modeloY.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No puedes calcular si las listas estan vacias");
                } else if (vistaRegrecion.jTextField_VarInd.getText().equals("")) {

                } else {
                    for (int j = 0; j < modeloX.size(); j++) {
                        listaX.add(j, Float.parseFloat((String) modeloX.getElementAt(j)));
                        listaY.add(j, Float.parseFloat((String) modeloY.getElementAt(j)));
                    }
                    vistaRegrecion.jTextField_Resultado.setText(String.valueOf(estadisticas.GenRegresion(listaX, listaY, Float.parseFloat(vistaRegrecion.jTextField_VarInd.getText()))));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Para poder calular la REGRESION MUESTRAL \n Debes igualar la cantidad de elementos \n entre la lista X y \n Tamaño lista X: " + modeloX.size() + " Tamaño lista Y: " + modeloY.size());
            }
        }
        if (e.getSource() == vistaRegrecion.jButton_Salir) {
            vistaRegrecion.dispose();
            Estadistico2.Menu.setVisible(true);
        }
    }

}
