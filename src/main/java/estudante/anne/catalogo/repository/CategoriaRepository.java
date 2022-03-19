package estudante.anne.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import estudante.anne.catalogo.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
