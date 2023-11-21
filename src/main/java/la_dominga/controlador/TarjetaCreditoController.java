package la_dominga.controlador;


import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.TarjetaCredito;
import la_dominga.servidor.TarjetaCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping("/tarjeta")
public class TarjetaCreditoController {

    @Autowired
    private TarjetaCreditoService tarjetaCreditoService;
    @PostMapping("/guardar")
    public RespuestaServidor<TarjetaCredito> agregarTarjeta(@Valid @RequestBody TarjetaCredito tarjeta) {
        TarjetaCredito tarjetaGuardada = tarjetaCreditoService.guardarTarjeta(tarjeta);
        return new RespuestaServidor<>("Success", 200, "Tarjeta guardada con éxito", tarjetaGuardada);
    }
    @GetMapping("/validar")
    public RespuestaServidor<String> validarTarjeta(@RequestParam String numeroTarjeta, @RequestParam String titular, @RequestParam String cvv, @RequestParam String mesAnio) {
        if (numeroTarjeta == null || titular == null || cvv == null || mesAnio == null) {
            return new RespuestaServidor<>("Error", 400, "Parámetros inválidos", "Parámetros de validación de tarjeta incompletos o incorrectos.");
        }
        Optional<TarjetaCredito> resultado = tarjetaCreditoService.validarTarjeta(numeroTarjeta, titular, cvv, mesAnio);
        if (resultado.isPresent()) {
            return new RespuestaServidor<>("Success", 200, "Validación de tarjeta exitosa", "La tarjeta es válida.");
        } else {
            return new RespuestaServidor<>("Error", 403, "Tarjeta no encontrada", "Esa tarjeta no existe");
        }
    }

}
