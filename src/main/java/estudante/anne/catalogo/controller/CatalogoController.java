package estudante.anne.catalogo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import estudante.anne.catalogo.dto.MusicaDto;
import estudante.anne.catalogo.model.Categoria;
import estudante.anne.catalogo.model.Musica;
import estudante.anne.catalogo.service.CatalogoService;
import estudante.anne.catalogo.service.CategoriaService;
import estudante.anne.catalogo.util.ModelParse;

@Controller
public class CatalogoController {
    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value="/musicas", method=RequestMethod.GET)
    public ModelAndView getMusicas() {
        ModelAndView mv = new ModelAndView("musicas");

        List<Musica> musicas = catalogoService.findAll();
        mv.addObject("musicas", musicas);

        List<Categoria> categorias = categoriaService.findAll();
        mv.addObject("categorias", categorias);

        return mv;
    }

    @RequestMapping(value="/musicas/{id}", method=RequestMethod.GET)
    public ModelAndView getMusicaDetalhes(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("musicaDetalhes");

        Musica musica = catalogoService.findById(id);

        mv.addObject("musica", musica);

        return mv;
    }

    @RequestMapping(value = "/musicas/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getFormEdit(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("musicaForm");

        Musica musica = catalogoService.findById(id);
        mv.addObject("musica", ModelParse.toMusicDto(musica));

        List<Categoria> categorias = categoriaService.findAll();
        mv.addObject("categorias", categorias);

        mv.addObject("titulo", "Editar música");

        return mv;
    }

    @RequestMapping(value="/addMusica", method=RequestMethod.GET)
    public ModelAndView getMusicaForm() {
        ModelAndView mv = new ModelAndView("musicaForm");

        MusicaDto musica = new MusicaDto();
        mv.addObject("musica", musica);

        List<Categoria> categorias = categoriaService.findAll();
        mv.addObject("categorias", categorias);

        mv.addObject("titulo", "Nova música");

        return mv;
    }

    @RequestMapping(value="/addMusica", method=RequestMethod.POST)
    public ModelAndView salvarMusica(@Valid @ModelAttribute("musica") MusicaDto musicaDto,
           BindingResult result, Model model) {
        if (result.hasErrors()) {
            ModelAndView musicaForm = new ModelAndView("musicaForm");
            musicaForm.addObject("mensagem", "Verifique os erros do formulário");

            return musicaForm;
        }

        Musica musica = ModelParse.toMusic(musicaDto);
        musica.setData(LocalDate.now());

        catalogoService.save(musica);

        return new ModelAndView("redirect:/musicas");
    }

    @GetMapping("/musicas/pesquisarTitulo")
    public ModelAndView pesquisar(@RequestParam("titulo") String titulo) {
        ModelAndView mv = new ModelAndView("musicas");

        List<Musica> musicas = catalogoService.findByTitulo(titulo);
        mv.addObject("musicas", musicas);

        List<Categoria> categorias = categoriaService.findAll();
        mv.addObject("categorias", categorias);

        return mv;
    }

    @GetMapping("/musicas/pesquisarCategoria")
    public ModelAndView pesquisar(@RequestParam("categoriaId") Long categoriaId) {
        ModelAndView mv = new ModelAndView("musicas");

        List<Musica> musicas = catalogoService.findAllByCategoriaId(categoriaId);
        mv.addObject("musicas", musicas);

        List<Categoria> categorias = categoriaService.findAll();
        mv.addObject("categorias", categorias);

        mv.addObject("categoriaId", categoriaId);

        return mv;
    }

    @RequestMapping(value="/delMusica/{id}", method=RequestMethod.GET)
    public String delMusica(@PathVariable("id") long id) {
        catalogoService.excluir(id);

        return "redirect:/musicas";
    }
}
