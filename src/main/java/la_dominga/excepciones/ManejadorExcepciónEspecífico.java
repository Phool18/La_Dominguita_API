package la_dominga.excepciones;


import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.configuraciones.Global;
import org.hibernate.JDBCException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static la_dominga.configuraciones.Global.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ManejadorExcepciónEspecífico {
    @ExceptionHandler(JDBCException.class)
    public RespuestaServidor sqlException(JDBCException ex) {
        return new RespuestaServidor("sql-exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespuestaServidor validException(MethodArgumentNotValidException ex) {
        return new RespuestaServidor("valid-exception", RPTA_ERROR, OPERACION_ERRONEA, ex.getMessage());
    }

    @ExceptionHandler(ExcepciónAlmacenamientoArchivo.class)
    public RespuestaServidor fileStorageException(ExcepciónAlmacenamientoArchivo ex) {
        return new RespuestaServidor("file-storage-exception", RPTA_ERROR, OPERACION_ERRONEA, ex.getMessage());
    }

    @ExceptionHandler(ExcepciónArchivoNoEncontrado.class)
    public RespuestaServidor myFileNotFoundException(ExcepciónArchivoNoEncontrado exception) {
        return new RespuestaServidor("my-file-not-found-exception", RPTA_ERROR, OPERACION_INCORRECTA, exception.getMessage());
    }
}