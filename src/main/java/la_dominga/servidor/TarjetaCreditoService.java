package la_dominga.servidor;

import la_dominga.entidades.TarjetaCredito;
import la_dominga.repositorio.TarjetaCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarjetaCreditoService {

    private final TarjetaCreditoRepository tarjetaRepository;

    @Autowired
    public TarjetaCreditoService(TarjetaCreditoRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }
    public TarjetaCredito guardarTarjeta(TarjetaCredito tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }
    public Optional<TarjetaCredito> validarTarjeta(String numeroTarjeta, String titular, String cvv, String mesAnio) {
        return tarjetaRepository.validarTarjeta(numeroTarjeta, titular, cvv, mesAnio);
    }


}
