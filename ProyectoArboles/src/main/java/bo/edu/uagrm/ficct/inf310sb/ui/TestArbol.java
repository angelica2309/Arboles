package bo.edu.uagrm.ficct.inf310sb.ui;

import bo.edu.uagrm.ficct.inf310sb.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310sb.arboles.IArbolBusqueda;

public class TestArbol {
    public static void main(String[] argumentos){
        IArbolBusqueda<Integer, String> arbolprueba = new ArbolBinarioBusqueda<>();

        arbolprueba.insertar(50,"Azul");
        arbolprueba.insertar(78,"Naranja");
        arbolprueba.insertar(74,"Zapato");
        arbolprueba.insertar(30,"Jeans");
        arbolprueba.insertar(44,"Amarillo");
        arbolprueba.insertar(20,"Negro");
        arbolprueba.insertar(25,"Café");
        arbolprueba.insertar(24,"Camisa");
        arbolprueba.insertar(23, "Mesa");
        arbolprueba.insertar(28,"TV");

        System.out.println(arbolprueba);

        System.out.println("Recorrido en PostOrden :" + arbolprueba.recorridoEnPostOrden());
        System.out.println("Recorrido en PostOrden Rec :" + ((ArbolBinarioBusqueda<?, ?, ?>) arbolprueba).recorridoEnPostOrdenRec());

        System.out.println("Recorrido por niveles :" + arbolprueba.recorridoPorNiveles());

        System.out.println("Recorrido en PreOrden :" + arbolprueba.recorridoEnPreOrden());
        System.out.println("Recorrido en PreOrden Rec :" + ((ArbolBinarioBusqueda<?, ?, ?>) arbolprueba).recorridoEnPreOrdenRec());

        System.out.println("Recorrido en InOrden :" + arbolprueba.recorridoEnInOrden());
        System.out.println("Recorrido en InOrden Rec :" + ((ArbolBinarioBusqueda<?, ?, ?>) arbolprueba).recorridoEnInOrdenRec());

        System.out.println("Altura del ArbolRec : " + arbolprueba.altura());
        System.out.println("Altura del Arbol : " + ((ArbolBinarioBusqueda<?, ?, ?>) arbolprueba).alturaIt());

        System.out.println("Nivel del Arbol :" + arbolprueba.nivel());
        System.out.println("Nivel del Arbol :" + ((ArbolBinarioBusqueda<?, ?, ?>) arbolprueba).nivelIt());

    }

}
