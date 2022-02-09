package congruencialmixto;

import com.csvreader.CsvWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Aurora
 */
public class CongruenciaMixtaDe0A1Version2 {
    double a, ca, m, semilla, Xn;

    CongruenciaMixtaDe0A1Version2(double semilla, double a, double ca, double m){
      //solo numeros entre 0 y 1
      this.semilla = semilla; //x0
      this.a = a; // Multiplicador
      this.ca = ca; // Constante aditiva
      this.m = m; // Modulo
      this.Xn = semilla;
    }

    public double next(){
      Xn = ((a*Xn + ca) % m); 
      return (Xn/m);
    }

    public List getNValues(int n){
      List values =  new ArrayList(n);
      for(int i = 0; i<n; i++){
        values.add(next());
      }
      return values;
    }
    public ArrayList getAll(){
      ArrayList values =  new ArrayList();
      double Xn2 = semilla;
      do{
        Xn2 = ((a*Xn2 + ca) % m);
        values.add((Xn2/m));
      }while(Xn2!=semilla);
      return values;
    }
    public static void main(String args[]) throws IOException {
      Scanner sc = new Scanner(System.in);
      
      double x1, x2, x3, x4;
      System.out.print("Ingrese semilla: ");
      x1 = sc.nextDouble(); //x0
      System.out.println("");
      System.out.print("Ingrese Multiplicador: ");
      x2 = sc.nextDouble(); // Multiplicador
      System.out.println("");
      System.out.print("Ingrese Constante aditiva: ");
      x3 = sc.nextDouble(); // Constante aditiva
      System.out.println("");
      System.out.print("Ingrese Modulo: ");
      x4 = sc.nextDouble(); // Modulo      
      System.out.println("");

      
      /*System.out.println("Imprimir primeros 4 valores: "+ random.getNValues(4));
      System.out.println("Imprimir todos los valores: "+ random.getAll());
      System.out.println("Imprimir el siguiente: "+ random.next()); // En donde el contador lo dejo el getNValues
      System.out.println("Imprimir el siguiente: "+ random.next());
      System.out.println("Imprimir el siguiente: "+ random.next());
      System.out.println("Longitud: "+ (random.getAll()).size());*/
      
        //Congeuncial mixto
        CongruenciaMixtaDe0A1Version2 random = new CongruenciaMixtaDe0A1Version2(x1, x2, x3, x4);
        ArrayList<Double> valores = random.getAll();
        
        //Calcula y guarda los valores aleatorios
        CsvWriter csvW = new CsvWriter("Salida.csv");
        for(Double val : valores){
            String [] datos = {(val+"")};
            csvW.writeRecord(datos);
        }
        csvW.close();
        System.out.println("\nLongitud del periodo = " + valores.size());
    }
}