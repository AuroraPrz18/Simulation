package congruencialmixto;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Karyme Nava
 */
public class CongruencialMixto {
    public int semilla;
    public int multiplicador;
    public int constanteAditiva;
    public int modulo;
    
    public int siguienteValor(int n){
        return ((multiplicador * n + constanteAditiva) % modulo);
    }
    
    public ArrayList<Integer> valores(int n){
        ArrayList<Integer> valores = new ArrayList<Integer>();
        int ant=semilla, act;
        for(int i=0; i<n; i++){
            valores.add(ant);
            act=siguienteValor(ant);
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
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        CongruencialMixto obj=new CongruencialMixto();
        System.out.print("Semilla  = ");
        int x0= sc.nextInt();
        obj.semilla = x0;
        System.out.print("Multiplicador = ");
        obj.multiplicador = sc.nextInt();
        System.out.print("Constante aditiva = ");
        obj.constanteAditiva = sc.nextInt();
        System.out.print("Modulo = ");
        obj.modulo = sc.nextInt();
        int valor = obj.siguienteValor(x0);
        System.out.println("\nValor = " + valor);
        ArrayList<Integer> valoresN = obj.valores(5);
        System.out.println("\nN primeros valores:");
        for(Integer val : valoresN){
            System.out.print(val + " ");
        }
        System.out.println("");
        ArrayList<Integer> valores = obj.valores();
        System.out.println("\nValores:");
        int cont=0;
        for(Integer val : valores){
            cont++;
            System.out.print(val + " ");
            if((cont % 15) == 0) System.out.println("");
        }
        System.out.println("");
        System.out.println("\nLongitud del periodo = " + valores.size());
    }   
}
