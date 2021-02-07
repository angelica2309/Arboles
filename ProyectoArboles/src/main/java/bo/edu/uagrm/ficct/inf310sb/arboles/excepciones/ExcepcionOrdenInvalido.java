package bo.edu.uagrm.ficct.inf310sb.arboles.excepciones;

public class ExcepcionOrdenInvalido extends Exception {


    public ExcepcionOrdenInvalido() {
        super("Arbol con orden Inválido");
    }

    public ExcepcionOrdenInvalido(String message) {
        super(message);
    }
}
