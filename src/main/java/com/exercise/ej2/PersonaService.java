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
    public List<PersonaOutputDTO> getAllPersona() {
        List<Persona> listaPersona = personaRepo.findAll();
        List<PersonaOutputDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(this.toOutputDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public List<PersonaOutputDTO> getPersonaByUsuario(String usuario) {
        List<Persona> listaPersona = personaRepo.findByUsuario(usuario);
        List<PersonaOutputDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(this.toOutputDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public PersonaOutputDTO getPersonaById(Integer id) throws Exception{
        Persona persona = personaRepo.findById(id).orElseThrow(()->new Exception("id "+id+" not found."));
        return this.toOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) {
        Persona persona = this.toPersona(personaInputDTO);
        persona.setId(null);
        Persona persona1 = personaRepo.save(persona);
        return this.toOutputDTO(persona1);
    }

    @Override
    public PersonaOutputDTO setPersona(PersonaInputDTO personaInputDTO) throws Exception{
        Persona persona = personaRepo.findById(personaInputDTO.getId()).orElseThrow(()->new Exception("id "+personaInputDTO.getId()+" not found."));
        persona.setPassword(personaInputDTO.getPassword());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setName(personaInputDTO.getName());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompany_email(personaInputDTO.getCompany_email());
        persona.setPersonal_email(personaInputDTO.getPersonal_email());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.isActive());
        persona.setCreated_date(personaInputDTO.getCreated_date());
        persona.setImagen_url(personaInputDTO.getImagen_url());
        persona.setTermination_date(personaInputDTO.getTermination_date());
        personaRepo.save(persona);
        return this.toOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO delPersona(Integer id) throws Exception{
        Persona persona = personaRepo.findById(id).orElseThrow(()->new Exception("id "+id+" not found."));
        PersonaOutputDTO personaOutputDTO = this.toOutputDTO(persona);
        personaRepo.delete(persona);
        return personaOutputDTO;
    }

    private PersonaOutputDTO toOutputDTO(Persona persona){
        PersonaOutputDTO personaDTO = new PersonaOutputDTO();
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

    private Persona toPersona(PersonaInputDTO personaInputDTO){
        Persona persona = new Persona();
        persona.setId(personaInputDTO.getId());
        persona.setPassword(personaInputDTO.getPassword());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompany_email(personaInputDTO.getCompany_email());
        persona.setPersonal_email(personaInputDTO.getPersonal_email());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.isActive());
        persona.setCreated_date(personaInputDTO.getCreated_date());
        persona.setImagen_url(personaInputDTO.getImagen_url());
        persona.setTermination_date(personaInputDTO.getTermination_date());
        return persona;
    }
}
