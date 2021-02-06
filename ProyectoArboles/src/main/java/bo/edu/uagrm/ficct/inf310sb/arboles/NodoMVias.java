package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.LinkedList;
import java.util.List;

public class NodoMVias<K,V> {
   private List<K> listaDeClaves;
   private List<V> listaDeValores;
   private  List<NodoMVias<K,V>> listaDeHijos;


   public NodoMVias(int orden){
       listaDeHijos = new LinkedList<>();
       listaDeClaves = new LinkedList<>();
       listaDeValores = new LinkedList<>();
       for (int i = 0; i < orden - 1; i++ ){
           listaDeHijos.add(NodoMVias.nodoVacio());
           listaDeClaves.add((K) NodoMVias.datoVacio());
           listaDeValores.add((V) NodoMVias.datoVacio());
       }
       listaDeHijos.add(NodoMVias.nodoVacio());
   }

   public NodoMVias(int orden, K primeraClave, V primerValor){
       this(orden);
       this.listaDeClaves.set(0, primeraClave);
       this.listaDeValores.set(0, primerValor);
   }

   public static NodoMVias nodoVacio(){
       return null;
   }

   public static Object datoVacio() {
       return null;
   }

    /**
     * Retorna la clave de la posicion indicada por el parametro posicion.
     * PRE- Condicion: El parámetro posición indica una posición válida
     * en el arreglo de la lista de claves
     * @param posición
     * @return
     */
    public K getClave(int posición){
        return this.listaDeClaves.get(posición);
    }

    public void setClave(int posición, K clave){
        this.listaDeClaves.set(posición,clave);
    }

    public V getValor(int posición){
        return this.listaDeValores.get(posición);
    }

    public void setValor(int posición, V valor){
        this.listaDeValores.set(posición, valor);
    }

    public  NodoMVias<K, V> getHijo(int posición){
        return this.listaDeHijos.get(posición);
    }

    public void setHijo(int posición, NodoMVias<K,V> nodo){
        this.listaDeHijos.set(posición, nodo);
    }

    public static boolean esNodoVacio(NodoMVias nodo){
        return nodo == NodoMVias.nodoVacio();
    }

    public boolean esClaveVacia(int posición){
        return this.listaDeClaves.get(posición) == NodoMVias.datoVacio();
    }
    public boolean esHijoVacio(int posición){
        return this.listaDeHijos.get(posición) == NodoMVias.nodoVacio();
    }

    public boolean esHoja(){
        for(int i = 0; i < this.listaDeHijos.size(); i++){
           if(!this.esHijoVacio(i)){
               return false;
           }
        }
        return true;
    }

    public boolean estanClavesLlenas(){
        for(int i = 0; i < this.listaDeClaves.size(); i++){
            if(this.esClaveVacia(i)){
                return false;
            }
        }
        return true;
    }

    public int cantidadDeClavesNoVacias(){
        int cantidad = 0;
        for(int i = 0; i < this.listaDeClaves.size(); i++){
            if(!this.esClaveVacia(i)){
                cantidad ++;
            }
        }
        return cantidad;
    }

    public int cantidadDeHijosVacios(){
        int cantidad = 0;
        for(int i = 0; i < this.listaDeHijos.size(); i++){
            if(this.esHijoVacio(i)){
                cantidad ++;
            }
        }
        return cantidad;
    }

    public int cantidadDeHijosNoVacios(){
        int cantidad = 0;
        for(int i = 0; i < this.listaDeHijos.size(); i++){
            if(!this.esHijoVacio(i)){
                cantidad ++;
            }
        }
        return cantidad;
    }

}
