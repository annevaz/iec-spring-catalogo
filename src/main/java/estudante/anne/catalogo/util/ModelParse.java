package estudante.anne.catalogo.util;

import estudante.anne.catalogo.dto.MusicaDto;
import estudante.anne.catalogo.model.Categoria;
import estudante.anne.catalogo.model.Musica;

public class ModelParse {
    public static Musica toMusic(MusicaDto musicaDto)
    {
        Musica musica = new Musica();

        musica.setId(musicaDto.getId());
        musica.setAutor(musicaDto.getAutor());
        musica.setTitulo(musicaDto.getTitulo());
        musica.setData(musicaDto.getData());
        musica.setLetra(musicaDto.getLetra());

        Categoria categoria = new Categoria();

        categoria.setId(musicaDto.getCategoriaId());

        musica.setCategoria(categoria);

        return musica;
    }

    public static MusicaDto toMusicDto(Musica musica)
    {
        MusicaDto musicaDto = new MusicaDto();

        musicaDto.setId(musica.getId());
        musicaDto.setAutor(musica.getAutor());
        musicaDto.setTitulo(musica.getTitulo());
        musicaDto.setData(musica.getData());
        musicaDto.setLetra(musica.getLetra());
        musicaDto.setCategoriaId(musica.getCategoria().getId());

        return musicaDto;
    }
}
