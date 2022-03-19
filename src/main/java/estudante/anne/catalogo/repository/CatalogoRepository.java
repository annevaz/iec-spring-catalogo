package estudante.anne.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import estudante.anne.catalogo.model.Musica;

public interface CatalogoRepository extends JpaRepository<Musica, Long>, CustomCatalogoRepository {
    List<Musica> findAllByTitulo(String titulo);
    List<Musica> findAllByTituloIsLike(String titulo);

    @Query("select m from Musica m where m.titulo like :titulo")
    List<Musica> findAllWithTituloLike(String titulo);

    @Query("select m from Musica m join m.categoria c where c.id = :categoriaId")
    List<Musica> findAllByCategoriaId(Long categoriaId);
}
