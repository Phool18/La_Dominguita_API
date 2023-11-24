package la_dominga.servidor;

import la_dominga.entidades.Cliente;
import la_dominga.repositorio.ClienteRepository;
import la_dominga.configuraciones.RespuestaServidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static la_dominga.configuraciones.Resultado.*;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final Validator validator;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, Validator validator) {
        this.clienteRepository = clienteRepository;
        this.validator = validator;
    }

    public RespuestaServidor save(Cliente c) {
        Set<ConstraintViolation<Cliente>> violations = validator.validate(c);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Cliente> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage()).append("\n");
            }
            return new RespuestaServidor(TIPO_RESULT, RPTA_WARNING, sb.toString(), null);
        }

        // Guarda o actualiza directamente el cliente
        clienteRepository.save(c);
        return new RespuestaServidor(TIPO_DATA, RPTA_OK, "Operación realizada con éxito", c);
    }



    public List<Cliente> listarClientes() {
        return clienteRepository.listarTodosLosClientes();
    }
}
