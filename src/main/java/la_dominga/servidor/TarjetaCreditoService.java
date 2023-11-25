package la_dominga.servidor;

import la_dominga.entidades.TarjetaCredito;
import la_dominga.entidades.dto.TarjetaCreditoDTO;
import la_dominga.repositorio.TarjetaCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        // Verificar que el número de tarjeta tenga 16 dígitos
        if (tarjeta.getNumeroTarjeta() == null || tarjeta.getNumeroTarjeta().length() != 16) {
            throw new IllegalArgumentException("El número de tarjeta debe tener exactamente 16 dígitos.");
        }

        // Verificar que el CVV tenga 3 dígitos
        if (tarjeta.getCvv() == null || tarjeta.getCvv().length() != 3) {
            throw new IllegalArgumentException("El CVV debe tener exactamente 3 dígitos.");
        }

        // Verificar si la tarjeta ya existe para el usuario
        if (tarjetaRepository.existsByNumeroTarjetaAndUsuarioId(tarjeta.getNumeroTarjeta(), tarjeta.getUsuario().getId())) {
            throw new IllegalArgumentException("Una tarjeta con este número ya está registrada para este usuario.");
        }

        // Validar la fecha
        if (!isValidDate(tarjeta.getMes_anio())) {
            throw new IllegalArgumentException("La fecha en el campo mes_anio no es válida.");
        }

        // Guardar la nueva tarjeta de crédito
        return tarjetaRepository.save(tarjeta);
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
        // Llama a la consulta personalizada en el repositorio para validar la tarjeta
        return tarjetaRepository.validarTarjeta(numeroTarjeta, titular, cvv, mesAnio);
    }

    public List<TarjetaCreditoDTO> listarTarjetasOcultasPorUsuario(int usuarioId) {
        List<TarjetaCredito> tarjetas = tarjetaRepository.findByUsuarioId(usuarioId);
        return tarjetas.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private TarjetaCreditoDTO convertirADTO(TarjetaCredito tarjeta) {
        TarjetaCreditoDTO dto = new TarjetaCreditoDTO();
        dto.setId(tarjeta.getId());

        String numeroTarjeta = tarjeta.getNumeroTarjeta();
        if (numeroTarjeta != null && numeroTarjeta.length() == 16) {
            dto.setNumeroTarjeta("**** **** **** " + numeroTarjeta.substring(12));
        } else {
            // Handle the case where the credit card number is not of the expected length
            dto.setNumeroTarjeta("Número inválido");
        }

        dto.setCvv("***");
        dto.setMes_anio("**/**");
        return dto;
    }
}
