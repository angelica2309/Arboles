package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.*;

public class ArbolBinarioBusqueda<K extends Comparable<K>, V, recorrido> implements IArbolBusqueda<K, V> {

    protected NodoBinario<K, V> raiz;

    public ArbolBinarioBusqueda() {

    }

    /**
     * Instancia un arbol reconstruyendo en base a sus recorridos InOrden y (PreOrden o PostOrden).
     * si el parametro usandoPreOrden es verdadero, los parametros clavesNoInOrden y valoresNoInOrden
     * tendran el recorrido preOrden del arbol, caso contrario tendran el postOrden
     * @param clavesInOrden
     * @param valoresInOrden
     * @param clavesNoInOrden
     * @param valoresNoInOrden
     * @param usandoPreOrden
     */
    public ArbolBinarioBusqueda(List<K> clavesInOrden,List<V> valoresInOrden,
                                List<K> clavesNoInOrden,List<V> valoresNoInOrden,
                                boolean usandoPreOrden){
        if (clavesInOrden == null | clavesNoInOrden == null ||
                valoresInOrden == null || valoresNoInOrden == null ){
            throw  new IllegalArgumentException( "Los parámetros no pueden ser nulos");
        }
        if (clavesInOrden.isEmpty() || clavesNoInOrden.isEmpty() ||
                valoresInOrden.isEmpty() || valoresNoInOrden.isEmpty() ){
            throw  new IllegalArgumentException( "Los parámetros no pueden ser vacíos");
        }
        if (clavesInOrden.size() != clavesNoInOrden.size() ||
                clavesInOrden.size() != valoresInOrden.size() ||
                clavesInOrden.size() != valoresNoInOrden.size() ){
            throw  new IllegalArgumentException( "Los parámetros no pueden ser listas de diferentes tamaños");
        }
        if (usandoPreOrden) {
            this.raiz =  reconstruirConPreOrden(clavesInOrden,valoresInOrden,clavesNoInOrden,valoresNoInOrden);
        }else {
            this.raiz = reconstruirConPostOrden(clavesInOrden,valoresInOrden,clavesNoInOrden,valoresNoInOrden);
        }
    }


    private NodoBinario<K,V> reconstruirConPreOrden(List<K> clavesInOrden, List<V> valoresInOrden,
                                        List<K> clavesPreOrden, List<V> valoresPreOrden) {
        int posicionDeClavePadreEnPreOrden = 0;
       K clavePadre = clavesPreOrden.get(posicionDeClavePadreEnPreOrden);
        int posicionDeClavePadreEnInOrden = this.posicionDeClave(clavePadre, clavesInOrden);

        // para armar la rama izquierda
        List<K> claveInOrdenPorIzquierda = clavesInOrden.subList(0, posicionDeClavePadreEnInOrden);
        List<V> valoresInOrdenPorIzquierda = valoresInOrden.subList(0, posicionDeClavePadreEnInOrden);
        List<K> clavePreOrdenPorIzquierda = clavesPreOrden.subList(0, posicionDeClavePadreEnPreOrden);
        List<V> valoresPreOrdenPorIzquierda = valoresPreOrden.subList(0, posicionDeClavePadreEnPreOrden);

        // para armar la rama derecha


        //armando el nodo actual
    }

    private NodoBinario<K,V> reconstruirConPostOrden(List<K> clavesInOrden, List<V> valoresInOrden,
                                         List<K> clavesPostOrden, List<V> valoresPostOrden){
        return  ;
    }

    private int posicionDeClave(K claveABuscar,List<K> listaDeClaves){
        for (int i = 0; i < listaDeClaves.size(); i++){
            K claveActual = listaDeClaves.get(i);
            if (claveActual.compareTo(claveABuscar) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void vaciar() {
       this.raiz = (NodoBinario<K, V>) NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override
    public int size() {
        if (this.esArbolVacio()){
            return 0;
        }
        int cantidadDeNodos = 0;
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = pilaDeNodos.pop();
            cantidadDeNodos++;
            if(!nodoActual.esVacioHijoDerecho()){
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return cantidadDeNodos;
    }
    public int cantidadDeHijosDerechos(){
        return cantidadDeHijosDerechos(this.raiz);

    }

    private int cantidadDeHijosDerechos(NodoBinario<K,V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }

        int hdPorRamaIzquierda = cantidadDeHijosDerechos(nodoActual.getHijoIzquierdo());
        int hdPorRamaDerecha = cantidadDeHijosDerechos(nodoActual.getHijoDerecho());
        if(!nodoActual.esVacioHijoDerecho()){
            return hdPorRamaIzquierda + hdPorRamaDerecha+ 1;
        }
        return hdPorRamaIzquierda + hdPorRamaDerecha;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(NodoBinario<K,V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }

        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
        if(alturaPorIzquierda > alturaPorDerecha){
            return alturaPorIzquierda + 1;
        }
        return alturaPorDerecha + 1;
    }

    public int alturaIt(){

        if (this.esArbolVacio()){
            return 0;
        }
        int alturaDelArbol = 0 ;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()){
            int cantidadDeNodosEnLaCola = colaDeNodos.size();
            int i =0;
            while(i < cantidadDeNodosEnLaCola) {

                NodoBinario<K, V> nodoActual = colaDeNodos.poll();

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                i++;
            }
            alturaDelArbol ++;
        }
        return alturaDelArbol;
    }


    @Override
    public int nivel() {
        return nivel(this.raiz);
    }

    private int nivel(NodoBinario<K,V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)){
            return -1;
        }

        int nivelPorIzquierda = nivel(nodoActual.getHijoIzquierdo());
        int nivelPorDerecha = nivel(nodoActual.getHijoDerecho());
        if(nivelPorIzquierda > nivelPorDerecha){
            return nivelPorIzquierda + 1;
        }
        return nivelPorDerecha + 1;
    }

    public int nivelIt(){
        if (this.esArbolVacio()){
            return -1;
        }
        int nivelDelArbol = 0 ;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()){
            int cantidadDeNodosEnLaCola = colaDeNodos.size();
            int i = 0;
            while(i < cantidadDeNodosEnLaCola) {

                NodoBinario<K, V> nodoActual = colaDeNodos.poll();

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                i++;
            }
            nivelDelArbol ++;
        }
        return nivelDelArbol - 1;
    }


    @Override
    public K minimo() {
        if (this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual = this.raiz;
        NodoBinario<K,V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior.getClave();
    }

    @Override
    public K maximo() {
        if (this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual = this.raiz;
        NodoBinario<K,V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoDerecho();
        }
        return nodoAnterior.getClave();
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula");
        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("Valor no puede ser nulo");
        }

        if (this.esArbolVacio()){
            this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
             return;
        }

        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();

        while (!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual = nodoActual.getClave();
            nodoAnterior = nodoActual;
            if (claveAInsertar.compareTo(claveActual) < 0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if (claveAInsertar.compareTo(claveActual) > 0){
                nodoActual = nodoActual.getHijoDerecho();
            }else { // la clave ya existe, entonces reemplazo su valor
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        // Si llega hasta ese punto, quiere decir que no existe en el árbol
        // La clave, entonces debo crear un nodo, con la clave y valor a insertar
        //y el nodoAnterior es el nodo padre de ese nuevo nodo

        NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
        K claveDelPadre = nodoAnterior.getClave();
        if (claveAInsertar.compareTo(claveDelPadre) < 0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K clave) {
        return null;
    }

    @Override
    public boolean contiene(K clave) {
        return this.buscar(clave) != null;
    }

    @Override
    public V buscar(K claveABuscar) {
        if (claveABuscar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula");
        }

        if (this.esArbolVacio()){
            return null;
        }

        NodoBinario<K, V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            if (claveABuscar.compareTo(claveActual) == 0) {
                return nodoActual.getValor();
            } else if (claveABuscar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
            // si llego a este punto quiere decir que no se encuentra la claveABuscar
            // en el árbol
        return null;
    }

    @Override
    public List<K> recorridoEnInOrden() {

        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = this.raiz;

        while(!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            }else {
                nodoActual = null;
            }
        }

        while(!pilaDeNodos.isEmpty()){

            if (!pilaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoTope = pilaDeNodos.peek();
                nodoActual = pilaDeNodos.pop();
                recorrido.add(nodoActual.getClave());
                if(!nodoTope.esVacioHijoDerecho() && nodoTope.getHijoDerecho() != nodoActual ){
                    meterPilaParaInOrden(pilaDeNodos, nodoTope.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }

    private void meterPilaParaInOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
    }

    public List<K> recorridoEnInOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        // se necesita para una implementacion recursiva un
        // metodo amigo que haga el grueso del trabajo
        recorridoEnInOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnInOrdenRec(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnInOrdenRec(nodoActual.getHijoIzquierdo(),recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoEnInOrdenRec(nodoActual.getHijoDerecho(), recorrido);
    }
    public List<K> recorridoEnPostOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        // se necesita para una implementacion recursiva un
        // metodo amigo que haga el grueso del trabajo
        recorridoEnPostOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPostOrdenRec(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrdenRec(nodoActual.getHijoIzquierdo(),recorrido);
        recorridoEnPostOrdenRec(nodoActual.getHijoDerecho(), recorrido);
        recorrido.add(nodoActual.getClave());
    }
    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoDerecho()){
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return recorrido;
    }

    public List<K> recorridoEnPreOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        // se necesita para una implementacion recursiva un
        // metodo amigo que haga el grueso del trabajo
        recorridoEnPreOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPreOrdenRec(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenRec(nodoActual.getHijoIzquierdo(),recorrido);
        recorridoEnPreOrdenRec(nodoActual.getHijoDerecho(), recorrido);

    }

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = this.raiz;

        meterPilaParaPostOrden(pilaDeNodos, nodoActual);

        while(!pilaDeNodos.isEmpty()){
          nodoActual = pilaDeNodos.pop();
          recorrido.add(nodoActual.getClave());
          if (!pilaDeNodos.isEmpty()){
              NodoBinario<K,V> nodoTope = pilaDeNodos.peek();
              if(!nodoTope.esVacioHijoDerecho() && nodoTope.getHijoDerecho() != nodoActual ){
                  meterPilaParaPostOrden(pilaDeNodos, nodoTope.getHijoDerecho());
              }
          }
        }
        return recorrido;
    }

    private void meterPilaParaPostOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else {
               nodoActual = nodoActual.getHijoDerecho();
            }
        }
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }

        }
        return recorrido;
    }
}
