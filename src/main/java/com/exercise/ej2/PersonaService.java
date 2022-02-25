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
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception {
        this.validar(personaInputDTO);
        Persona persona = this.toPersona(personaInputDTO);
        persona.setId(null);
        Persona persona1 = personaRepo.save(persona);
        return this.toOutputDTO(persona1);
    }

    @Override
    public PersonaOutputDTO setPersona(PersonaInputDTO personaInputDTO) throws Exception{
        this.validar(personaInputDTO);
        Persona persona = personaRepo.findById(personaInputDTO.id()).orElseThrow(()->new Exception("id "+personaInputDTO.id()+" not found."));
        persona.setPassword(personaInputDTO.password());
        persona.setUsuario(personaInputDTO.usuario());
        persona.setName(personaInputDTO.name());
        persona.setUsuario(personaInputDTO.usuario());
        persona.setSurname(personaInputDTO.surname());
        persona.setCompany_email(personaInputDTO.company_email());
        persona.setPersonal_email(personaInputDTO.personal_email());
        persona.setCity(personaInputDTO.city());
        persona.setActive(personaInputDTO.active());
        persona.setCreated_date(personaInputDTO.created_date());
        persona.setImagen_url(personaInputDTO.imagen_url());
        persona.setTermination_date(personaInputDTO.termination_date());
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
        return new PersonaOutputDTO(
                persona.getId(),
                persona.getUsuario(),
                persona.getName(),
                persona.getSurname(),
                persona.getCompany_email(),
                persona.getPersonal_email(),
                persona.getCity(),
                persona.isActive(),
                persona.getCreated_date(),
                persona.getImagen_url(),
                persona.getTermination_date()
        );
    }

    private Persona toPersona(PersonaInputDTO personaInputDTO){
        Persona persona = new Persona();
        persona.setId(personaInputDTO.id());
        persona.setPassword(personaInputDTO.password());
        persona.setUsuario(personaInputDTO.usuario());
        persona.setName(personaInputDTO.name());
        persona.setSurname(personaInputDTO.surname());
        persona.setCompany_email(personaInputDTO.company_email());
        persona.setPersonal_email(personaInputDTO.personal_email());
        persona.setCity(personaInputDTO.city());
        persona.setActive(personaInputDTO.active());
        persona.setCreated_date(personaInputDTO.created_date());
        persona.setImagen_url(personaInputDTO.imagen_url());
        persona.setTermination_date(personaInputDTO.termination_date());
        return persona;
    }

    private void validar(PersonaInputDTO personaInputDTO) throws Exception{
        String usuario = personaInputDTO.usuario();
        if (usuario==null) throw new Exception("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new Exception("Error: user length must be between 6 and 10 characters");
        if (personaInputDTO.password()==null) throw new Exception("Error: password is null.");
        if (personaInputDTO.name()==null) throw new Exception("Error: name is null.");
        if (personaInputDTO.company_email()==null) throw new Exception("Error: Company_email is null.");
        if (personaInputDTO.personal_email()==null) throw new Exception("Error: Personal_email is null.");
        if (personaInputDTO.city()==null) throw new Exception("Error: City is null.");
        if (personaInputDTO.created_date()==null) throw new Exception("Error: Created_date is null");
    }
}
