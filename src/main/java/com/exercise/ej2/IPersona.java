package com.exercise.ej2;

import java.util.List;

public interface IPersona {
    List<PersonaOutputDTO> getAllPersona();
    List<PersonaOutputDTO> getPersonaByUsuario(String usuario);
    PersonaOutputDTO getPersonaById(Integer id) throws Exception;
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO setPersona(PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO delPersona(Integer id) throws Exception;
}
