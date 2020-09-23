//Francisco Javier Portillo Pineda
package Controlador;

import Modelo.EsNumero;
import Modelo.OperacionesEstadisticas;
import Principal.Estadistico2;
import Vista.CorrelacionLineal;
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

public class controladorCorrelacion implements ActionListener {

    private ArrayList<Float> listaX = new ArrayList<Float>();
    private ArrayList<Float> listaY = new ArrayList<Float>();
    MenuPrincipal menu = new MenuPrincipal();
    private CorrelacionLineal vistacorrelacion;
    OperacionesEstadisticas estadisticas = new OperacionesEstadisticas();
    Modelo.EsNumero esN = new EsNumero();
    private DefaultListModel modeloX = new DefaultListModel();
    private DefaultListModel modeloY = new DefaultListModel();
    private String eleSelectX = "";
    private String eleSelectY = "";

    public controladorCorrelacion(CorrelacionLineal vistacorrelacion) {
        this.vistacorrelacion = vistacorrelacion;
        this.vistacorrelacion.jButton_INSERTAR.addActionListener(this);
        this.vistacorrelacion.jButton_BORRAR.addActionListener(this);
        this.vistacorrelacion.jButton_LIMPIAR.addActionListener(this);
        this.vistacorrelacion.jButton_CALCULAR.addActionListener(this);
        this.vistacorrelacion.jButton5.addActionListener(this);
        this.vistacorrelacion.jList_X.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeloX.getSize() == 0) {
                } else {
                    eleSelectX = String.valueOf(vistacorrelacion.jList_X.getSelectedIndex());
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
        this.vistacorrelacion.jList_Y.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeloY.getSize() == 0) {
                } else {
                    eleSelectY = String.valueOf(vistacorrelacion.jList_Y.getSelectedIndex());
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
        this.vistacorrelacion.addWindowListener(new WindowListener() {
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
        vistacorrelacion.setIconImage(new ImageIcon(getClass().getResource("/Recursos/calculadora.png")).getImage());
        vistacorrelacion.setLocationRelativeTo(null);
        vistacorrelacion.setVisible(true);
        vistacorrelacion.jList_X.setModel(modeloX);
        vistacorrelacion.jList_Y.setModel(modeloY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistacorrelacion.jButton_INSERTAR) {
            if (vistacorrelacion.jTextField_X.getText().equals("") || vistacorrelacion.jTextField_Y.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No puedes ingresar valores nulos");
            } else {
                if (esN.esNumero(vistacorrelacion.jTextField_X.getText()) == true && esN.esNumero(vistacorrelacion.jTextField_Y.getText())) {
                    modeloX.addElement(vistacorrelacion.jTextField_X.getText());
                    modeloY.addElement(vistacorrelacion.jTextField_Y.getText());
                    vistacorrelacion.jTextField_X.setText("");
                    vistacorrelacion.jTextField_Y.setText("");
                    vistacorrelacion.jTextField_X.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo puedes ingresar numeros");
                    vistacorrelacion.jTextField_X.setText("");
                    vistacorrelacion.jTextField_Y.setText("");
                }
            }
        }
        if (e.getSource() == vistacorrelacion.jButton_BORRAR) {
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
        if (e.getSource() == vistacorrelacion.jButton_LIMPIAR) {
            modeloX.clear();
            modeloY.clear();
            vistacorrelacion.jTextField_X.setText("");
            vistacorrelacion.jTextField_Y.setText("");
            vistacorrelacion.jTextField_RESULTADOS.setText("");
        }
        if (e.getSource() == vistacorrelacion.jButton_CALCULAR) {
            if (modeloX.size() == modeloY.size()) {
                if (modeloX.size() == 0 || modeloY.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No puedes calcular si las listas estan vacias");
                } else {
                    for (int j = 0; j < modeloX.size(); j++) {
                        listaX.add(j, Float.parseFloat((String) modeloX.getElementAt(j)));
                        listaY.add(j, Float.parseFloat((String) modeloY.getElementAt(j)));
                    }
                    //vistaRegrecion.jTextField_Resultado.setText(String.valueOf(estadisticas.GenRegresion(listaX, listaY, Float.parseFloat(vistaRegrecion.jTextField_VarInd.getText()))));
                    vistacorrelacion.jTextField_RESULTADOS.setText(String.valueOf(estadisticas.GenCorrelacion(listaX, listaY)));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Para poder calular la CORRELACION LINEAL \n Debes igualar la cantidad de elementos \n entre la lista X y \n Tamaño lista X: " + modeloX.size() + " Tamaño lista Y: " + modeloY.size());
            }
        }
        if (e.getSource() == vistacorrelacion.jButton5) {
            vistacorrelacion.dispose();
            Estadistico2.Menu.setVisible(true);
        }
    }

}
