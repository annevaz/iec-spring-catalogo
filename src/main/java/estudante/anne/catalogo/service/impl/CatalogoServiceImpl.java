package estudante.anne.catalogo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estudante.anne.catalogo.model.Musica;
import estudante.anne.catalogo.repository.CatalogoRepository;
import estudante.anne.catalogo.service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {
    @Autowired
    private CatalogoRepository catalogoRepository;

    @Override
    public List<Musica> findAll() {
        return catalogoRepository.findAll();
    }

    @Override
    public Musica findById(long id) {
        return catalogoRepository.findById(id).get();
    }

    @Override
    public Musica save(Musica musica) {
        return catalogoRepository.save(musica);
    }

    @Override
    public void excluir(long id) {
        catalogoRepository.deleteById(id);
    }

    @Override
    public List<Musica> findByTitulo(String titulo) {
        String tituloLike = titulo + "%";

        return catalogoRepository.findAllByTituloIsLike(tituloLike);
    }

    @Override
    public List<Musica> findAllByCategoriaId(Long categoriaId)
    {
        return catalogoRepository.findAllByCategoriaId(categoriaId);
    }
}
