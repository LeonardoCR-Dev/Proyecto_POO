//Francisco Javier Portillo Pineda
package Principal;

import Controlador.controladorMenu;
import Vista.AcercaDe;
import Vista.MenuPrincipal;

public class Estadistico2 {

    public static MenuPrincipal Menu = new MenuPrincipal();
    public static AcercaDe acerca = new AcercaDe();

    public static void main(String[] args) {
        controladorMenu controlador = new controladorMenu(Menu);       
        controlador.iniciar();
        Menu.setVisible(true);
    }
}
