package la_dominga.servidor;

import la_dominga.entidades.TarjetaCredito;
import la_dominga.entidades.dto.TarjetaCreditoDTO;
import la_dominga.repositorio.TarjetaCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarjetaCreditoService {

    private final TarjetaCreditoRepository tarjetaRepository;

    @Autowired
    public TarjetaCreditoService(TarjetaCreditoRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }
    public TarjetaCredito guardarTarjeta(TarjetaCredito tarjeta) {
        if (isValidDate(tarjeta.getMes_anio())) {
            return tarjetaRepository.save(tarjeta);
        } else {
            throw new IllegalArgumentException("La fecha en el campo mes_anio no es válida.");
        }
    }

    // Método para validar si la fecha es válida
    private boolean isValidDate(String mesAnio) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
            sdf.setLenient(false); // No permitir fechas inválidas
            Date date = sdf.parse(mesAnio);
            Date currentDate = new Date();
            return !date.before(currentDate); // La fecha debe ser igual o posterior a la fecha actual
        } catch (ParseException e) {
            return false; // Error al analizar la fecha
        }
    }
    public Optional<TarjetaCredito> validarTarjeta(String numeroTarjeta, String titular, String cvv, String mesAnio) {
        return tarjetaRepository.validarTarjeta(numeroTarjeta, titular, cvv, mesAnio);
    }

    public List<TarjetaCreditoDTO> listarTarjetasOcultasPorUsuario(int usuarioId) {
        List<TarjetaCredito> tarjetas = tarjetaRepository.findByUsuarioId(usuarioId);
        return tarjetas.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private TarjetaCreditoDTO convertirADTO(TarjetaCredito tarjeta) {
        TarjetaCreditoDTO dto = new TarjetaCreditoDTO();
        dto.setId(tarjeta.getId());
        dto.setNumeroTarjeta("**** **** **** " + tarjeta.getNumeroTarjeta().substring(12));
        dto.setCvv("***");
        dto.setMes_anio("**/**");
        return dto;
    }
}
