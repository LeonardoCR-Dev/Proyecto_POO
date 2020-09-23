package Controlador;

import Modelo.EsNumero;
import Modelo.OperacionesEstadisticas;
import Principal.Estadistico2;
import Vista.Desviacion;
import Vista.MenuPrincipal;
import Vista.Varianza;
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

public class controladorVarianza implements ActionListener {

    private ArrayList<Float> lista = new ArrayList<Float>();

    MenuPrincipal menu = new MenuPrincipal();
    private Varianza vistaVarianza;
    OperacionesEstadisticas estadisticas = new OperacionesEstadisticas();
    Modelo.EsNumero esN = new EsNumero();
    private DefaultListModel modeloX = new DefaultListModel();
    private String eleSelectX = "";

    public controladorVarianza(Varianza vistaVarianza) {
        this.vistaVarianza = vistaVarianza;

        this.vistaVarianza.jButton_INSERTAR.addActionListener(this);
        this.vistaVarianza.jButton_BORRAR.addActionListener(this);
        this.vistaVarianza.jButton_LIMPIAR.addActionListener(this);
        this.vistaVarianza.jButton_CALCULAR.addActionListener(this);
        this.vistaVarianza.jButton5.addActionListener(this);
        this.vistaVarianza.jList_X.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeloX.getSize() == 0) {
                } else {
                    eleSelectX = String.valueOf(vistaVarianza.jList_X.getSelectedIndex());
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
        this.vistaVarianza.addWindowListener(new WindowListener() {
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
        vistaVarianza.setIconImage(new ImageIcon(getClass().getResource("/Recursos/calculadora.png")).getImage());
        vistaVarianza.setLocationRelativeTo(null);
        vistaVarianza.setVisible(true);
        vistaVarianza.jList_X.setModel(modeloX);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaVarianza.jButton_INSERTAR) {
            if (vistaVarianza.jTextField_X.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No puedes ingresar valores nulos");
            } else {
                if (esN.esNumero(vistaVarianza.jTextField_X.getText()) == true) {
                    modeloX.addElement(vistaVarianza.jTextField_X.getText());
                    vistaVarianza.jTextField_X.setText("");
                    vistaVarianza.jTextField_X.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo puedes ingresar numeros");
                    vistaVarianza.jTextField_X.setText("");
                }
            }
        }
        if (e.getSource() == vistaVarianza.jButton_BORRAR) {
            if (eleSelectX.equals("")) {
            } else {
                modeloX.remove(Integer.parseInt(eleSelectX));
                eleSelectX = "";
            }
        }
        if (e.getSource() == vistaVarianza.jButton_LIMPIAR) {
            modeloX.clear();
            vistaVarianza.jTextField_X.setText("");
            vistaVarianza.jTextField_RESULTADOS.setText("");
        }
        if (e.getSource() == vistaVarianza.jButton_CALCULAR) {
            if (modeloX.size() == 0) {
                JOptionPane.showMessageDialog(null, "No puedes calcular si las listas estan vacias");
            } else {
                Object[] arreglox = new Object[modeloX.size()];
                String[] arreglostringx = new String[arreglox.length];
                float[] arregloNumx = new float[arreglostringx.length];
                for (int i = 0; i < arreglox.length; i++) {
                    arreglox[i] = modeloX.getElementAt(i);
                }
                for (int j = 0; j < arreglox.length; j++) {
                    arreglostringx[j] = arreglox[j].toString();
                }
                for (int k = 0; k < arreglostringx.length; k++) {
                    lista.add(k, Float.parseFloat(arreglostringx[k]));
                }
                vistaVarianza.jTextField_RESULTADOS.setText(String.valueOf(estadisticas.varianza(lista)));
            }
        }
        if (e.getSource() == vistaVarianza.jButton5) {
            vistaVarianza.dispose();
            Estadistico2.Menu.setVisible(true);
        }
    }

}
