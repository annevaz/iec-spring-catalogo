package estudante.anne.catalogo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import estudante.anne.catalogo.model.Musica;

public class CustomCatalogoRepositoryImpl implements CustomCatalogoRepository {

    private final EntityManager em;

    public CustomCatalogoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Musica> findAllByTituloUpper(String titulo) {
        String tituloUpper = titulo.toUpperCase();

        return em.createQuery("select m from Musica m where m.titulo = :titulo", Musica.class)
            .setParameter("titulo", tituloUpper)
            .getResultList();
    }
}
