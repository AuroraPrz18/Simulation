
package congruencialmixto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

public class Prueba_Series {

    ArrayList<Double> vec_sub;
    int m_fo[][];
	
    public Prueba_Series()
    {

    }
	
    public boolean getSeries(ArrayList<Double> vec,int n,int periodo, double alfa){
		
        double fei=0,x02=0,xn2=0;
        int N=periodo;
        vec_sub=new ArrayList<Double>(n);
        m_fo=new int[n][n];

        fei=(N-1)/Math.pow(n, 2);
        
        for(int i=0;i<n;i++){
            vec_sub.add(i, (double)i/n);
        }
        
        for(int i=0;i<N-1;i++){
            ubicar(vec.get(i),vec.get(i+1),n);
        }
		
        //Obtener chi02
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                x02+=Math.pow(m_fo[i][j]-fei,2);
            }
        }
        
        System.out.println("Frecuencias observadas obtenidas");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(m_fo[i][j]+" ");
            }
            System.out.println("");
        }

        x02*=Math.pow(n, 2)/(N-1);
  
        ChiSquaredDistribution c= new ChiSquaredDistribution(n*n-1);
        double chi=c.inverseCumulativeProbability(1-alfa);
        
        System.out.println("\nX02: "+x02);
        System.out.println("X02 con la que se compara: "+chi+"\n");
        
        return x02<chi;
    }
	
    public void ubicar(double a,double b,int n){
        int aux1=-1,aux2=-1;
        for(int i=0;i<n-1;i++){
            if(a>=vec_sub.get(i) && a<vec_sub.get(i+1))
                aux1=i;	
            else if(i==n-2 && a>=vec_sub.get(i+1) && a<=1)
                aux1=i+1;
            if(b>=vec_sub.get(i) && b<vec_sub.get(i+1))
                aux2=i;
            else if(i==n-2 && b>=vec_sub.get(i+1) && b<=1)
               aux2=i+1;
        }
        m_fo[aux1][aux2]++;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
             ArrayList<Double> arr = new ArrayList<Double>();
        BufferedReader csvReader = new BufferedReader(new FileReader("Salida_Series.csv"));
        String numero = "";
        numero = csvReader.readLine();
        while (numero != null) {
            arr.add(Double.parseDouble(numero));
            numero = csvReader.readLine();
        }
        csvReader.close(); 
        
        Prueba_Series pruebaSe=new Prueba_Series();
        
        boolean res=pruebaSe.getSeries(arr, 10, arr.size(), 0.05);
        
        if(res)
            System.out.println("No se puede rechazar la hipótesis de que la muestra proviene de una distribucion uniforme");
        else
            System.out.println("Se acepta la hipotesis alterna");
        
        
    }
    
}
