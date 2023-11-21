package la_dominga.servidor;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Categoria;
import la_dominga.repositorio.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static la_dominga.configuraciones.Resultado.*;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository repository) {
        this.categoriaRepository = repository;
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    public RespuestaServidor listarCategorias(){
        return new RespuestaServidor(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.categoriaRepository.listarCategorias());
    }

}
