package congruencialmixto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

/**
 *
 * @author Aurora
 */
public class PruebaKolmogorovSmirnov {
    public static void main(String[] args) throws IOException {
        ArrayList<Double> valores = new ArrayList<Double>();
        //double fe=0, fo=0, x0=0, intervalos=5, alpha = 1 - 0.05, gradosLibertad = intervalos - 1;
        
        //Lee los datos del archivo CSV - Generar n numeros pseudoaleatorios uniformes
        BufferedReader csvReader = new BufferedReader(new FileReader("Salida.csv"));
        String numero = "";
        numero = csvReader.readLine();
        while (numero != null && !numero.equals("")) {
            valores.add(Double.parseDouble(numero));
            numero = csvReader.readLine();
        }
        csvReader.close();
        double tamano = (double)valores.size();
        // Ordenar dichos numeros en orden ascendente
        Collections.sort(valores);
        
        // Calcular la distribución acumulada de los numeros generados
        Double Fn[] = new Double[(int)tamano+5];
        for(int i=0; i<tamano; i++){
            Fn[i]=(double)(i+1)/(double)tamano;
        }
        
        // Calcular el estadístico Kolmogorov-Smirnov
        double DnMax=0;
        for(int i=0; i<tamano; i++){
            double Dn = Math.abs(Fn[i] - valores.get(i));
            DnMax = Math.max(DnMax, Dn);
        }
        double d = 1.36 / Math.sqrt(tamano);
        if(tamano==100) d =0.134;
        
        System.out.println("Para esta prueba asegurese de ");
        System.out.println("Dn maxima = "+DnMax);
        System.out.println("d con nivel de significancia de 5% = "+d);
        
        // Validar hipotesis
        if(DnMax < d){
            System.out.println("No se puede rechazar la hipótesis de que la muestra proviene de una distribucion uniforme");
        } else{
            System.out.println("Se acepta la hipótesis alternativa");
        }
    }
}
