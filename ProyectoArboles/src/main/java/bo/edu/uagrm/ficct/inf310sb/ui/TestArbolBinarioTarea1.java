package bo.edu.uagrm.ficct.inf310sb.ui;

import bo.edu.uagrm.ficct.inf310sb.arboles.AVL;
import bo.edu.uagrm.ficct.inf310sb.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310sb.arboles.IArbolBusqueda;
import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionOrdenInvalido;
import com.sun.javafx.iio.ImageStorage;

import java.util.Scanner;

public class TestArbolBinarioTarea1 {

    public static void main(String[] argumentos) throws ExcepcionOrdenInvalido {
        IArbolBusqueda<Integer, String> arbolprueba = null;
        IArbolBusqueda<Integer, String> arbolprueba2 = new ArbolBinarioBusqueda<>();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Elija un tipo de árbol (ABB, AVL)");
        String tipoArbol = entrada.next();
        switch (tipoArbol){
            case "ABB":
                arbolprueba = new ArbolBinarioBusqueda<>();
                break;
            case "AVL":
                arbolprueba = new AVL<>();
                break;
            default:
                System.out.println("Tipo de árbol invalido. Usando AVL");
                arbolprueba = new AVL<>();
                break;
        }

        Scanner menú = new Scanner(System.in);
        boolean i = true;
        while(i == true) {
        System.out.println("1. Insertar");
        System.out.println("2. Eliminar");
        System.out.println("3. Recorrido InOrden");
        System.out.println("4. Recorrido PostOrden");
        System.out.println("5. Recorrido PreOrden");
        System.out.println("6. Cantidad de Nodos que solo tienen hijo Izquierdo recursivo");
        System.out.println("7. Cantidad de Nodos que solo tienen hijo Izquierdo iterativo");
        System.out.println("8. Cantidad de Nodos que solo tienen hijo Izquierdo recursivo solo en un Nivel");
        System.out.println("9. Cantidad de Nodos que solo tienen hijo Izquierdo iterativo desde un Nivel");
        System.out.println("10. Cantidad de Nodos que solo tienen hijo Izquierdo iterativo antes de un Nivel");
        System.out.println("11. Cantidad de nodos que tienen ambos hijos desde un Nivel");
        System.out.println("12. Arbol Invertido");
        System.out.println("13. Arbol Binario Lleno");
            System.out.println("14. Arboles Similares (arboles ya  cargados)");
        System.out.println("Elija una opción");
        String opción = menú.next();
            switch (opción) {
                case "0":
                    arbolprueba = new ArbolBinarioBusqueda<>();
                    break;
                case "1":
                    System.out.println("Inserte la Clave:");
                    int claveAInsertar = menú.nextInt();
                    System.out.println("Inserte la Valor:");
                    String valorAInsertar = menú.next();
                    arbolprueba.insertar(claveAInsertar, valorAInsertar);
                    System.out.println("Nodo Insertado al árbol!");
                    break;
                case "2":
                    System.out.println("Clave a eliminar:");
                    int claveAEliminar = menú.nextInt();
                    arbolprueba.eliminar(claveAEliminar);
                    System.out.println("Nodo Eliminado del árbol!");
                    break;
                case "3" :
                    System.out.println("Recorrido InOrden : " + arbolprueba.recorridoEnInOrden());
                    break;
                case "4" :
                    System.out.println("Recorrido PostOrden : " + arbolprueba.recorridoEnPostOrden());
                    break;
                case "5" :
                    System.out.println("Recorrido PreOrden : " + arbolprueba.recorridoEnPreOrden());
                    break;
                case "6":
                    System.out.println("Cantidad de Nodos que solo tienen hijo Izquierdo recursivo: " +
                            ((ArbolBinarioBusqueda<Integer, String>) arbolprueba).cantidadNodosQueTienenSoloHijoIzquierdo());
                    break;
                case "7":
                    System.out.println("Cantidad de Nodos que solo tienen hijo Izquierdo iterativo: " +
                            ((ArbolBinarioBusqueda<Integer, String>) arbolprueba).cantidadNodosQueTienenSoloHijoIzquiedoIt());
                    break;
                case "8":
                    System.out.println("Nivel :");
                    int nivel = menú.nextInt();
                    System.out.println("Cantidad de Nodos que solo tienen hijo Izquierdo recursivo solo en un Nivel :" +
                            ((ArbolBinarioBusqueda<Integer, String>) arbolprueba).cantidadNodosQueTienenSoloHijoIzquierdoNivel(nivel));
                    break;

                case "9":
                    System.out.println("Nivel :");
                    int nivel3 = menú.nextInt();
                    System.out.println("Cantidad de Nodos que solo tienen hijo Izquierdo recursivo desde un Nivel :" +
                            ((ArbolBinarioBusqueda<Integer, String>) arbolprueba).cantidadNodosQueTienenSoloHijoIzquiedoNivelIt(nivel3));
                    break;
                case "10":
                    System.out.println("Nivel :");
                    int nivel4 = menú.nextInt();
                    System.out.println("Cantidad de Nodos que solo tienen hijo Izquierdo recursivo antes de un Nivel :" +
                            ((ArbolBinarioBusqueda<Integer, String>) arbolprueba).cantidadNodosQueTienenSoloHijoIzquiedoNivelAnteriorIt(nivel4));
                    break;
                case "11":
                    System.out.println("Nivel :");
                    int nivel5 = menú.nextInt();
                    System.out.println("Cantidad de nodos que tienen ambos hijos desde un Nivel :" +
                            ((ArbolBinarioBusqueda<Integer, String>) arbolprueba).cantidadNodosQueTienenAmbosHijos(nivel5));
                    break;
                case "12":
                    System.out.println("RecorridoInOrden :" + arbolprueba.recorridoEnInOrden());
                    ArbolBinarioBusqueda<Integer, String> arbolInvertido = ((ArbolBinarioBusqueda<Integer, String>) arbolprueba).arbolBinarioBusquedaInvertido();
                   System.out.println("RecorridoInOrden de Árbol Invertido:" + arbolInvertido.recorridoEnInOrdenRec());
                   break;
                case "13":
                    if(((ArbolBinarioBusqueda<Integer, String>) arbolprueba).arbolBinarioLleno()){
                        System.out.println("Verdadero : El árbol está lleno");
                    }else {
                        System.out.println("Falso : El árbol no está lleno");
                    }
                    break;
                case "14":
                   i= false;
                   break;
            }
        }
        // arboles similares
        arbolprueba.insertar(50,"Azul");
        arbolprueba.insertar(78,"Naranja");
        arbolprueba.insertar(74,"Zapato");
        arbolprueba.insertar(30,"Jeans");
        arbolprueba.insertar(44,"Amarillo");
        arbolprueba.insertar(20,"Negro");




        arbolprueba2.insertar(25,"Azul");
        arbolprueba2.insertar(40,"Naranja");
        arbolprueba2.insertar(35,"Zapato");
        arbolprueba2.insertar(10,"Jeans");
        arbolprueba2.insertar(15,"Amarillo");
        arbolprueba2.insertar(5,"Negro");



        if(((ArbolBinarioBusqueda<Integer, String>) arbolprueba).árbolesSimilares((ArbolBinarioBusqueda<Integer, String>) arbolprueba2)){
            System.out.println("Verdadero : Los Árboles son similares");
        }else {
            System.out.println("Falso : Los Árboles no son similares");
        }

    }

}
