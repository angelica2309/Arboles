package bo.edu.uagrm.ficct.inf310sb.ui;

import bo.edu.uagrm.ficct.inf310sb.arboles.*;
import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionOrdenInvalido;

import java.util.Scanner;

public class TestArbol {
    public static void main(String[] argumentos) throws ExcepcionOrdenInvalido {
        IArbolBusqueda<Integer, String> arbolprueba;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Elija un tipo de árbol (ABB, AVL, AMVias, AB)");
        String tipoArbol = entrada.next();
        switch (tipoArbol){
            case "ABB":
                arbolprueba = new ArbolBinarioBusqueda<>();
                break;
            case "AVL":
                arbolprueba = new AVL<>();
                break;
            case "AMVias":
                arbolprueba = new ArbolMViasBusqueda<>(4);
                break;
            case "AB":
                arbolprueba = new ArbolB<>(4);
                break;
            default:
                System.out.println("Tipo de árbol invalido. Usando AVL");
                arbolprueba = new AVL<>();
                break;
        }

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
        arbolprueba.insertar(74,"Banana");
        arbolprueba.insertar(120,"Arroz");
        arbolprueba.insertar(35,"Blusa");
        arbolprueba.insertar(111,"Zapato");
        arbolprueba.insertar(90,"Portatil");
        arbolprueba.insertar(81,"Llaves");
        arbolprueba.insertar(71,"Mouse");
        arbolprueba.insertar(100,"Cables");
        arbolprueba.insertar(89,"Térmico");
        arbolprueba.insertar(51,"Gato");

        System.out.println(arbolprueba);

        System.out.println("Recorrido en PostOrden :" + arbolprueba.recorridoEnPostOrden());
        System.out.println("Recorrido en PostOrden Rec :" + ((ArbolBinarioBusqueda<?, ?>) arbolprueba).recorridoEnPostOrdenRec());

        System.out.println("Recorrido por niveles :" + arbolprueba.recorridoPorNiveles());

        System.out.println("Recorrido en PreOrden :" + arbolprueba.recorridoEnPreOrden());
        System.out.println("Recorrido en PreOrden Rec :" + ((ArbolBinarioBusqueda<?, ?>) arbolprueba).recorridoEnPreOrdenRec());

        System.out.println("Recorrido en InOrden :" + arbolprueba.recorridoEnInOrden());
        System.out.println("Recorrido en InOrden Rec :" + ((ArbolBinarioBusqueda<?, ?>) arbolprueba).recorridoEnInOrdenRec());

        System.out.println("Altura del ArbolRec : " + arbolprueba.altura());
        System.out.println("Altura del Arbol : " + ((ArbolBinarioBusqueda<?, ?>) arbolprueba).alturaIt());

        System.out.println("Nivel del Arbol :" + arbolprueba.nivel());
        System.out.println("Nivel del Arbol :" + ((ArbolBinarioBusqueda<?, ?>) arbolprueba).nivelIt());

        arbolprueba.eliminar(23);
        System.out.println("Recorrido por niveles :" + arbolprueba.recorridoPorNiveles());
        arbolprueba.eliminar(50);

    }

}
