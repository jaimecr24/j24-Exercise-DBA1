package com.exercise.ej2.application;

import com.exercise.ej2.infrastructure.PersonaInputDTO;
import com.exercise.ej2.infrastructure.PersonaListaOutputDTO;
import com.exercise.ej2.infrastructure.PersonaOutputDTO;

import java.util.HashMap;

public interface IPersona {
    PersonaListaOutputDTO getAllPersona();
    PersonaListaOutputDTO getPersonaByUser(String usuario);
    PersonaOutputDTO getPersonaById(String id) throws Exception;
    PersonaListaOutputDTO getSelect(HashMap<String,Object> conditions, boolean getTotalItems);
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO putPersona(String id, PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO delPersona(String id) throws Exception;
}
