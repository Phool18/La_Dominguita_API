package la_dominga.controlador;


import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.TarjetaCredito;
import la_dominga.entidades.dto.TarjetaCreditoDTO;
import la_dominga.servidor.TarjetaCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static la_dominga.configuraciones.Resultado.*;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaCreditoController {

    @Autowired
    private TarjetaCreditoService tarjetaCreditoService;
    @PostMapping("/guardar")
    public ResponseEntity<RespuestaServidor> guardarTarjeta(@RequestBody TarjetaCredito tarjeta) {
        try {
            TarjetaCredito tarjetaGuardada = tarjetaCreditoService.guardarTarjeta(tarjeta);
            return ResponseEntity.ok(new RespuestaServidor(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, tarjetaGuardada));
        } catch (IllegalArgumentException e) {
            // Handle the case where the credit card already exists for the user
            return ResponseEntity.badRequest().body(new RespuestaServidor(OPERACION_ERRONEA, RPTA_ERROR, e.getMessage(), "Usa otra tarjeta"));
        }
    }
    @GetMapping("/validar")
    public RespuestaServidor<String> validarTarjeta(@RequestParam String numeroTarjeta, @RequestParam String titular, @RequestParam String cvv, @RequestParam String mesAnio) {
        if (numeroTarjeta == null || titular == null || cvv == null || mesAnio == null) {
            return new RespuestaServidor<>("Error", 400, "Parámetros inválidos", "Parámetros de validación de tarjeta incompletos o incorrectos.");
        }
        Optional<TarjetaCredito> resultado = tarjetaCreditoService.validarTarjeta(numeroTarjeta, titular, cvv, mesAnio);
        if (resultado.isPresent()) {
            return new RespuestaServidor<>("Success", 1, "Validación de tarjeta exitosa", "La tarjeta es válida.");
        } else {
            return new RespuestaServidor<>("Error", 403, "Tarjeta no encontrada", "Esa tarjeta no existe");
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public RespuestaServidor<List<TarjetaCreditoDTO>> listarTarjetasPorUsuario(@PathVariable int usuarioId) {
        List<TarjetaCreditoDTO> tarjetasDTO = tarjetaCreditoService.listarTarjetasOcultasPorUsuario(usuarioId);
        return new RespuestaServidor<>("Success", 200, "Tarjetas de crédito obtenidas", tarjetasDTO);
    }


}
