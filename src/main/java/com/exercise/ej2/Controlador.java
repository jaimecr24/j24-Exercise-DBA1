package com.exercise.ej2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador {

    @Autowired
    IPersona personaService;

    @GetMapping
    public List<PersonaDTO> getLista()
    {
        return personaService.getAllPersona();
    }

    @GetMapping("/persona/{id}")
    public PersonaDTO getById(@PathVariable Integer id) throws Exception
    {
        return personaService.getPersonaById(id);
    }

    @GetMapping("/usuario/{usuario}")
    public List<PersonaDTO> getByNombre(@PathVariable String usuario) throws Exception
    {
        return personaService.getPersonaByUsuario(usuario);
    }

    @PostMapping("/")
    public PersonaDTO addPersona(@RequestBody Persona persona)
    {
        return personaService.addPersona(persona);
    }
}
