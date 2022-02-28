package com.exercise.ej2.infrastructure;

import com.exercise.ej2.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface PersonaRepo extends JpaRepository<Persona,String> {

    List<Persona> findByUser(String user);
    List<Persona> getData(HashMap<String, Object> conditions);
}
