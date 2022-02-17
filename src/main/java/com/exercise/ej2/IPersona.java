package com.exercise.ej2;

import java.util.List;

public interface IPersona {
    List<PersonaDTO> getAllPersona();
    List<PersonaDTO> getPersonaByUsuario(String usuario);
    PersonaDTO getPersonaById(Integer id) throws Exception;
    PersonaDTO addPersona(Persona persona);
}
