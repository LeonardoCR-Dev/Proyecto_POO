//Francisco Javier Portillo Pineda
package Controlador;

import Modelo.EsNumero;
import Modelo.OperacionesEstadisticas;
import Principal.Estadistico2;
import Vista.Covarianza;
import Vista.MenuPrincipal;
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

public class controladorCovarianza implements ActionListener {

    private ArrayList<Float> listaX = new ArrayList<Float>();
    private ArrayList<Float> listaY = new ArrayList<Float>();
    MenuPrincipal menu = new MenuPrincipal();
    private Covarianza vistaCovarianza;
    OperacionesEstadisticas estadisticas = new OperacionesEstadisticas();
    Modelo.EsNumero esN = new EsNumero();
    private DefaultListModel modeloX = new DefaultListModel();
    private DefaultListModel modeloY = new DefaultListModel();
    private String eleSelectX = "";
    private String eleSelectY = "";

    public controladorCovarianza(Covarianza vistaCovarianza) {
        this.vistaCovarianza = vistaCovarianza;
        this.vistaCovarianza.jButton_INSERTAR.addActionListener(this);
        this.vistaCovarianza.jButton_BORRAR.addActionListener(this);
        this.vistaCovarianza.jButton_LIMPIAR.addActionListener(this);
        this.vistaCovarianza.jButton_CALCULAR.addActionListener(this);
        this.vistaCovarianza.jButton_SALIR.addActionListener(this);
        this.vistaCovarianza.jList_X.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeloX.getSize() == 0) {
                } else {
                    eleSelectX = String.valueOf(vistaCovarianza.jList_X.getSelectedIndex());
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
        this.vistaCovarianza.jList_Y.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeloY.getSize() == 0) {
                } else {
                    eleSelectY = String.valueOf(vistaCovarianza.jList_Y.getSelectedIndex());
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
        this.vistaCovarianza.addWindowListener(new WindowListener() {
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
        vistaCovarianza.setIconImage(new ImageIcon(getClass().getResource("/Recursos/calculadora.png")).getImage());
        vistaCovarianza.setLocationRelativeTo(null);
        vistaCovarianza.setVisible(true);
        vistaCovarianza.jList_X.setModel(modeloX);
        vistaCovarianza.jList_Y.setModel(modeloY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCovarianza.jButton_INSERTAR) {
            if (vistaCovarianza.jTextField_X.getText().equals("") || vistaCovarianza.jTextField_Y.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No puedes ingresar valores nulos");
            } else {
                if (esN.esNumero(vistaCovarianza.jTextField_X.getText()) == true && esN.esNumero(vistaCovarianza.jTextField_Y.getText())) {
                    modeloX.addElement(vistaCovarianza.jTextField_X.getText());
                    modeloY.addElement(vistaCovarianza.jTextField_Y.getText());
                    vistaCovarianza.jTextField_X.setText("");
                    vistaCovarianza.jTextField_Y.setText("");
                    vistaCovarianza.jTextField_X.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo puedes ingresar numeros");
                    vistaCovarianza.jTextField_X.setText("");
                    vistaCovarianza.jTextField_Y.setText("");
                }
            }
        }
        if (e.getSource() == vistaCovarianza.jButton_BORRAR) {
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
        if (e.getSource() == vistaCovarianza.jButton_LIMPIAR) {
            modeloX.clear();
            modeloY.clear();
            vistaCovarianza.jTextField_X.setText("");
            vistaCovarianza.jTextField_Y.setText("");
            vistaCovarianza.jTextField_RESULTADOS.setText("");
        }
        if (e.getSource() == vistaCovarianza.jButton_CALCULAR) {
            if (modeloX.size() == modeloY.size()) {
                if (modeloX.size() == 0 || modeloY.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No puedes calcular si las listas estan vacias");
                } else {
                    for (int j = 0; j < modeloX.size(); j++) {
                        listaX.add(j, Float.parseFloat((String) modeloX.getElementAt(j)));
                        listaY.add(j, Float.parseFloat((String) modeloY.getElementAt(j)));
                    }
                    //  vistaRegrecion.jTextField_Resultado.setText(String.valueOf(estadisticas.GenRegresion(listaX, listaY, Float.parseFloat(vistaRegrecion.jTextField_VarInd.getText()))));                                    
                    vistaCovarianza.jTextField_RESULTADOS.setText(String.valueOf(estadisticas.GenCovarianza(listaX, listaY)));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Para poder calular la CORRELACION LINEAL \n Debes igualar la cantidad de elementos \n entre la lista X y \n Tamaño lista X: " + modeloX.size() + " Tamaño lista Y: " + modeloY.size());
            }
        }
        if (e.getSource() == vistaCovarianza.jButton_SALIR) {
            vistaCovarianza.dispose();
            Estadistico2.Menu.setVisible(true);
        }
    }
}
