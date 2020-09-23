//Francisco Javier Portillo Pineda
package Modelo;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class OperacionesEstadisticas {

    public float Promediolista(ArrayList vector) {
        float sumatoria = 0;
        float promedio = 0;
        for (int i = 0; i < vector.size(); i++) {
            sumatoria += (float) vector.get(i);
        }
        promedio = sumatoria / vector.size(); 
        return promedio;
    }
    
    public float Desviacionlista(ArrayList vector) {       
        float prom, sum = 0;
        int i, n = vector.size();
        prom = Promediolista(vector);
        for (i = 0; i < n; i++) {
            sum += Math.pow( (float) vector.get(i) - prom, 2);
        }
        float Desviacion = (float) Math.sqrt(sum / (double) n);
        return Desviacion;
    }

    public double varianza(ArrayList vector){
        float prom, sum = 0;
        int i, n = vector.size();
        prom = Promediolista(vector);
        for(i = 0; i < n; i++){
            float resta = (float) vector.get(i)-prom;            
            sum += resta*resta;            
        }                
        float varianza = (float) sum / n;
        return varianza;               
    }
    public float GenRegresion(ArrayList modelox, ArrayList modeloy, float varInd) {      
        float SumatoriaArriba = 0.00f;
        float SumatoriaAbajo = 0.00f;
        float divicion = 0.00f;
        float a = 0.00f;
        float y = 0.00f;
        for (int i = 0; i < modelox.size(); i++) {
            SumatoriaAbajo += (((float) modelox.get(i) - Promediolista(modelox)) * ((float) modelox.get(i) - Promediolista(modelox)));
            SumatoriaArriba += (((float) modelox.get(i) - Promediolista(modelox)) * ((float) modeloy.get(i) - Promediolista(modeloy)));
        }
        divicion = SumatoriaAbajo / SumatoriaAbajo;
        a = (Promediolista(modeloy)) - ((divicion) * Promediolista(modelox));
        y = ((a) + (divicion) * (varInd));
        return y;
    }

    public float GenCorrelacion(ArrayList modelox, ArrayList modeloy) {
        float SumatoriaArriba = 0.00f;
        float divicion = 0.00f;
        float a = 0.00f;
        float y = 0.00f;

        for (int i = 0; i < modelox.size(); i++) {
            SumatoriaArriba += (((float) modelox.get(i) - Promediolista(modelox)) * ( (float) modelox.get(i) - Promediolista(modeloy)));
        }
        divicion = SumatoriaArriba / ((modelox.size()) * (Desviacionlista(modelox)) * (Desviacionlista(modeloy)));
        return divicion;
    }

    public float GenCovarianza(ArrayList modelox, ArrayList modeloy) {
        String correlacion = "";
        float SumatoriaArriba = 0.00f;
        float covarianza = 0.00f;
        float a = 0.00f;
        float y = 0.00f;

        for (int i = 0; i < modelox.size(); i++) {
            SumatoriaArriba += (((float) modelox.get(i) - Promediolista(modelox)) * ((float) modeloy.get(i) - Promediolista(modeloy)));
        }
        float cov = (float) ((1.00 / modelox.size()) * (SumatoriaArriba));

        return cov;
    }
}
