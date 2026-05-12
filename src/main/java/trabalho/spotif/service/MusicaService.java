package trabalho.spotif.service;

import trabalho.spotif.model.Musica;

import java.util.ArrayList;
import java.util.List;

public class MusicaService {

    List<Musica> musicas = new ArrayList<>();

    public Musica adicionarMusica(Musica musica) {

        musicas.add(musica);
        System.out.println("musica adicionada com sucesso " + musica.getTitulo());

        return musica;
    }

    public List<Musica> listarMusicas() {
        System.out.println("chamando banco de dados das musicas");
        return musicas;
    }

    public Musica buscarPorId(int id){

       for(Musica musica : musicas){
           if(musica.getId() == id){
               return musica;
           }
       }

        throw new RuntimeException("musica nao encontrada");

    }

    public void removerporId(int id){
        for(Musica musica : musicas){
            if(musica.getId() == id){
                System.out.println("removendo "+ musica.getTitulo());
                musicas.remove(musica);
                return;
            }
        }
    }
}
