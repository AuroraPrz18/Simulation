package congruencialmixto;

import com.csvreader.CsvWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Karyme Nava
 */
public class CongruencialMixto0a1 {
    public int semilla;
    public int multiplicador;
    public int constanteAditiva;
    public int modulo;
    
    public int siguienteValor(int n){
        return ((multiplicador * n + constanteAditiva) % modulo);
    }
    
    public ArrayList<Integer> valores(int n){
        ArrayList<Integer> valores = new ArrayList<Integer>();
        int act=semilla;
        for(int i=0; i<n; i++){
            valores.add(act);
            act=siguienteValor(act);
        }
        return valores;
    }
    
    public ArrayList<Integer> valores(){
        ArrayList<Integer> valores = new ArrayList<Integer>();
        int act=siguienteValor(semilla);
        valores.add(semilla);
        while(semilla!=act){
            valores.add(act);
            act=siguienteValor(act);
        }
        return valores;
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        CongruencialMixto0a1 obj=new CongruencialMixto0a1();
        //Datos de entrada
        obj.semilla = 3;
        System.out.println("Semilla  = " + obj.semilla);
        obj.multiplicador = 13;
        System.out.println("Multiplicador = " + obj.multiplicador);
        obj.constanteAditiva = 11;
        System.out.println("Constante aditiva = " + obj.constanteAditiva);
        obj.modulo = 43000;
        int m = obj.modulo;
        System.out.println("Modulo = " + obj.modulo);
        
        //Congeuncial mixto
        ArrayList<Integer> valores = obj.valores();
        
        //Calcula y guarda los valores aleatorios
        CsvWriter csvW = new CsvWriter("Salida1.csv");
        for(Integer val : valores){
            String [] datos = {((((double)val)/((double)m))+"")};
            csvW.writeRecord(datos);
        }
        csvW.close();
        System.out.println("\nLongitud del periodo = " + valores.size());
    }
}
