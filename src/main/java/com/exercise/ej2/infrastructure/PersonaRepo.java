package com.exercise.ej2.infrastructure;

import com.exercise.ej2.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepo extends JpaRepository<Persona,String> {

    List<Persona> findByUsuario(String usuario);
}
