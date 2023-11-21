package la_dominga.configuraciones;
<<<<<<< HEAD

=======
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
public class Resultado {
    public enum Estado {
        A,
        I
    }
    public static final String TIPO_RESULT = "result";
    public static final String TIPO_DATA = "data";
    public static final String TIPO_AUTH = "auth";
    public static final int RPTA_OK = 1;
    public static final int RPTA_WARNING = 0;
    public static final int RPTA_ERROR = -1;
    public static final String OPERACION_CORRECTA = "Operación finalizada correctamente";
    public static final String OPERACION_INCORRECTA = "No se ha podido culminar la operación";
    public static final String OPERACION_ERRONEA = "Ha ocurrido un error al realizar la operación";
    public static final String AUTH_SECRET = "serviciosweb2";
}