/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

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
        double []P = new double[1001];
        double []FO = new double[1001];
        double []FE = new double[1001];
        double x0 = 0.0, x;
        double n = 14.0;
        double alpha = 1 - 0.05, gradosLibertad = n;
        
        //Lee los datos del archivo CSV
        BufferedReader csvReader = new BufferedReader(new FileReader("SalidaPruebaDistanciaDigitos.csv"));
        String numero = "";
        numero = csvReader.readLine();
        while (numero != null && !numero.equals("")) {
            valores.add(numero.substring(2, numero.length()));
            numero = csvReader.readLine();
        }
        csvReader.close();
        
        double p=0;
        for(int i=0; i<n; i++){
            P[i] = 0.1*Math.pow(0.9, i);
            FE[i] = valores.size()*P[i];
            FO[i] = 0.0;
            p+=P[i];
        }
        
        FO[(int)n] = 0.0;
        P[(int)n] = 1.0-p;
        FE[(int)n] = valores.size()*P[(int)n];
        /*for(int i=(int)n; i<=1000; i++){
            P[(int)n]+=0.1*Math.pow(0.9, i);
            FE[(int)n]+=valores.size()*P[i];
        }*/
        
        char inicio;
        for(int i=0; i<valores.size(); i++){
            inicio = valores.get(i).charAt(0);
            for(int k=1; k<=n; k++){
                if(valores.get(i).charAt(k) == inicio){
                    FO[k-1]++;
                    break;
                } 
                if(k==n) FO[k]++;
            }
        }
       
        for(int i=0; i<=n; i++){
            x0+=Math.pow(FO[i]-FE[i], 2.0)/FE[i];
        }
        
        ChiSquaredDistribution chi = new ChiSquaredDistribution(gradosLibertad);
        x = chi.inverseCumulativeProbability(alpha);
        
        System.out.println("X02 = " + x0);
        System.out.println("X2 con la que se compara = " + x + " con nivel de significancia = "+ (Math.round(100.0*(1 - alpha))/100.0) + " y " + gradosLibertad + " grados de libertad");

        if(x0 < x){
            System.out.println("No se puede rechazar la hipótesis de que la muestra proviene de una distribución uniforme");
        } else{
            System.out.println("Se acepta la hipótesis alternativa");
        }
    }
}
