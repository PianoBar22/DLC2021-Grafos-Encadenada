package interfaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import soporte.*;

/**
 * Una clase para contener un main de prueba para la implementación de grafos
 * por listas de adyancencia.
 * 
 * @author Ing. Valerio Frittelli.
 * @version Marzo de 2014.
 */
public class Principal 
{
    public static void main(String args[])
    {
        /*
        UndirectedGraph <String> ug1 = new UndirectedGraph<>();
        ug1.add("a");
        ug1.add("b");
        ug1.add("c");
        ug1.add("d");
        ug1.addArc("a", "b", 2);
        ug1.addArc("a", "b", 2);  // paralelo: no debe permitirlo...
        ug1.addArc("b", "c", 3);
        ug1.addArc("b", "d", 1);
        ug1.addArc("d", "c", 4);
        System.out.println("Grafo 1 (no dirigido - sin arcos paralelos: ");
        System.out.println(ug1);
        System.out.println();
                
        UndirectedGraph <String> ug2 = new UndirectedGraph<>(true);
        ug2.add("a");
        ug2.add("b");
        ug2.add("c");
        ug2.add("d");
        ug2.addArc("a", "b", 2);
        ug2.addArc("a", "b", 2);  // paralelo: debe permitirlo... 
        ug2.addArc("b", "c", 3);
        ug2.addArc("b", "d", 1);
        ug2.addArc("d", "c", 4);
        System.out.println("Grafo 2 (no dirigido - con arcos paralelos: ");
        System.out.println(ug2);        
        System.out.println();

        DirectedGraph <String> dg1 = new DirectedGraph<>();
        dg1.add("a");
        dg1.add("b");
        dg1.add("c");
        dg1.add("d");
        dg1.addArc("a", "b", 2);
        dg1.addArc("a", "b", 2);  // paralelo: no debe permitirlo...
        dg1.addArc("c", "b", 3);
        dg1.addArc("b", "d", 1);
        dg1.addArc("d", "c", 4);
        System.out.println("Grafo 3 (dirigido - sin arcos paralelos: ");
        System.out.println(dg1);
        System.out.println();

        DirectedGraph <String> dg2 = new DirectedGraph<>(true);
        dg2.add("a");
        dg2.add("b");
        dg2.add("c");
        dg2.add("d");
        dg2.addArc("a", "b", 2);
        dg2.addArc("a", "b", 2);  // paralelo: debe permitirlo...
        dg2.addArc("c", "b", 3);
        dg2.addArc("b", "d", 1);
        dg2.addArc("d", "c", 4);
        System.out.println("Grafo 4 (dirigido - con arcos paralelos: ");
        System.out.println(dg2);
        
        DirectedGraph <String> dg3 = null;
        try
        {
            dg3 = (DirectedGraph<String>)dg2.clone();
        }
        catch(CloneNotSupportedException e)
        {
        }
        System.out.println("Grafo 5 (dirigido - con arcos paralelos [clonado del grafo 4]: ");
        System.out.println(dg3);
*/
        
        UndirectedGraph <String> ug2 = new UndirectedGraph<>(true);
        ug2.add("A");//1
        ug2.add("B");//2
        ug2.add("C");//3
        ug2.add("D");//4
        ug2.add("E");//5
        ug2.add("F");//6
        ug2.add("G");//7
        ug2.add("H");//8
        
        ug2.addArc("A", "B");
        ug2.addArc("A", "C");
        ug2.addArc("B", "C");
        ug2.addArc("B", "E");
        ug2.addArc("B", "D");
        ug2.addArc("B", "B");
        ug2.addArc("C", "G");
        ug2.addArc("C", "E");
        ug2.addArc("C", "H");
        ug2.addArc("D", "E");
        ug2.addArc("E", "F");
        ug2.addArc("G", "H");
        
        System.out.println("Grafo no dirigido: ");
        System.out.println(ug2);
        System.out.println("En profundidad");
        System.out.println(ug2.recorrerEnProfundidad());
        System.out.println("En Amplitud");
        System.out.println(ug2.recorrerEnAmplitud());
        System.out.println("Cantidad Componentes conexas");
        System.out.println(ug2.contarComponentesConexas());
        
        UndirectedGraph <String> ug3 = new UndirectedGraph<>(true);
        
        ug3.add("A");
        ug3.add("B");
        ug3.add("C");
        ug3.add("D");
        ug3.add("E");
        ug3.add("F");
        ug3.add("G");
        ug3.add("H");
        ug3.add("I");
        
        ug3.addArc("A", "B");
        ug3.addArc("A", "D");
        ug3.addArc("B", "C");
        ug3.addArc("B", "D");
        ug3.addArc("C", "D");
        ug3.addArc("C", "E");
        ug3.addArc("D", "E");
        ug3.addArc("F", "G");
        ug3.addArc("F", "H");
        ug3.addArc("G", "H");
        
        System.out.println("Grafo no dirigido (para recorrer profe): ");
        System.out.println(ug3);
        System.out.println("En profundidad");
        System.out.println(ug3.recorrerEnProfundidad());
        System.out.println("En Amplitud");
        System.out.println(ug3.recorrerEnAmplitud());
        System.out.println("Cantidad Componentes conexas");
        System.out.println(ug3.contarComponentesConexas());
        
        UndirectedGraph <String> ug4 = new UndirectedGraph<>(true);
        File myObj = new File("C:\\UTN\\DLC\\graph\\graph.txt");
        
        try (Scanner myReader = new Scanner(myObj)) {
            while(myReader.hasNextLine()){
                Scanner line = new Scanner(myReader.nextLine());
                Iterator it = line.useDelimiter(" ");
                if(it.hasNext()){
                    String strNodo = (String) it.next();
                    ug4.add(strNodo);
                }
            }
            
            Scanner myReader2 = new Scanner(myObj);
            while(myReader2.hasNextLine()){
                Scanner line = new Scanner(myReader2.nextLine());
                Iterator it = line.useDelimiter(" ");
                if(it.hasNext()){
                    String strNodoInit = (String) it.next();
                    while(it.hasNext()){
                        String strNodoFin = (String) it.next();
                        if(Integer.parseInt(strNodoFin) > Integer.parseInt(strNodoInit)){
                            ug4.addArc(strNodoInit, strNodoFin);
                        }
                    }
                }
            }
            
            System.out.println("Grafo no dirigido (desafio): ");
            System.out.println(ug4);
            System.out.println("En profundidad");
            System.out.println(ug4.recorrerEnProfundidad());
            System.out.println("En Amplitud");
            System.out.println(ug4.recorrerEnAmplitud());
            System.out.println("Cantidad Componentes conexas");
            System.out.println(ug4.contarComponentesConexas());
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        DirectedGraph <String> dg1 = new DirectedGraph<>();
        dg1.add("a");
        dg1.add("b");
        dg1.add("c");
        dg1.add("d");
        dg1.add("e");
        dg1.add("f");
        
        dg1.addArc("a", "b", 2);
        dg1.addArc("b", "c", 2);  // paralelo: no debe permitirlo...
        dg1.addArc("d", "b", 3);
        dg1.addArc("e", "d", 1);
        dg1.addArc("e", "f", 4);
        dg1.addArc("f", "d", 4);
        dg1.addArc("f", "c", 4);
        System.out.println("Grafo 3 (dirigido - sin arcos paralelos: ");
        System.out.println(dg1);
        System.out.println();
    }
}
