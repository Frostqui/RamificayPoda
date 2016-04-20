/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasteleria;

/**
 *
 * @author diego
 */


public class Nodo implements Comparable {
	int n=0, m=0;
	int datos_pasteleros [][] =null;
	int pasteles [] = null;
	//Que pastelero para cada pastel
	int solucion [] = null;
	int cota = 0;
	int etapa = 0;
	
	Nodo (int n, int m, int datos_pasteleros [][], int pasteles[]) {
		this.n = n;
		this.m = m;
		this.datos_pasteleros = datos_pasteleros;
		this.pasteles = pasteles;
		this.solucion = new int [n+1];
	}
	public static int dame_mejor (int datos_pasteleros[][], int tipo_pastel,int n) {
		int mejor_pastelero = 1;
		for (int i=1; i<=n;i++) {
			if (datos_pasteleros[i][tipo_pastel]>datos_pasteleros[mejor_pastelero][tipo_pastel]) {
				mejor_pastelero = i;
			}
		}
		return mejor_pastelero;
	}
	
	public void setCota () {
		int beneficio = 0;
		
		//Calculamos real
		for (int i=1; i<=etapa;i++) {
			beneficio = beneficio + datos_pasteleros[solucion[i]][pasteles[i]];
		}
		//Calculamos lo que queda por asignar considerando algo muy muy bueno aunque sea imposible. Por ejemplo, asignar el mejor de los beneficios que la tabla
		//Cuando mas mejoremos la cota mejor sera nuestra poda
		//Es como calcula: Como mucho, a cuando de beneficio podría llegar esto
		for (int i=etapa+1; i<=n;i++) {
			//Dame el mejor pastelero para ese tipo de pastel, aunque este ocupado
			int mejor_para_ese_pastel = dame_mejor (datos_pasteleros, pasteles[i],n);
			beneficio = beneficio + datos_pasteleros[mejor_para_ese_pastel][pasteles[i]];
		}
		
		this.cota = beneficio;
	}
	
	@Override
	public int compareTo(Object o) {
		Nodo otroNodo = (Nodo) o;
		if (this.cota<otroNodo.cota) 
			return -1;
		else if (this.cota>otroNodo.cota) 
			return 1;
		else
			return 0;
	}
	
}













