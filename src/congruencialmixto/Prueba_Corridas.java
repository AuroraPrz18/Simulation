
package congruencialmixto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

public class Prueba_Corridas {

    public boolean getcorridas_p(ArrayList<Double> datos,double alfa,int periodo)
    {
		
        double fei=0,x02=0;
        int R[],bits[],N=periodo,flag=0,aux=0,ultimo=0,Z=0;
        
        bits=new int[periodo];
        getbin(datos,bits,N); //Generar arreglo de binarios
        int n=(N+1)/2; //Corridas esperadas
        
        R=new int[n];
        
        for(int j=0;j<N;j++){
            flag=bits[j];

            aux=j;
            ultimo=j;
            Z=-1;
            do{
                Z++;
                aux++;						
                if(aux>=N-1)//Controlar posiciones
                    break;
            }while(bits[aux]!=flag);
            
            if(Z>=n)
                Z=n;
            R[Z]++;
            
        }// fin for
        
        if(ultimo!=N-1)
                R[N-1-ultimo]++;
        
        for(int i=0;i<n;i++){
            if(R[i]!=0){ 
                fei=(N-i+3.0)/(Math.pow(2.0,i+1.0));
                x02+=Math.pow(R[i]-fei,2.0)/fei;
                //System.out.println(i+" fei: "+fei+" x02: "+x02);
            }
        }
        
        //Imprimir longitud de corridas
        for(int i=0; i<R.length; i++){
            if(R[i]!=0)
                System.out.println("Cantidad de corridas para una longitud de "+i+" :"+R[i]);
        }
        
        ChiSquaredDistribution c= new ChiSquaredDistribution(n-1);
        double chi=c.inverseCumulativeProbability(1-alfa);
        
        System.out.println("\nX02: "+x02);
        System.out.println("X02 con la que se compara: "+chi);
        
        return x02<chi;
        
    }
	
	public void getbin(ArrayList<Double> a,int b[],int N){
            for(int i=0;i<N;i++){ // a[]-> datos b[]->bits N-> Cantidad datos
                if(a.get(i)<0.5)
                    b[i]=0;
                else
                    b[i]=1;
		}
	}
    public static void main(String[] args) throws FileNotFoundException, IOException {
       ArrayList<Double> arr = new ArrayList<Double>();
        BufferedReader csvReader = new BufferedReader(new FileReader("Salida_Corridas.csv"));
        String numero = "";
        numero = csvReader.readLine();
        while (numero != null) {
            arr.add(Double.parseDouble(numero));
            numero = csvReader.readLine();
        }
        csvReader.close();  
       
        Prueba_Corridas pruebaCo=new Prueba_Corridas();
        
        boolean res=pruebaCo.getcorridas_p(arr,0.05,arr.size());
        
        if(res)
            System.out.println("\nLa hipótesis nula no se puede rechazar");
        else
            System.out.println("\nSe acepta la hipótesis alterna");   
        
    }
    
}
