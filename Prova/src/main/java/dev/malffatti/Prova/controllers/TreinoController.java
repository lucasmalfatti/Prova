package dev.malffatti.Prova.controllers;

import dev.malffatti.Prova.entities.Exercicio;
import dev.malffatti.Prova.entities.Treino;
import dev.malffatti.Prova.entities.dtos.AtualizarStatusDTO;
import dev.malffatti.Prova.services.TreinoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/treino")
public class TreinoController {

    private TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    public TreinoController() {
    }

    @GetMapping("/treino/bucar")
    public ResponseEntity<?> listarTreinos(){
        try{
            List<Treino> treinos = treinoService.listarTreinos();
            return ResponseEntity.ok(treinos);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao consultar", HttpStatusCode.valueOf(504));
        }
    }

    @GetMapping("/treino/buscar/{codigo}")
    public ResponseEntity<?> listarTreinoPorId(@PathVariable Long codigo){
        try {
            Optional<Treino> treino  = treinoService.listarTreinoPorId(codigo);
            if (treinoService.listarTreinoPorId(codigo) != null){
                return ResponseEntity.ok(treino);
            }else{
                return new ResponseEntity("Treino inexistente", HttpStatusCode.valueOf(504));
            }
        }catch (Exception e){
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }

    @PostMapping("/treino/add")
    public ResponseEntity<?> criarTreino(@RequestBody Treino treino){
        try {
            Treino treinoAdd = treinoService.criarTreino(treino);
            return ResponseEntity.ok(treinoAdd);
        }catch (Exception e){
            return new ResponseEntity("Erro ao criar um novo treino", HttpStatusCode.valueOf(504));
        }
    }

    @PatchMapping("/treino/alterar/status")
    public ResponseEntity<?> atualizarValorStatus(@RequestBody AtualizarStatusDTO atualizarStatusDTO){
        try {
            Treino treino = treinoService.atualizarStatus(atualizarStatusDTO);
            return ResponseEntity.ok(treino);
        }catch (Exception e){
            return new ResponseEntity("Erro ao atualizar o status", HttpStatusCode.valueOf(504));
        }
    }

    @DeleteMapping("/treino/delete{codigo}")
    public ResponseEntity<?> deletarTreino(@PathVariable Long codigo){
        try {
            treinoService.deletarTreino(codigo);
            return ResponseEntity.ok("Treino removido com sucesso");
        }catch (Exception e){
            return new ResponseEntity("Erro ao excluir", HttpStatusCode.valueOf(504));
        }
    }

    @DeleteMapping("/{treinoCodigo}/exercicio/{exercicioCodigo}")
        public ResponseEntity<Void> removeExercicioDeTreino(Long treinoCodigo, Long exercicioCodigo) {

        boolean removido = treinoService.removeExercicioDeTreino(treinoCodigo, exercicioCodigo);

        if (removido) {
            return ResponseEntity.noContent().build();
        } else {
           return ResponseEntity.notFound().build();
        }
    }

}
