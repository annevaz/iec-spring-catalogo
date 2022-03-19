package estudante.anne.catalogo.service;

import java.util.List;

import estudante.anne.catalogo.model.Musica;

public interface CatalogoService {
    List<Musica> findAll();

    Musica findById(long id);

    Musica save(Musica musica);

    void excluir(long id);

    List<Musica> findByTitulo(String titulo);

    List<Musica> findAllByCategoriaId(Long categoriaId);
}
