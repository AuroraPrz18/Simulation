package congruencialmixto;

import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.math3.distribution.NormalDistribution;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Karyme Nava
 */
public class PruebaPromedios {
    public static void main(String[] args) throws IOException {
        ArrayList<Double> valores = new ArrayList<Double>();
        double x=0, z0, z;
        //Lee los datos del archivo CSV
        NormalDistribution normal=new NormalDistribution();
        BufferedReader csvReader = new BufferedReader(new FileReader("Salida.csv"));
        String numero = "";
        numero = csvReader.readLine();
        while (numero != null && !numero.equals("")) {
            valores.add(Double.parseDouble(numero));
            numero = csvReader.readLine();
        }
        csvReader.close();
        
        //Promedio
        for(Double val : valores){
            x+=val;
        }
        double tamano = (double)valores.size();
        x/=tamano;
        System.out.println("Promedio = " + x);
        
        //Calcula Z0
        z0=(x - 0.5) * (double)Math.sqrt(tamano);
        z0/= (double)Math.sqrt((double)1.0/(double)12.0);
        System.out.println("Z0 = " + z0);
        
        //Calcula Z
        z=normal.inverseCumulativeProbability(1.0-(0.05/2.0));
        System.out.println("Z = " + z);
        
        if(Math.abs(z0) < z){
            System.out.println("Se acepta la hipótesis nula");
        } else{
            System.out.println("Se acepta la hipótesis alternativa");
        }
    }
}
