package bo.edu.uagrm.ficct.inf310sb.arboles;

public class AVL<K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K,V> {
    private static final byte DIFERENCIA_MAXIMA = 1;


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
        super.raiz = this.insertar(this.raiz, claveAInsertar, valorAInsertar);


    }

    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return nuevoNodo;
        }

        K claveActual = nodoActual.getClave();
        if (claveAInsertar.compareTo(claveActual) > 0) {
            // se va por la derecha
            NodoBinario<K, V> supuestoNuevoHijoDerecho = insertar(nodoActual.getHijoDerecho(), claveAInsertar,
                    valorAInsertar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return this.balancear(nodoActual);
        }
        if (claveAInsertar.compareTo(claveActual) < 0) {
            // se va por la izquierda
            NodoBinario<K, V> supuestoNuevoHijoIzquierdo = insertar(nodoActual.getHijoIzquierdo(), claveAInsertar,
                    valorAInsertar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return this.balancear(nodoActual);
        }
        // si llega aquí quiere decir que en el nodo actual esta la clave a insertar, clave actual == clave a insertar
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
    }

    private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
        int alturaRamaIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaRamaDerecha = altura(nodoActual.getHijoDerecho());
        int diferencia = alturaRamaIzquierda - alturaRamaDerecha;
        if (diferencia > DIFERENCIA_MAXIMA) {
            // hay que balancear a la derecha
            NodoBinario<K, V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
            alturaRamaIzquierda = altura(hijoIzquierdo.getHijoIzquierdo());
            alturaRamaDerecha = altura(hijoIzquierdo.getHijoDerecho());
            if (alturaRamaDerecha > alturaRamaIzquierda) {
                //rotacion doble
                return this.rotacionDobleADerecha(nodoActual);
            } else {
                return this.rotacionSimpleADerecha(nodoActual);
            }
        } else if (diferencia < DIFERENCIA_MAXIMA) {
            NodoBinario<K, V> hijoDerecho = nodoActual.getHijoDerecho();
            alturaRamaDerecha = altura(hijoDerecho.getHijoDerecho());
            alturaRamaIzquierda = altura(hijoDerecho.getHijoIzquierdo());
            if (alturaRamaIzquierda > alturaRamaDerecha) {
                //rotacion doble
                return this.rotacionDobleAIzquierda(nodoActual);
            } else {
                return this.rotacionSimpleAIzquierda(nodoActual);
            }
        }

        return nodoActual;
    }

    private NodoBinario<K, V> rotacionSimpleAIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;

    }

    private NodoBinario<K, V> rotacionDobleAIzquierda(NodoBinario<K, V> nodoActual) {
        nodoActual.setHijoDerecho(rotacionSimpleADerecha(nodoActual.getHijoDerecho()));
        return this.rotacionSimpleAIzquierda(nodoActual);
    }

    private NodoBinario<K, V> rotacionSimpleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K, V> rotacionDobleADerecha(NodoBinario<K, V> nodoActual) {
        nodoActual.setHijoIzquierdo(rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo()));
        return this.rotacionSimpleADerecha(nodoActual);
    }

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

    /**
     *Implemente el método eliminar de un árbol AVL
     */
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K claveAEliminar) {
        K claveActual = nodoActual.getClave();
        if (claveAEliminar.compareTo(claveActual) > 0){
            NodoBinario<K,V> posibleNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
            return balancear(nodoActual);
        }
        if (claveAEliminar.compareTo(claveActual) < 0){
            NodoBinario<K,V> posibleNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(posibleNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        // si llego a este punto quiere decir que ya
        // encontré el nodo con la clave a eliminar
        // caso 1
        if (nodoActual.esHoja()){
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }
        // caso 2
        //caso 2.1 solo tiene hijo izquierdo
        if (nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo() ){
            return nodoActual.getHijoIzquierdo();
        }
        //caso 2.2 solo tiene hijo derecho
        if (!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo() ){
            return nodoActual.getHijoDerecho();
        }
        //caso 3
        // si tiene sus dos hijos
        NodoBinario<K,V> nodoReemplazo = super.buscarNodoSucesor(nodoActual.getHijoDerecho());

        NodoBinario<K,V> posibleNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), nodoReemplazo.getClave());
        nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);

        nodoActual.setValor(nodoReemplazo.getValor());
        nodoActual.setClave(nodoReemplazo.getClave());

        return nodoActual;
    }

}
