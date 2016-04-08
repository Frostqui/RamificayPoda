import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Principal {
	public static boolean es_factible (int solucion_parcial [], int intento, int hasta) {
		for (int i=1; i<solucion_parcial.length; i++) {
			if (solucion_parcial[i]==intento)
				return false;
		}
		return true;
	}
	
	public static ArrayList<Nodo> getComplecciones (Nodo padre, int n, int m) {
		ArrayList<Nodo> hijos = new ArrayList<Nodo> ();
		
		for (int pastelero=1; pastelero<=n; pastelero++) {
			int datos_pasteleros [][]= new int [n+1][m+1];
			int pasteles []= new int [n+1];
			for (int i = 1; i<n+1; i++){
				for (int j = 1; j< m+1; j++) {
					datos_pasteleros[i][j] = padre.datos_pasteleros[i][j];
		        }
			}
			
			for (int i = 1; i<n+1; i++){
		    	pasteles[i] = padre.pasteles[i];
		    }
			Nodo nuevo = new Nodo (padre.n,padre.m, datos_pasteleros, pasteles);
			CopiarSolucion (padre.solucion, nuevo.solucion);
			
			nuevo.etapa = padre.etapa+1;
			if (es_factible (nuevo.solucion, pastelero, nuevo.etapa-1)) {
				nuevo.solucion[nuevo.etapa] = pastelero;
				nuevo.setCota();
				hijos.add (nuevo);
			}
		}
		return hijos;
	}
	
	public static void main (String args[]) {
		int n=0, m=0;
		int datos_pasteleros [][] =null;
		int pasteles [] = null;
		
		int solucion_mejor [] = null;
		
	
		//PASO 1: Leemos los datos del fichero de entrada
		File f = new File(args[0]);
		
		BufferedReader entrada;
		try {
			entrada = new BufferedReader( new FileReader( f ) );
			String linea = entrada.readLine();
			String trozos[] = linea.split(" ");
			//n son los pasteleros y las filas
			n= Integer.parseInt (trozos[0]);
			//m son los pasteles
			m= Integer.parseInt (trozos[1]);
			datos_pasteleros = new int [n+1][m+1];
			pasteles = new int [n+1];
			solucion_mejor = new int [n+1];
		    for (int i = 1; i<n+1; i++){
				linea = entrada.readLine();
				trozos = linea.split(" ");
	            for (int j = 1; j< m+1; j++) {
	            	datos_pasteleros[i][j] = Integer.parseInt(trozos[j-1]);
	            }
			}
		    linea = entrada.readLine();
		    String aux[] = linea.split(" ");
		    for (int i = 1; i<n+1; i++){
		    	pasteles[i] = Integer.parseInt(aux[i-1]);
		    }
		}catch (IOException e) {
			e.printStackTrace();
		}
		//PASO 2: Selecccionamos una solucion valida pero que no tiene porque ser la mejor
    	//La usaremos como cota inicial. En nuestro caso tomamos un pastelero para hacer cada pastel secuencialmente
    	int cota_superior = 0;
    	
    	for (int i=1; i<n+1; i++) { //Son n pasteles
    		solucion_mejor [i] =  i; //El pastel i lo hace el pastelero i
    		cota_superior = cota_superior + datos_pasteleros [i][pasteles[i]]; //Sumamos el beneficio de que el pastelero i haga el pastel del tipo del pastel numero i
    	}
    	System.out.println ("Beneficio inicial: "+ cota_superior);
    	System.out.println ("Solucion inicial");
    	imprimir_solucion (solucion_mejor, pasteles);
    	
    	
		ArrayList <Nodo> lista = new ArrayList<Nodo> ();
		Nodo nodo = new Nodo (n,m,datos_pasteleros, pasteles); 
		nodo.setCota();
		System.out.println ("Cota al empezar: "+ nodo.cota);
		lista.add(nodo);
		
		while (!lista.isEmpty()) {
		
			Nodo mas_prometedor = lista.remove(0);
			
			//Sacamos los nodos hijos
			ArrayList<Nodo> complecciones = getComplecciones (mas_prometedor,n,m);
			for (Nodo hijo: complecciones) {
				if (hijo.etapa==n) {
					System.out.println ("Solucion nueva con beneficio: "+hijo.cota+" y tenemos otra con beneficio"+cota_superior);
					
					if (hijo.cota>cota_superior) {
						System.out.println ("Actualizamos solucion por otra mejor, con mas beneficio");
						imprimir_solucion (hijo.solucion, hijo.pasteles);
						//Tenemos un hijo solucion, actualizamos
						CopiarSolucion (hijo.solucion, solucion_mejor);
						//Actualizamos la cota
						cota_superior = hijo.cota;
					}
				}else if (hijo.cota>cota_superior) {
					lista.add(hijo);
				}else {
					System.out.println ("Nodo rechazado, cota: "+hijo.cota+ " y cota superior: "+ cota_superior);
				}
			}
			
			Collections.sort(lista);
		}
		
		imprimir_solucion (solucion_mejor, pasteles);
	}
	
	public static void CopiarSolucion (int [] origen, int [] destino) {
		for (int i = 0; i< origen.length; i++){
			destino[i]=origen[i];
		}
	}
	
	public static void imprimir_solucion (int solucion[], int pasteles[]){
		for (int i = 1; i < pasteles.length; i++) {
			System.out.println ("Pastel numero "+i+" tipo: "+pasteles[i]+" asignado a "+solucion[i]);
		}
	}
}
