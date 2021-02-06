package bo.edu.uagrm.ficct.inf310sb.arboles.excepciones;

public class ExcepcioClaveNoExiste extends  RuntimeException{
    public ExcepcioClaveNoExiste() {
        this("Clave no existe en su estrctura");
    }

    public ExcepcioClaveNoExiste(String message) {
        super(message);
    }
}
