package congruencialmixto;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

/**
 *
 * @author Aurora
 */
public class PruebaDeFrecuencias {
    public static void main(String[] args) throws IOException {
        ArrayList<Double> valores = new ArrayList<Double>();
        double fe=0, fo=0, x0=0, intervalos=5, alpha = 0.05, gradosLibertad = intervalos - 1;
        
        //Lee los datos del archivo CSV
        BufferedReader csvReader = new BufferedReader(new FileReader("Salida.csv"));
        String numero = "";
        numero = csvReader.readLine();
        while (numero != null && !numero.equals("")) {
            valores.add(Double.parseDouble(numero));
            numero = csvReader.readLine();
        }
        csvReader.close();
        
        // Frecuencia esperada
        double tamano = (double)valores.size();
        fe = tamano / intervalos;
        System.out.println("Fe = " + fe);
        
        // Frecuencia observada
        Double FOArr[] = new Double[(int)intervalos + 5];
        for(int i=1; i<=intervalos; i++){
            FOArr[i]=0.0;
        }
        double sub = 1.0;
        Collections.sort(valores);
        for(Double val : valores){
            if(val <= sub/intervalos){
                FOArr[(int)sub]+=1.0;
            }else{// se requiere cambio de intervalo
                while(val > sub/intervalos)sub+=1.0; // considerando los casos donde en un intervalo no hay ningun dato
                FOArr[(int)sub]+=1.0;
            }
        }
        
        // Obtener chi_cuadrada
        System.out.print("Fo = ");
        for(int i=1; i<=intervalos; i++){
            double x = ((FOArr[i] - fe) * (FOArr[i] - fe)) / fe; 
            x0 += x;
            System.out.print(FOArr[i] + ", ");
        }
        System.out.println();
        
        // Obtener la chi cuadrada con la que compararemos
        ChiSquaredDistribution chi = new ChiSquaredDistribution(gradosLibertad, alpha);
        double x02 = chi.cumulativeProbability(1);//????
        x02 = 9.49;
        
        
        System.out.println("X0 = " + x0);
        System.out.println("X0 con la que se compara = " + x02+ " con nivel de significancia = "+ alpha + " y " + gradosLibertad + " grados de libertad");
        
        if(x0 < x02){
            System.out.println("No se puede rechazar la hipótesis de que la muestra proviene de una distribucion uniforme");
        } else{
            System.out.println("Se rechaza la hipótesis de que la muestra proviene de una distribucion uniforme");
        }
    }
}
