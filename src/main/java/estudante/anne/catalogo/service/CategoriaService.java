package estudante.anne.catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import estudante.anne.catalogo.model.Categoria;

public interface CategoriaService {
    Categoria save(Categoria musica);

    void excluir(long id);

    Categoria findById(long id);

    List<Categoria> findAll();

    Page<Categoria> findAll(Pageable pageable);
}
