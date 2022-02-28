package com.exercise.ej2.infrastructure;

import com.exercise.ej2.application.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;


@RestController
public class PersonaController {

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

    @GetMapping("/user/{user}")
    public ResponseEntity<PersonaListaOutputDTO> getByUser(@PathVariable String user)
    {
        return new ResponseEntity<>(personaService.getPersonaByUser(user), HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<PersonaListaOutputDTO> getSelect(
            @RequestParam(required=false) String user,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String surname,
            @RequestParam(required=false) @DateTimeFormat(pattern="dd-MM-yyyy") Date dateInf,
            @RequestParam(required=false) @DateTimeFormat(pattern="dd-MM-yyyy") Date dateSup,
            @RequestParam(required=false) String sorted, // "user" o "name"
            @RequestParam(required=false) Integer page,
            @RequestParam(required=false, defaultValue = "10") int limit)
    {
        HashMap<String, Object> conditions=new HashMap<>();

        if (user!=null) conditions.put("user",user);
        if (name!=null) conditions.put("name",name);
        if (surname!=null) conditions.put("surname",surname);
        if (dateInf!=null) conditions.put("dateInf", dateInf);
        if (dateSup!=null) conditions.put("dateSup", dateSup);
        if (sorted!=null) conditions.put("sorted", sorted); // user or name
        if (page!=null) conditions.put("page",page);
        conditions.put("limit",limit);
        return new ResponseEntity<>(personaService.getSelect(conditions, true), HttpStatus.OK);
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
