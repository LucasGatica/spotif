package trabalho.spotif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import trabalho.spotif.model.GenerosMusicais;
import trabalho.spotif.model.Musica;
import trabalho.spotif.service.MusicaService;

@Controller
public class MusicaController {

    private final MusicaService musicaService = new MusicaService();

    @GetMapping({"/", "/musicas"})
    public String home(Model model) {
        var musicas = musicaService.listarMusicas();
        for (Musica musica : musicas) {
            System.out.println(musica.getTitulo());
        }
        model.addAttribute("musicas", musicas);
        model.addAttribute("buscaRealizada", false);
        model.addAttribute("musicaBuscada", null);
        return "home";
    }

    @GetMapping("/musicas/adicionar")
    public String adicionarPagina(Model model) {
        model.addAttribute("generos", GenerosMusicais.values());
        return "adicionar";
    }

    @GetMapping("/musicas/removerPagina")
    public String removerPagina() {
        return "remover";
    }

    @PostMapping("/musicas")
    public String adicionar(@ModelAttribute Musica musica) {
        Musica musicaAdicionada = musicaService.adicionarMusica(musica);
        System.out.println("musicaAdicionada: " + musicaAdicionada.getTitulo());
        return "redirect:/musicas/adicionar";
    }

    @PostMapping("/musicas/remover")
    public String remover(@RequestParam int id) {
        System.out.println("removendo musica com id " + id);
        musicaService.removerporId(id);
        return "redirect:/musicas";
    }

    @PostMapping("/musicas/buscar")
    public String buscar(@RequestParam int id, Model model) {
        var musicas = musicaService.listarMusicas();
        model.addAttribute("musicas", musicas);
        model.addAttribute("buscaRealizada", true);

        try {
            Musica musicaAchada = musicaService.buscarPorId(id);
            System.out.println("musica encontrada: " + musicaAchada.getTitulo());
            model.addAttribute("musicaBuscada", musicaAchada);
        } catch (RuntimeException e) {
            model.addAttribute("musicaBuscada", null);
        }

        return "home";
    }


}
