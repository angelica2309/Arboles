package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.*;

public class ArbolBinarioBusqueda<K extends Comparable<K>, V> implements IArbolBusqueda<K, V> {

    protected NodoBinario<K, V> raiz;

    public ArbolBinarioBusqueda() {

    }

    /**
     * Instancia un arbol reconstruyendo en base a sus recorridos InOrden y (PreOrden o PostOrden).
     * si el parametro usandoPreOrden es verdadero, los parametros clavesNoInOrden y valoresNoInOrden
     * tendran el recorrido preOrden del arbol, caso contrario tendran el postOrden
     *
     * @param clavesInOrden
     * @param valoresInOrden
     * @param clavesNoInOrden
     * @param valoresNoInOrden
     * @param usandoPreOrden
     */
    public ArbolBinarioBusqueda(List<K> clavesInOrden, List<V> valoresInOrden,
                                List<K> clavesNoInOrden, List<V> valoresNoInOrden,
                                boolean usandoPreOrden) {
        if (clavesInOrden == null | clavesNoInOrden == null ||
                valoresInOrden == null || valoresNoInOrden == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos");
        }
        if (clavesInOrden.isEmpty() || clavesNoInOrden.isEmpty() ||
                valoresInOrden.isEmpty() || valoresNoInOrden.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros no pueden ser vacíos");
        }
        if (clavesInOrden.size() != clavesNoInOrden.size() ||
                clavesInOrden.size() != valoresInOrden.size() ||
                clavesInOrden.size() != valoresNoInOrden.size()) {
            throw new IllegalArgumentException("Los parámetros no pueden ser listas de diferentes tamaños");
        }
        if (usandoPreOrden) {
            this.raiz = reconstruirConPreOrden(clavesInOrden, valoresInOrden, clavesNoInOrden, valoresNoInOrden);
        } else {
            this.raiz = reconstruirConPostOrden(clavesInOrden, valoresInOrden, clavesNoInOrden, valoresNoInOrden);
        }
    }


    private NodoBinario<K, V> reconstruirConPreOrden(List<K> clavesInOrden, List<V> valoresInOrden,
                                                     List<K> clavesPreOrden, List<V> valoresPreOrden) {
        if (clavesInOrden.isEmpty()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }

        int posicionDeClavePadreEnPreOrden = 0;
        K clavePadre = clavesPreOrden.get(posicionDeClavePadreEnPreOrden);
        V valoresPadre = valoresPreOrden.get(posicionDeClavePadreEnPreOrden);
        int posicionDeClavePadreEnInOrden = this.posicionDeClave(clavePadre, clavesInOrden);

        // para armar la rama izquierda
        List<K> claveInOrdenPorIzquierda = clavesInOrden.subList(0, posicionDeClavePadreEnInOrden);
        List<V> valoresInOrdenPorIzquierda = valoresInOrden.subList(0, posicionDeClavePadreEnInOrden);
        List<K> clavePreOrdenPorIzquierda = clavesPreOrden.subList(1, posicionDeClavePadreEnInOrden + 1);
        List<V> valoresPreOrdenPorIzquierda = valoresPreOrden.subList(1, posicionDeClavePadreEnInOrden + 1);
        NodoBinario<K, V> hijoIzquierdo = reconstruirConPreOrden(claveInOrdenPorIzquierda, valoresInOrdenPorIzquierda,
                clavePreOrdenPorIzquierda, valoresPreOrdenPorIzquierda);

        // para armar la rama derecha
        List<K> claveInOrdenPorDerecha = clavesInOrden.subList(posicionDeClavePadreEnInOrden + 1, clavesInOrden.size() + 1);
        List<V> valoresInOrdenPorDerecha = valoresInOrden.subList(posicionDeClavePadreEnInOrden + 1, clavesInOrden.size() + 1);
        List<K> clavePreOrdenPorDerecha = clavesPreOrden.subList(posicionDeClavePadreEnInOrden + 1, clavesInOrden.size() + 1);
        List<V> valoresPreOrdenPorDerecha = valoresPreOrden.subList(posicionDeClavePadreEnInOrden + 1, clavesInOrden.size() + 1);
        NodoBinario<K, V> hijoDerecho = reconstruirConPreOrden(claveInOrdenPorDerecha, valoresInOrdenPorDerecha,
                clavePreOrdenPorDerecha, valoresPreOrdenPorDerecha);


        //armando el nodo actual
        NodoBinario<K, V> nodoActual = new NodoBinario<>(clavePadre, valoresPadre);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual.setHijoDerecho(hijoDerecho);
        return nodoActual;
    }

    private NodoBinario<K, V> reconstruirConPostOrden(List<K> clavesInOrden, List<V> valoresInOrden,
                                                      List<K> clavesPostOrden, List<V> valoresPostOrden) {
        if (clavesInOrden.isEmpty()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }

        int posicionPadreEnPostOrden = clavesPostOrden.size();
        K clavePadre = clavesPostOrden.get(posicionPadreEnPostOrden);
        V valorPadre = valoresPostOrden.get(posicionPadreEnPostOrden);
        int posicionPadreEnInOrden = this.posicionDeClave(clavePadre, clavesInOrden);

        // para armar la rama izquierda
        List<K> clavesEnInOrdenPorIzquierda = clavesInOrden.subList(0, posicionPadreEnInOrden);
        List<V> valoresEnInOrdenPorIzquierda = valoresInOrden.subList(0, posicionPadreEnInOrden);
        List<K> clavesEnPostOrdenPorIzquierda = clavesInOrden.subList(0, posicionPadreEnInOrden);
        List<V> valoresEnPostOrdenPorIzquierda = valoresInOrden.subList(0, posicionPadreEnInOrden);
        NodoBinario<K, V> hijosIzquierdos = reconstruirConPostOrden(clavesEnInOrdenPorIzquierda, valoresEnInOrdenPorIzquierda,
                clavesEnPostOrdenPorIzquierda, valoresEnPostOrdenPorIzquierda);

        // para armar la rama dercha
        List<K> clavesEnInOrdenPorDerecha = clavesInOrden.subList(posicionPadreEnInOrden + 1, posicionPadreEnPostOrden + 1);
        List<V> valoresEnInOrdenPorDerecha = valoresInOrden.subList(posicionPadreEnInOrden + 1, posicionPadreEnPostOrden + 1);
        List<K> clavesEnPostOrdenPorDerecha = clavesInOrden.subList(posicionPadreEnInOrden, posicionPadreEnPostOrden + 1);
        List<V> valoresEnPostOrdenPorDerecha = valoresInOrden.subList(posicionPadreEnInOrden, posicionPadreEnPostOrden + 1);
        NodoBinario<K, V> hijosDerechos = reconstruirConPostOrden(clavesEnInOrdenPorDerecha, valoresEnInOrdenPorDerecha,
                clavesEnPostOrdenPorDerecha, valoresEnPostOrdenPorDerecha);
        //armando el nodo actual
        NodoBinario<K, V> nodoActual = new NodoBinario<>();
        nodoActual.setHijoIzquierdo(hijosIzquierdos);
        nodoActual.setHijoDerecho(hijosDerechos);
        return nodoActual;
    }

    private int posicionDeClave(K claveABuscar, List<K> listaDeClaves) {
        for (int i = 0; i < listaDeClaves.size(); i++) {
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
        if (this.esArbolVacio()) {
            return 0;
        }
        int cantidadDeNodos = 0;
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            cantidadDeNodos++;
            if (!nodoActual.esVacioHijoDerecho()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return cantidadDeNodos;
    }

    public int cantidadDeHijosDerechos() {
        return cantidadDeHijosDerechos(this.raiz);

    }

    private int cantidadDeHijosDerechos(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }

        int hdPorRamaIzquierda = cantidadDeHijosDerechos(nodoActual.getHijoIzquierdo());
        int hdPorRamaDerecha = cantidadDeHijosDerechos(nodoActual.getHijoDerecho());
        if (!nodoActual.esVacioHijoDerecho()) {
            return hdPorRamaIzquierda + hdPorRamaDerecha + 1;
        }
        return hdPorRamaIzquierda + hdPorRamaDerecha;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }

        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
        if (alturaPorIzquierda > alturaPorDerecha) {
            return alturaPorIzquierda + 1;
        }
        return alturaPorDerecha + 1;
    }

    public int alturaIt() {

        if (this.esArbolVacio()) {
            return 0;
        }
        int alturaDelArbol = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            int cantidadDeNodosEnLaCola = colaDeNodos.size();
            int i = 0;
            while (i < cantidadDeNodosEnLaCola) {

                NodoBinario<K, V> nodoActual = colaDeNodos.poll();

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                i++;
            }
            alturaDelArbol++;
        }
        return alturaDelArbol;
    }


    @Override
    public int nivel() {
        return nivel(this.raiz);
    }

    private int nivel(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return -1;
        }

        int nivelPorIzquierda = nivel(nodoActual.getHijoIzquierdo());
        int nivelPorDerecha = nivel(nodoActual.getHijoDerecho());
        if (nivelPorIzquierda > nivelPorDerecha) {
            return nivelPorIzquierda + 1;
        }
        return nivelPorDerecha + 1;
    }

    public int nivelIt() {
        if (this.esArbolVacio()) {
            return -1;
        }
        int nivelDelArbol = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            int cantidadDeNodosEnLaCola = colaDeNodos.size();
            int i = 0;
            while (i < cantidadDeNodosEnLaCola) {

                NodoBinario<K, V> nodoActual = colaDeNodos.poll();

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                i++;
            }
            nivelDelArbol++;
        }
        return nivelDelArbol - 1;
    }


    @Override
    public K minimo() {
        if (this.esArbolVacio()) {
            return null;
        }
        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior.getClave();
    }

    public boolean tieneNodosCompletosEnNivel(int nivelObjetivo) {
        return tieneNodosCompletosEnNivel(this.raiz, nivelObjetivo, 0);

    }

    private boolean tieneNodosCompletosEnNivel(NodoBinario<K, V> nodoActual, int nivelObjetivo, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return false;
        }

        if (nivelActual == nivelObjetivo) {
            return !nodoActual.esVacioHijoIzquierdo() &&
                    !nodoActual.esVacioHijoDerecho();
        }

        boolean completoPorIzquierda = this.tieneNodosCompletosEnNivel(nodoActual.getHijoIzquierdo(),
                nivelObjetivo, nivelActual + 1);
        boolean completoPorDerecha = this.tieneNodosCompletosEnNivel(nodoActual.getHijoDerecho(),
                nivelObjetivo, nivelActual + 1);
        return completoPorIzquierda && completoPorDerecha;
    }

    @Override
    public K maximo() {
        if (this.esArbolVacio()) {
            return null;
        }
        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
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

        if (this.esArbolVacio()) {
            this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return;
        }

        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();

        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            nodoAnterior = nodoActual;
            if (claveAInsertar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (claveAInsertar.compareTo(claveActual) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else { // la clave ya existe, entonces reemplazo su valor
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        // Si llega hasta ese punto, quiere decir que no existe en el árbol
        // La clave, entonces debo crear un nodo, con la clave y valor a insertar
        //y el nodoAnterior es el nodo padre de ese nuevo nodo

        NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
        K claveDelPadre = nodoAnterior.getClave();
        if (claveAInsertar.compareTo(claveDelPadre) < 0) {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        } else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K claveAEliminar) {
        if (claveAEliminar == null) {
            throw new IllegalArgumentException("Clave  a eliminar no puede ser nula");
        }

        V valorARetornar = this.buscar(claveAEliminar);
        if (valorARetornar == null) {
            throw new IllegalArgumentException("La clave a eliminar no existe en el árbol");
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorARetornar;
    }

    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K claveAEliminar) {
        K claveActual = nodoActual.getClave();
        if (claveAEliminar.compareTo(claveActual) > 0) {
            NodoBinario<K, V> posibleNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
            return nodoActual;
        }
        if (claveAEliminar.compareTo(claveActual) < 0) {
            NodoBinario<K, V> posibleNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(posibleNuevoHijoIzquierdo);
            return nodoActual;
        }
        // si llego a este punto quiere decir que ya
        // encontré el nodo con la clave a eliminar
        // caso 1
        if (nodoActual.esHoja()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }
        // caso 2
        //caso 2.1 solo tiene hijo izquierdo
        if (nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
            return nodoActual.getHijoIzquierdo();
        }
        //caso 2.2 solo tiene hijo derecho
        if (!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()) {
            return nodoActual.getHijoDerecho();
        }
        //caso 3
        // si tiene sus dos hijos
        NodoBinario<K, V> nodoReemplazo = this.buscarNodoSucesor(nodoActual.getHijoDerecho());

        NodoBinario<K, V> posibleNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), nodoReemplazo.getClave());
        nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);

        nodoActual.setValor(nodoReemplazo.getValor());
        nodoActual.setClave(nodoReemplazo.getClave());

        return nodoActual;
    }

    protected NodoBinario<K, V> buscarNodoSucesor(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoAnterior;
        do {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        } while (!NodoBinario.esNodoVacio(nodoActual));
        return nodoAnterior;
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

        if (this.esArbolVacio()) {
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
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        NodoBinario<K, V> nodoActual = this.raiz;

        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = null;
            }
        }

        while (!pilaDeNodos.isEmpty()) {

            if (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoTope = pilaDeNodos.peek();
                nodoActual = pilaDeNodos.pop();
                recorrido.add(nodoActual.getClave());
                if (!nodoTope.esVacioHijoDerecho() && nodoTope.getHijoDerecho() != nodoActual) {
                    meterPilaParaInOrden(pilaDeNodos, nodoTope.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }

    private void meterPilaParaInOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
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

    private void recorridoEnInOrdenRec(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnInOrdenRec(nodoActual.getHijoIzquierdo(), recorrido);
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

    private void recorridoEnPostOrdenRec(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrdenRec(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPostOrdenRec(nodoActual.getHijoDerecho(), recorrido);
        recorrido.add(nodoActual.getClave());
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoDerecho()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
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

    private void recorridoEnPreOrdenRec(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenRec(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenRec(nodoActual.getHijoDerecho(), recorrido);

    }

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        NodoBinario<K, V> nodoActual = this.raiz;

        meterPilaParaPostOrden(pilaDeNodos, nodoActual);

        while (!pilaDeNodos.isEmpty()) {
            nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoTope = pilaDeNodos.peek();
                if (!nodoTope.esVacioHijoDerecho() && nodoTope.getHijoDerecho() != nodoActual) {
                    meterPilaParaPostOrden(pilaDeNodos, nodoTope.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }

    private void meterPilaParaPostOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }

        }
        return recorrido;
    }
    // Práctico # 1

    /**
     * Implemente un método recursivo que retorne la cantidad nodos que tienen solo hijo
     * izquierdo no vacío en un árbol binario
     */
    public int cantidadNodosQueTienenSoloHijoIzquierdo() {
        return cantidadNodosQueTienenSoloHijoIzquierdo(this.raiz);
    }

    private int cantidadNodosQueTienenSoloHijoIzquierdo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int hIRamaDerecha = cantidadNodosQueTienenSoloHijoIzquierdo(nodoActual.getHijoDerecho());
        int hIRamaIzquierda = cantidadNodosQueTienenSoloHijoIzquierdo(nodoActual.getHijoIzquierdo());
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return hIRamaDerecha + hIRamaIzquierda + 1;
        }
        return hIRamaDerecha + hIRamaIzquierda;
    }

    /**
     * Implemente un método iterativo que retorne la cantidad nodos que tienen solo hijo
     * izquierdo no vacío en un árbol binario
     */
    public int cantidadNodosQueTienenSoloHijoIzquiedoIt() {
        if (NodoBinario.esNodoVacio(this.raiz)) {
            return 0;
        }
        int cantidad = 0;
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            int numeroNodosEnLaCola = pilaDeNodos.size();
            int i = 0;
            while (i < numeroNodosEnLaCola) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                if (nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
                    cantidad++;
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    pilaDeNodos.push(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    pilaDeNodos.push(nodoActual.getHijoDerecho());
                }
                i++;
            }
        }
        return cantidad;
    }

    /**
     * Implemente un método recursivo que retorne la cantidad nodos que tienen solo hijo
     * izquierdo no vacío en un árbol binario, pero solo en el nivel N
     */
    public int cantidadNodosQueTienenSoloHijoIzquierdoNivel(int nivel) {
        return cantidadNodosQueTienenSoloHijoIzquierdoNivel(this.raiz, nivel, 0);
    }

    private int cantidadNodosQueTienenSoloHijoIzquierdoNivel(NodoBinario<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int hIRamaDerecha = cantidadNodosQueTienenSoloHijoIzquierdoNivel(nodoActual.getHijoDerecho(), nivel, nivelActual + 1);
        int hIRamaIzquierda = cantidadNodosQueTienenSoloHijoIzquierdoNivel(nodoActual.getHijoIzquierdo(), nivel, nivelActual + 1);
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho() && nivel == nivelActual) {
            return hIRamaDerecha + hIRamaIzquierda + 1;
        }
        return hIRamaDerecha + hIRamaIzquierda;
    }

    /**
     * Implemente un método iterativo que retorne la cantidad nodos que tienen solo hijo
     * izquierdo no vacío en un árbol binario, pero solo después del nivel N
     */

    public int cantidadNodosQueTienenSoloHijoIzquiedoNivelIt(int nivel) {
        if (NodoBinario.esNodoVacio(this.raiz)) {
            return 0;
        }
        int cantidad = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int nivelActual = 0;
        while (!colaDeNodos.isEmpty()) {
            int numeroNodosEnLaCola = colaDeNodos.size();
            int i = 0;
            while (i < numeroNodosEnLaCola) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo() && nivel <= nivelActual) {
                    cantidad++;
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                i++;
            }
            nivelActual++;
        }
        return cantidad;
    }

    /**
     * Implemente un método iterativo que retorne la cantidad nodos que tienen solo hijo
     * izquierdo no vacío en un árbol binario, pero solo antes del nivel
     */
    public int cantidadNodosQueTienenSoloHijoIzquiedoNivelAnteriorIt(int nivel) {
        if (NodoBinario.esNodoVacio(this.raiz)) {
            return 0;
        }
        int cantidad = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int nivelActual = 0;
        while (!colaDeNodos.isEmpty()) {
            int numeroNodosEnLaCola = colaDeNodos.size();
            int i = 0;
            while (i < numeroNodosEnLaCola) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo() && nivel < nivelActual) {
                    cantidad++;
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                i++;
            }
            nivelActual++;
        }
        return cantidad;
    }

    /**
     * Implemente un método recursivo que reciba como parámetro otro árbol binario de
     * búsqueda que retorne verdadero, si el árbol binario es similar al árbol binario recibido como
     * parámetro, falso en caso contrario.
     */

    // nodo por nodo
    public boolean árbolesSimilares(ArbolBinarioBusqueda<K,V> otroÁrbolBinario){
        return this.árbolesSimilares(otroÁrbolBinario, this.raiz, otroÁrbolBinario.raiz);
    }

    private boolean árbolesSimilares(ArbolBinarioBusqueda<K,V> otroÁrbolBinario, NodoBinario<K,V> nodoActual, NodoBinario<K,V> nodoAComparar) {

        if((NodoBinario.esNodoVacio(nodoActual) && !NodoBinario.esNodoVacio(nodoAComparar)) ||
                (NodoBinario.esNodoVacio(nodoAComparar) && !NodoBinario.esNodoVacio(nodoActual))) {
            return false;
        }
        if(NodoBinario.esNodoVacio(nodoActual) && NodoBinario.esNodoVacio(nodoAComparar)){
            return true;
        }

         boolean porIzquierda = árbolesSimilares(otroÁrbolBinario, nodoActual.getHijoIzquierdo(), nodoAComparar.getHijoIzquierdo());
         boolean porDerecha = árbolesSimilares(otroÁrbolBinario, nodoActual.getHijoDerecho(), nodoAComparar.getHijoDerecho());

        return porIzquierda && porDerecha;
    }

    // con recorrido solo compara las claves
    public boolean arbolSimilar(ArbolBinarioBusqueda<K, V> otroArbol) {
        List<K> recorrido1 = this.recorridoEnInOrden();
        List<K> recorrido2 = otroArbol.recorridoEnInOrden();
        for (int i = 0; i < recorrido1.size(); i++) {
            K clave = recorrido1.get(i);
            K clave2 = recorrido2.get(i);
            if (clave != clave2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Para un árbol binario implemente un método que retorne la cantidad de nodos que tienen
     * ambos hijos desde el nivel N.
     */
    public int cantidadNodosQueTienenAmbosHijos(int nivel) {
        return cantidadNodosQueTienenAmbosHijos(this.raiz, nivel, 0);
    }

    private int cantidadNodosQueTienenAmbosHijos(NodoBinario<K, V> nodoActual, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int hIRamaDerecha = cantidadNodosQueTienenAmbosHijos(nodoActual.getHijoDerecho(), nivel, nivelActual + 1);
        int hIRamaIzquierda = cantidadNodosQueTienenAmbosHijos(nodoActual.getHijoIzquierdo(), nivel, nivelActual + 1);
        if (!nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho() && nivel <= nivelActual) {
            return hIRamaDerecha + hIRamaIzquierda + 1;
        }
        return hIRamaDerecha + hIRamaIzquierda;
    }

    /**
     * Implementar un método que retorne un nuevo árbol binario de búsqueda invertido.
     */

    public ArbolBinarioBusqueda<K, V> arbolBinarioBusquedaInvertido() {
        ArbolBinarioBusqueda<K, V> arbolInvertido = new ArbolBinarioBusqueda<>();
        Queue<NodoBinario<K, V>> colaNodos = new LinkedList<>();
        colaNodos.offer(this.raiz);
        while (!colaNodos.isEmpty()) {
            int cantidadNodosEnLaCola = colaNodos.size();
            int i = 0;
            while (i < cantidadNodosEnLaCola) {
                NodoBinario<K, V> nodoActual = colaNodos.poll();
                //insertar nodos al arbol invertido
                K claveAInsertar = nodoActual.getClave();
                V valorAInsertar = nodoActual.getValor();
                arbolInvertido.insertarInvertido(claveAInsertar, valorAInsertar);
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaNodos.offer(nodoActual.getHijoDerecho());
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaNodos.offer(nodoActual.getHijoIzquierdo());
                }
                i++;
            }
        }
        return arbolInvertido;
    }

    public void insertarInvertido(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
                throw new IllegalArgumentException("Clave no puede ser nula");
        }
        if (valorAInsertar == null) {
                throw new IllegalArgumentException("Valor no puede ser nulo");
        }

        if (this.esArbolVacio()) {
                this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
                return;
        }
        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior = (NodoBinario<K, V>) NodoBinario.nodoVacio();

          while (!NodoBinario.esNodoVacio(nodoActual)) {
               K claveActual = nodoActual.getClave();
               nodoAnterior = nodoActual;
               if (claveAInsertar.compareTo(claveActual) < 0) {
                    nodoActual = nodoActual.getHijoDerecho();
               } else if (claveAInsertar.compareTo(claveActual) > 0) {
                   nodoActual = nodoActual.getHijoIzquierdo();
               } else { // la clave ya existe, entonces reemplazo su valor
                   nodoActual.setValor(valorAInsertar);
                   return;
               }
          }
          NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
          K claveDelPadre = nodoAnterior.getClave();
          if (claveAInsertar.compareTo(claveDelPadre) < 0) {
              nodoAnterior.setHijoDerecho(nuevoNodo);
          } else {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
          }

    }
    /**
     *  Implementar un método que retorne verdadero si un árbol binario esta lleno.
     */
    public boolean arbolBinarioLleno(){
        if(NodoBinario.esNodoVacio(this.raiz)){
            return false;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            int cantidadNodosEnLaCola = colaDeNodos.size();
            int i = 0;
            while (i < cantidadNodosEnLaCola) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                // Si no es hoja
                if (!nodoActual.esHoja()){
                    if (nodoActual.esVacioHijoIzquierdo() || nodoActual.esVacioHijoDerecho()) {
                        return false;
                    }
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                i++;
            }
        }
        return true ;
    }

    public boolean arbolBinarioLlenoRec(){
        return arbolBinarioLlenoRec(this. raiz);
    }

    private boolean arbolBinarioLlenoRec(NodoBinario<K,V> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return false;
        }
        boolean completoPorIzquierda = this.arbolBinarioLlenoRec(nodoActual.getHijoIzquierdo()) ;
        boolean completoPorDerecha = this.arbolBinarioLlenoRec(nodoActual.getHijoDerecho()) ;
        return completoPorIzquierda && completoPorDerecha;
    }
}
