package dev.malffatti.Prova.services;


import dev.malffatti.Prova.entities.Exercicio;
import dev.malffatti.Prova.repositories.ExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    private ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public List<Exercicio> listarExercicios(){
        return exercicioRepository.findAll();
    }

    public Optional<Exercicio> listarExercicioPorId(Long codigo){
        return exercicioRepository.findById(codigo);
    }

    public Exercicio criarExercicio(Exercicio exercicio){
        exercicioRepository.save(exercicio);
        return exercicio;
    }

    public void deletarExercicio(Long codigo){
        exercicioRepository.deleteById(codigo);
    }



}
