package bo.edu.uagrm.ficct.inf310sb.arboles;

import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcioClaveNoExiste;
import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionOrdenInvalido;

import java.util.Stack;

public class ArbolB <K extends Comparable<K>, V> extends ArbolMViasBusqueda<K,V>{
    private  int nroMaximoDeDatos;
    private  int nroMinimoDeDatos;
    private  int nroMinimoDeHijos;

    public ArbolB(){
        super();
        this.nroMaximoDeDatos = 2;
        this.nroMinimoDeDatos = 1;
        this.nroMinimoDeHijos = 2;

    }

    public ArbolB(int orden)  throws
            ExcepcionOrdenInvalido {
        super(orden);
        this.nroMaximoDeDatos = super.orden - 1;
        this.nroMinimoDeDatos = this.nroMaximoDeDatos / 2;
        this.nroMinimoDeHijos = this.nroMinimoDeDatos + 1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave  a eliminar no puede ser nula");
        }

        V valorARetornar = this.buscar(claveAInsertar);
        if(valorARetornar == null){
            throw new ExcepcioClaveNoExiste("La clave a eliminar no existe en el árbol");
        }
        if(super.esArbolVacio()){
            super.raiz = new NodoMVias<>(super.orden + 1, claveAInsertar, valorAInsertar);
            return;
        }

        Stack<NodoMVias <K,V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K,V> nodoActual = super.raiz;
        while(!NodoMVias.esNodoVacio(nodoActual)){
            int posicionClaveExiste = super.existeClaveEnNodo(nodoActual, claveAInsertar);
            if(posicionClaveExiste != POSICION_INVALIDA){
                nodoActual.setValor(posicionClaveExiste, valorAInsertar);
                nodoActual = NodoMVias.nodoVacio();
            }else {
                // que sea hoja
                if (nodoActual.esHoja()){
                    super.insertarDatosOrdenadosEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                    if (nodoActual.cantidadDeClavesNoVacias() > this.nroMinimoDeDatos){
                        this.dividir(nodoActual, pilaDeAncestros);
                    }
                    nodoActual = NodoMVias.nodoVacio();
                }else{
                    // cuando no es hoja
                    int posicionPorDondeBajar = super.porDondeBajar(nodoActual, claveAInsertar);
                    pilaDeAncestros.push(nodoActual);
                    nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                }
            }
        }

    }
// lo mas importante es el método dividir
    private void dividir(NodoMVias<K,V> nodoActual, Stack<NodoMVias<K,V>> pilaDeAncestros) {
    }

    @Override
    public V eliminar(K claveAEliminar) {
        if (claveAEliminar == null) {
            throw new IllegalArgumentException("Clave  a eliminar no puede ser nula");
        }

        Stack<NodoMVias <K,V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K, V> nodoActual = this.buscarNodoDeLaClave(claveAEliminar, pilaDeAncestros);

        if(NodoMVias.esNodoVacio(nodoActual)){
            throw new ExcepcioClaveNoExiste("La Clave no existe en el árbol");
        }
        int posicionDeLaCLaveEnElNodo = super.porDondeBajar(nodoActual, claveAEliminar) - 1;
        V valorARetornar = nodoActual.getValor(posicionDeLaCLaveEnElNodo);

        if(nodoActual.esHoja()) {
            super.eliminarDatosDeNodo(nodoActual, posicionDeLaCLaveEnElNodo);
            if (nodoActual.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos) {
                if (pilaDeAncestros.isEmpty()) {
                    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                        super.vaciar();
                    }
                } else {
                    this.prestarseOFusionarse(nodoActual, pilaDeAncestros);
                }
            }
        }else {
                // no es hoja
                pilaDeAncestros.push(nodoActual);
                NodoMVias<K,V> nodoDelPredecesor = this.buscarNodoPredecesor(pilaDeAncestros,
                        nodoActual.getHijo(posicionDeLaCLaveEnElNodo));
                int posicionDelPredecesor = nodoDelPredecesor.cantidadDeClavesNoVacias() - 1;
                K clavePredecesora = nodoDelPredecesor.getClave(posicionDelPredecesor);
                V valorPredecesora = nodoDelPredecesor.getValor(posicionDelPredecesor);
                super.eliminarDatosDeNodo(nodoDelPredecesor, posicionDelPredecesor);
                nodoActual.setClave(posicionDeLaCLaveEnElNodo, clavePredecesora);
                nodoActual.setValor(posicionDeLaCLaveEnElNodo, valorPredecesora);
                if(nodoDelPredecesor.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos){
                    this.prestarseOFusionarse(nodoDelPredecesor, pilaDeAncestros);
                }
        }

        return valorARetornar;

    }

    private NodoMVias<K,V> buscarNodoPredecesor(Stack<NodoMVias<K,V>> pilaDeAncestros, NodoMVias<K,V> hijo) {
       return null;
    }

    private void prestarseOFusionarse(NodoMVias<K,V> nodoActual, Stack<NodoMVias<K,V>> pilaDeAncestros) {
    }

    private NodoMVias<K,V> buscarNodoDeLaClave(K claveAEliminar, Stack<NodoMVias<K,V>> pilaDeAncestros) {
        return null;
    }
}
