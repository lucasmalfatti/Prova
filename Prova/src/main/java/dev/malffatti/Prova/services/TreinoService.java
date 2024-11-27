package dev.malffatti.Prova.services;

import dev.malffatti.Prova.entities.Exercicio;
import dev.malffatti.Prova.entities.Treino;
import dev.malffatti.Prova.entities.dtos.AtualizarStatusDTO;
import dev.malffatti.Prova.repositories.ExercicioRepository;
import dev.malffatti.Prova.repositories.TreinoRepository;

import java.util.List;
import java.util.Optional;

public class TreinoService {

    private TreinoRepository treinoRepository;

    private ExercicioRepository exercicioRepository;

    public TreinoService(TreinoRepository treinoRepository, ExercicioRepository exercicioRepository) {
        this.treinoRepository = treinoRepository;
        this.exercicioRepository = exercicioRepository;
    }

    public TreinoService() {

    }

    public Treino criarTreino(Treino treino){
        treinoRepository.save(treino);
        return treino;
    }

    public void deletarTreino(Long codigo){
        treinoRepository.deleteById(codigo);
    }

    public List<Treino> listarTreinos(){
        return treinoRepository.findAll();
    }

    public Optional<Treino> listarTreinoPorId(Long codigo){
        return treinoRepository.findById(codigo);
    }

   public Treino atualizarStatus(AtualizarStatusDTO statusTreino){
        Optional<Treino> treino = treinoRepository.findById(statusTreino.getCodigo());
        treino.get().setStatus(statusTreino.getStatus());
        treinoRepository.save(treino.get());
        return treino.get();
   }

   public boolean removeExercicioDeTreino(Long treinoCodigo, Long exercicioCodigo){
        Optional<Treino> treinoOptional = treinoRepository.findById(treinoCodigo);
        Optional<Exercicio> exercicioOptional = exercicioRepository.findById(exercicioCodigo);

        if (treinoOptional.isPresent() && exercicioOptional.isPresent()){
           Treino treino = treinoOptional.get();
           Exercicio exercicio = exercicioOptional.get();

           if (treino.getExercicios().contains(exercicio)){
               treino.getExercicios().remove(exercicio);
               exercicio.setTreino(null);
               treinoRepository.save(treino);
               treinoRepository.delete(treino);
               return true;
           }
       }
       return false;
   }

}

