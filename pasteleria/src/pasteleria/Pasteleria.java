/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasteleria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author diego
 */
public class Pasteleria {

    static int beneficio = 0;
    static String ficherosalida = null;
    static int[][] datospasteleros;

    public static boolean es_factible(int solucion_parcial[], int intento, int hasta) {
        for (int i = 1; i < solucion_parcial.length; i++) {
            if (solucion_parcial[i] == intento) {
                return false;
            }
        }
        return true;
    }

    
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
        int n = 0, m = 0;
        int datos_pasteleros[][] = null;
        int pastel[] = null;

        int solucion_mejor[] = null;

        if (args.length != 0) {

            if (args.length == 2) {
               
                ficherosalida = args[1];
            }
            
            
            File f = new File(args[0]);

            BufferedReader entrada;
            try {
                entrada = new BufferedReader(new FileReader(f));
                String linea = entrada.readLine();
                String trozos[] = linea.split(" ");
                
                n = Integer.parseInt(trozos[0]);                
                m = Integer.parseInt(trozos[1]);
                
                datos_pasteleros = new int[n + 1][m + 1];
                pastel = new int[n + 1];
                solucion_mejor = new int[n + 1];
                for (int i = 1; i < n + 1; i++) {
                    linea = entrada.readLine();
                    trozos = linea.split(" ");
                    for (int j = 1; j < m + 1; j++) {
                        datos_pasteleros[i][j] = Integer.parseInt(trozos[j - 1]);
                    }
                }
                linea = entrada.readLine();
                String aux[] = linea.split(" ");
                for (int i = 1; i < n + 1; i++) {
                    pastel[i] = Integer.parseInt(aux[i - 1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            int cota_superior = 0;

            for (int i = 1; i < n + 1; i++) { //Calculamos la primera solucion con la primera cota 
                solucion_mejor[i] = i; 
                cota_superior = cota_superior + datos_pasteleros[i][pastel[i]]; 
            }
            

            ArrayList<Nodo> listanodos = new ArrayList<Nodo>(); //Nodos vivos
            Nodo nodo = new Nodo(n, m, datos_pasteleros, pastel);
            nodo.setCota(); //Añadimos el primer nodo a los nodos vivos
            
            listanodos.add(nodo);

            while (!listanodos.isEmpty()) { 

                Nodo mas_prometedor = listanodos.remove(0);

                
                ArrayList<Nodo> complecciones = complecciones(mas_prometedor, n, m);
                for (Nodo hijo : complecciones) {
                    if (hijo.etapa == n) {
                        
                        if (hijo.cota > cota_superior) {
                           
                            imprime(hijo.solucion, hijo.pasteles);
                            

                            for (int i = 0; i < hijo.solucion.length; i++) {
                                solucion_mejor[i] = hijo.solucion[i];
                            }

                          
                            cota_superior = hijo.cota;
                        }
                    } else if (hijo.cota > cota_superior) {
                        listanodos.add(hijo);
                    } else {
                        
                    }
                }

                Collections.sort(listanodos);
            }
            beneficio = cota_superior;
            imprime(solucion_mejor, pastel);

        }
    }

    
    
    
    public static ArrayList<Nodo> complecciones(Nodo padre, int n, int m) {
        ArrayList<Nodo> hijos = new ArrayList<Nodo>();

        for (int pastelero = 1; pastelero <= n; pastelero++) {
            int datos_pasteleros[][] = new int[n + 1][m + 1];
            int pastel[] = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    datos_pasteleros[i][j] = padre.datos_pasteleros[i][j];
                }
            }

            for (int i = 1; i < n + 1; i++) {
                pastel[i] = padre.pasteles[i];
            }
            Nodo nuevo_nodo = new Nodo(padre.n, padre.m, datos_pasteleros, pastel);

            for (int i = 0; i < padre.solucion.length; i++) {
                nuevo_nodo.solucion[i] = padre.solucion[i];
            }

            nuevo_nodo.etapa = padre.etapa + 1;
            if (es_factible(nuevo_nodo.solucion, pastelero, nuevo_nodo.etapa - 1)) {
                nuevo_nodo.solucion[nuevo_nodo.etapa] = pastelero;
                nuevo_nodo.setCota();
                hijos.add(nuevo_nodo);
            }
        }
        return hijos;
    }

    
    
    
    public static void imprime(int sol[], int pastel[]) throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter(ficherosalida, "UTF-8");
        for (int i = 1; i < pastel.length; i++) {           
            writer.println(i + "- del tipo: " + pastel[i] + " asignado a " + sol[i]);
        }
        writer.println("Beneficio: " + beneficio);
        writer.close();

    }
}
