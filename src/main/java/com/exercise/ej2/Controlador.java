package com.exercise.ej2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador {

    @Autowired
    IPersona personaService;

    @GetMapping
    public List<PersonaOutputDTO> getLista()
    {
        return personaService.getAllPersona();
    }

    @GetMapping("/persona/{id}")
    public PersonaOutputDTO getById(@PathVariable Integer id) throws Exception
    {
        return personaService.getPersonaById(id);
    }

    @GetMapping("/usuario/{usuario}")
    public List<PersonaOutputDTO> getByNombre(@PathVariable String usuario) throws Exception
    {
        return personaService.getPersonaByUsuario(usuario);
    }

    @PostMapping("/persona")
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return personaService.addPersona(personaInputDTO);
    }

    @PutMapping("/persona")
    public PersonaOutputDTO setPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return personaService.setPersona(personaInputDTO);
    }

    @DeleteMapping("/persona/{id}")
    public PersonaOutputDTO delById(@PathVariable Integer id) throws Exception
    {
        return personaService.delPersona(id);
    }

}
