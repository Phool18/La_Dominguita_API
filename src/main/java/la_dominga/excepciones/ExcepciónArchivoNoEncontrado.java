package la_dominga.excepciones;

public class ExcepciónArchivoNoEncontrado extends RuntimeException {
    public ExcepciónArchivoNoEncontrado(String message) {
        super(message);
    }

    public ExcepciónArchivoNoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }
}
