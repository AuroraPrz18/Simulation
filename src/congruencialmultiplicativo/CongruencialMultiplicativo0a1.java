package congruencialmultiplicativo;

import com.csvreader.CsvWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Karyme Nava
 */
public class CongruencialMultiplicativo0a1 {
    public int semilla;
    public int multiplicador;
    public int modulo;
    
    public int siguienteValor(int n){
        return ((multiplicador * n) % modulo);
    }
    
    public ArrayList<Integer> valores(int n){
        ArrayList<Integer> valores = new ArrayList<Integer>();
        int ant=semilla, act;
        for(int i=0; i<n; i++){
            act=siguienteValor(ant);
            valores.add(act);
            ant=act;
        }
        return valores;
    }
    
    public ArrayList<Integer> valores(){
        ArrayList<Integer> valores = new ArrayList<Integer>();
        int val1=semilla, val2=siguienteValor(semilla);
        int ant=siguienteValor(val2);
        int act=siguienteValor(ant);
        valores.add(val1);
        if(val1!=val2) valores.add(val2);
        while(val1!=ant || val2!=act){
            valores.add(ant);
            ant=act;
            act=siguienteValor(ant);
        }
        return valores;
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        CongruencialMultiplicativo0a1 obj=new CongruencialMultiplicativo0a1();
        System.out.print("Semilla  = ");
        int x0= sc.nextInt();
        obj.semilla = x0;
        System.out.print("Multiplicador = ");
        obj.multiplicador = sc.nextInt();
        System.out.print("Modulo = ");
        int m = sc.nextInt();
        obj.modulo = m;
        
        //Congruencial multiplicativo
        ArrayList<Integer> valores = obj.valores();
        
        //Calcula y guarda los valores aleatorios
        CsvWriter csvW = new CsvWriter("Salida2.csv");
        for(Integer val : valores){
            String [] datos = {((((double)val)/((double)m))+"")};
            csvW.writeRecord(datos);
        }
        csvW.close();
        System.out.println("\nLongitud del periodo = " + valores.size());
    }   
}
