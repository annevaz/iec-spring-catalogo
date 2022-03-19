package estudante.anne.catalogo.repository;

import java.util.List;

import estudante.anne.catalogo.model.Musica;

public interface CustomCatalogoRepository {
    List<Musica> findAllByTituloUpper(String titulo);
}
