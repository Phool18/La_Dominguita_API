package la_dominga.servidor;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.configuraciones.Resultado;
import la_dominga.entidades.Categoria;
import la_dominga.repositorio.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public RespuestaServidor<List<Categoria>> listarTodasLasCategorias() {
        try {
            List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
            return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_OK, "Categorías obtenidas con éxito", categorias);
        } catch (Exception e) {
            return new RespuestaServidor<>(Resultado.TIPO_RESULT, Resultado.RPTA_ERROR, "Error al obtener categorías", null);
        }
    }

}
