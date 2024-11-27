package dev.malffatti.Prova.controllers;


import dev.malffatti.Prova.entities.Exercicio;
import dev.malffatti.Prova.services.ExercicioService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {

    private ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> listarExercicios(){
        try{
            List<Exercicio> exercicios = exercicioService.listarExercicios();
            return ResponseEntity.ok(exercicios);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao consultar", HttpStatusCode.valueOf(504));
        }
    }

    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<?> listarExercicioId(@PathVariable Long codigo){
        try {
            Optional<Exercicio> exercicio = exercicioService.listarExercicioPorId(codigo);
            if (exercicioService.listarExercicioPorId(codigo) != null){
                return ResponseEntity.ok(exercicio);
            }else{
                return new ResponseEntity("Exercicio inexistente", HttpStatusCode.valueOf(504));
            }
        }catch (Exception e){
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> criarExercicio(@RequestBody Exercicio exercicio){
        try {
            Exercicio exercicioAdd = exercicioService.criarExercicio(exercicio);
            return ResponseEntity.ok(exercicioAdd);
        }catch (Exception e){
            return new ResponseEntity("Erro ao criar um exercicio", HttpStatusCode.valueOf(504));
        }
    }

    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deletarExercicio(@PathVariable Long codigo){
        try {
            exercicioService.deletarExercicio(codigo);
            return ResponseEntity.ok("Exercicio removido com sucesso");
        }catch (Exception e){
            return new ResponseEntity("Erro ao excluir", HttpStatusCode.valueOf(504));

        }
    }


}
