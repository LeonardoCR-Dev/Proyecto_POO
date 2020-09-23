//Francisco Javier Portillo Pineda
package Modelo;

public class EsNumero {
    
    public static boolean esNumero(String cadena) {
        try {
            Float.parseFloat(cadena);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
