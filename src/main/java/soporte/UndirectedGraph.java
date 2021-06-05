package soporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class UndirectedGraph<T> extends Graph<T> 
{
    /**
     * Crea un grafo no dirigido, con lista de vértices vacía, lista de arcos 
     * vacía y sin permitir arcos paralelos.
     */
    public UndirectedGraph() 
    {
    }
    
    /**
     * Crea un grafo no dirigido con lista de vértices vacía y lista de arcos 
     * vacía. El grafo permite arcos paralelos si el parámetro p es true, y no 
     * los permite si p es false.
     * @param p true: se permiten arcos paralelos.
     */
    public UndirectedGraph(boolean p)
    {
        super(p);
    }
            

    /**
     * Crea un grafo no dirigido cuya lista de vértices será <b>v</b> y cuya 
     * lista de arcos será <b>a</b>, sin permitir arcos paralelos. El método no 
     * controla si las listas de entrada contienen objetos válidos. Si alguna de 
     * las dos listas de entrada es null, la lista correspondiente se creará 
     * vacía.
     * @param v la lista de vértices a almacenar en el grafo.
     * @param a la lista de arco a almacenar en el grafo.
     */
    public UndirectedGraph(LinkedList< Node<T> > v, LinkedList< Arc<T> > a) 
    {
        super(v, a);
    }

    /**
     * Crea un grafo no dirigido cuya lista de vértices será <b>v</b> y cuya 
     * lista de arcos será <b>a</b>. El parámetro p indica si el grafo aceptará 
     * arcos paralelos (p = true) o no (p = false). El método no controla si las 
     * listas de entrada contienen objetos válidos. Si alguna de las dos listas 
     * de entrada es null, la lista correspondiente se creará vacía.
     * @param v la lista de vértices a almacenar en el grafo.
     * @param a la lista de arco a almacenar en el grafo.
     * @param p true: el grafo acepta arcos paralelos.
     */
    public UndirectedGraph(LinkedList< Node<T> > v, LinkedList< Arc<T> > a, boolean p) 
    {
        super(v, a, p);
    }
   
    /**
     * Crea un arco no dirigido con in como primer vértice y en como segundo 
     * vértice. El peso del arco será w. No comprueba si las referencias in y en
     * son null.
     * @param in el vértice inicial.
     * @param en el vértice final. 
     * @param w el peso del arco
     * @return el arco creado.
     */
    @Override
    public Arc<T> createArc(Node <T> in, Node <T> en, int w)
    {
        return new UndirectedArc(in, en, w);
    }

    @Override
    public String recorrerEnProfundidad() {
        return this.recorrer(1);
    }
    
    public String recorrer(int tipoVisita) {
        StringBuilder sbRet = new java.lang.StringBuilder();
        this.visitados = new HashMap<>();
        
        this.vertices.forEach(nodo -> {
            if(!visitados.containsKey(nodo))
                if (tipoVisita == 1)
                    sbRet.append(visitarEnProfundidadIterativo(nodo));
                else
                    sbRet.append(visitarEnAmplitud(nodo));
            //sbRet.append(this.visitarNodoOld(nodo, tipoVisita));
               
        });
        return sbRet.toString();
    }
    
    private String visitarEnProfundidadIterativo(Node nodo){
        StringBuilder sbRet = new java.lang.StringBuilder();
        Stack<Node> pila = new Stack();
        
        pila.push(nodo);
        while(!pila.empty()){
            Node nodoAux = pila.pop();
            sbRet.append(nodoAux.getValue()).append("-");
            
            this.visitados.put(nodoAux, true);

            Iterator it = nodoAux.getArcs().iterator();
            while (it.hasNext()){
                Arc arco = (Arc) it.next();

                if(arco.getInit().equals(nodoAux)){
                    if(!this.visitados.containsKey(arco.getEnd())){
                        pila.push(arco.getEnd());
                        this.visitados.put(arco.getEnd(), true);
                        //sbRet.append(arco.getEnd().getValue());
                    }
                }
            }
        }
        return sbRet.toString();
    }
    
    private String visitarEnProfundidad(Node nodo){
        StringBuilder sbRet = new java.lang.StringBuilder();
        if(!this.visitados.containsKey(nodo)){
            this.visitados.put(nodo, true);
            sbRet.append(nodo.getValue());
            
            Iterator it = nodo.getArcs().iterator();
            while (it.hasNext()){
                Arc arco = (Arc) it.next();
                    
                if(arco.getInit().equals(nodo)){
                    sbRet.append(visitarEnProfundidad(arco.getEnd()));
                }
                else{
                    sbRet.append(visitarEnProfundidad(arco.getInit()));
                }
            }
        }
        return sbRet.toString();
    }

    @Override
    public String recorrerEnAmplitud() {
        return recorrer(0);
    }

    private String visitarEnAmplitud(Node<T> nodo) {
        StringBuilder sbRet = new java.lang.StringBuilder();
        Queue<Node> cola = new LinkedList<>();
        
        cola.offer(nodo);
        while(!cola.isEmpty()){
            Node nodoAux = cola.poll();
            this.visitados.put(nodoAux, true);
            sbRet.append(nodoAux.getValue()).append("-");

            Iterator it = nodoAux.getArcs().iterator();
            while (it.hasNext()){
                Arc arco = (Arc) it.next();

                if(arco.getInit().equals(nodoAux) && !this.visitados.containsKey(arco.getEnd())){
                    //sbRet.append(visitarNodo(arco.getEnd(), tipoVisita));
                    cola.offer(arco.getEnd());
                    this.visitados.put(arco.getEnd(), true);
                }
                else if(!this.visitados.containsKey(arco.getInit())){
                    //sbRet.append(visitarNodo(arco.getInit(), tipoVisita));
                    cola.offer(arco.getInit());
                    this.visitados.put(arco.getInit(), true);
                }
            }
        }
        return sbRet.toString();
    }

    @Override
    public int contarComponentesConexas() {
        int cant = 0;
        this.visitados = new HashMap<>();
        
        for(Node nodo : this.vertices) /*this.vertices.forEach(nodo ->*/ {
            if(!visitados.containsKey(nodo)){
                cant++;
                visitarEnAmplitud(nodo);
            }
            //sbRet.append(this.visitarNodoOld(nodo, tipoVisita));
               
        }
        
        return cant;
    }
}

