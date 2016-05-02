/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

/**
 *
 * @author diego
 */
public class Practica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       System.out.println("\nInvertido = " + invertir(37654,0));
    }
    
     public static int invertir(int n,int m) { 
      int invertido; 
      if (n == 0) { 
         invertido = m; 
      } else { 
         invertido = invertir(n / 10, m * 10 + n % 10); 
      } 
      return invertido; 
   }
     
     public static int combinatoria(int n, int k){
         
         if( k == 0 && n==k){
             return 1;
             
             
         }
         
         else return combinatoria()
         
     
     }
    
    
    
}


