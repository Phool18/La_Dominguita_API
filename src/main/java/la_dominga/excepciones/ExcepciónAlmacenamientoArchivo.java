package la_dominga.excepciones;

public class ExcepciónAlmacenamientoArchivo extends RuntimeException {
    public ExcepciónAlmacenamientoArchivo(String message) {
        super(message);
    }

    public ExcepciónAlmacenamientoArchivo(String message, Throwable cause) {
        super(message, cause);
    }
}
