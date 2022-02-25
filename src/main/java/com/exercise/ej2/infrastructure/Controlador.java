package com.exercise.ej2.infrastructure;

import com.exercise.ej2.application.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controlador {

    @Autowired
    IPersona personaService;

    @GetMapping
    public ResponseEntity<PersonaListaOutputDTO> findAll()
    {
        return new ResponseEntity<>(personaService.getAllPersona(),HttpStatus.OK);
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<PersonaOutputDTO> getById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaListaOutputDTO> getByNombre(@PathVariable String usuario)
    {
        return new ResponseEntity<>(personaService.getPersonaByUsuario(usuario), HttpStatus.OK);
    }

    @PostMapping("/persona")
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return new ResponseEntity<>(personaService.addPersona(personaInputDTO), HttpStatus.OK);
    }

    @PutMapping("/persona/{id}")
    public ResponseEntity<PersonaOutputDTO> putPersona(
            @RequestBody PersonaInputDTO personaInputDTO,
            @PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(personaService.putPersona(id, personaInputDTO), HttpStatus.OK);
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<PersonaOutputDTO> delById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(personaService.delPersona(id),HttpStatus.OK);
    }

}
