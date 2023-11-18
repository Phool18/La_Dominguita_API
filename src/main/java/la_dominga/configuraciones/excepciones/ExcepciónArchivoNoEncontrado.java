package la_dominga.configuraciones.excepciones;

public class ExcepciónArchivoNoEncontrado extends RuntimeException {
    public ExcepciónArchivoNoEncontrado(String message) {
        super(message);
    }

    public ExcepciónArchivoNoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }
}
