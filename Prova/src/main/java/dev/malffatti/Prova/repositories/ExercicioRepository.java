package dev.malffatti.Prova.repositories;

import dev.malffatti.Prova.entities.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
}
