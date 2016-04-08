/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ramificaypoda;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author diego
 */
public class Ramificaypoda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        FileWriter fichero = new FileWriter("C:\\Users\\diego\\Desktop\\dyv.txt");
        PrintWriter pw = new PrintWriter(fichero);
        pw.print("me cago en dios");

        try {

            if (null != fichero) {
                fichero.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }
}

    

