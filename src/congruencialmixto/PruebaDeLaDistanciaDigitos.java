/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congruencialmixto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

/**
 *
 * @author Karyme Nava
 */
public class PruebaDeLaDistanciaDigitos {
    public static void main(String[] args) throws IOException {
        ArrayList<String> valores = new ArrayList<String>();
        double []P = new double[100];
        double []FO = new double[100];
        double []FE = new double[100];
        double x0 = 0.0, x;
        double n = 6.0;
        double alpha = 1 - 0.05, gradosLibertad = n - 1.0;
        int []pos = new int[12];
        
        //Lee los datos del archivo CSV
        BufferedReader csvReader = new BufferedReader(new FileReader("Salida.csv"));
        String numero = "";
        numero = csvReader.readLine();
        while (numero != null && !numero.equals("")) {
            valores.add(numero.substring(2, numero.length()));
            numero = csvReader.readLine();
        }
        csvReader.close();
        
        for(int i=0; i<=n; i++){
            P[i] = 0.1*Math.pow(0.9, i);
            FE[i] = valores.size()*P[i];
            FO[i] = 0.0;
        }
        
        for(int i=0; i<valores.size(); i++){
            for(int k=0; k<=10; k++) pos[k] = -1;
            for(int k=0; k<n; k++){
                if(pos[(valores.get(i).charAt(k))-'0']>=0){
                    FO[(k-1)-pos[(valores.get(i).charAt(k))-'0']]++;
                    break;
                }
                pos[(valores.get(i).charAt(k))-'0']=k;
                if(k==(n-1)) FO[0]++;
            }
        }
        
        for(int i=0; i<=n; i++){
            x0+=Math.pow(FO[i]-FE[i], 2.0)/FE[i];
            System.out.println("*"+i+" "+FO[i]+" "+FE[i]);
        }
        
        ChiSquaredDistribution chi = new ChiSquaredDistribution(gradosLibertad);
        x = chi.inverseCumulativeProbability(alpha);
        
        System.out.println("X02 = " + x0);
        System.out.println("X02 con la que se compara = " + (Math.round(100.0*x)/100.0) + " con nivel de significancia = "+ (Math.round(100.0*(1 - alpha))/100.0) + " y " + gradosLibertad + " grados de libertad");

        if(x0 < x){
            System.out.println("No se puede rechazar la hipótesis de que la muestra proviene de una distribucion uniforme");
        } else{
            System.out.println("Se acepta la hipótesis alternativa");
        }
    }
}
