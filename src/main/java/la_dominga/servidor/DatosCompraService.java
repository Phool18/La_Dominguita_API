package la_dominga.servidor;

import la_dominga.entidades.DatosCompra;
import la_dominga.repositorio.DatosCompraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatosCompraService {
    private final DatosCompraRepository repository;


    public DatosCompraService(DatosCompraRepository repository) {
        this.repository = repository;
    }

    public void guardarInformacionDeLaVenta(Iterable<DatosCompra> informacion){
        this.repository.saveAll(informacion);
    }
}
