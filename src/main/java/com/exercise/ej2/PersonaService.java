package com.exercise.ej2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona{

    private final PersonaRepo personaRepo;

    public PersonaService(PersonaRepo personaRepo){
        super();
        this.personaRepo = personaRepo;
    }

    @Override
    public List<PersonaDTO> getAllPersona() {
        List<Persona> listaPersona = personaRepo.findAll();
        List<PersonaDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(this.getDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public List<PersonaDTO> getPersonaByUsuario(String usuario) {
        List<Persona> listaPersona = personaRepo.findByUsuario(usuario);
        List<PersonaDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(this.getDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public PersonaDTO getPersonaById(Integer id) throws Exception{
        Persona persona = personaRepo.findById(id).orElseThrow(()->new Exception("id "+id.toString()+" not found."));
        return this.getDTO(persona);
    }

    @Override
    public PersonaDTO addPersona(Persona persona) {
        Persona persona1 = personaRepo.save(persona);
        return this.getDTO(persona);
    }

    private PersonaDTO getDTO(Persona persona){
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(persona.getId());
        personaDTO.setUsuario(persona.getUsuario());
        personaDTO.setName(persona.getName());
        personaDTO.setSurname(persona.getSurname());
        personaDTO.setCompany_email(persona.getCompany_email());
        personaDTO.setPersonal_email(persona.getPersonal_email());
        personaDTO.setCity(persona.getCity());
        personaDTO.setActive(persona.isActive());
        personaDTO.setCreated_date(persona.getCreated_date());
        personaDTO.setImagen_url(persona.getImagen_url());
        personaDTO.setTermination_date(persona.getTermination_date());
        return personaDTO;
    }
}
