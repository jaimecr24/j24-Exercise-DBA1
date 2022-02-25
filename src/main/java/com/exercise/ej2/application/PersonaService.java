package com.exercise.ej2.application;

import com.exercise.ej2.infrastructure.PersonaInputDTO;
import com.exercise.ej2.infrastructure.PersonaListaOutputDTO;
import com.exercise.ej2.infrastructure.PersonaOutputDTO;
import com.exercise.ej2.infrastructure.PersonaRepo;
import com.exercise.ej2.domain.Persona;
import com.exercise.ej2.shared.NotFoundException;
import com.exercise.ej2.shared.UnprocesableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersona {

    private final PersonaRepo personaRepo;

    public PersonaService(PersonaRepo personaRepo){
        super();
        this.personaRepo = personaRepo;
    }

    // @Autowired PersonaRepo personaRepo;

    @Override
    public PersonaListaOutputDTO getAllPersona() {
        List<Persona> listaPersona = personaRepo.findAll();
        List<PersonaOutputDTO> listaDTO = listaPersona.stream().map(this::toOutputDTO).collect(Collectors.toList());
        return new PersonaListaOutputDTO(listaDTO.size(), listaDTO);
    }

    @Override
    public PersonaListaOutputDTO getPersonaByUsuario(String usuario) {
        List<Persona> listaPersona = personaRepo.findByUsuario(usuario);
        List<PersonaOutputDTO> listaDTO = listaPersona.stream().map(this::toOutputDTO).collect(Collectors.toList());
        return new PersonaListaOutputDTO(listaDTO.size(), listaDTO);
    }

    @Override
    public PersonaOutputDTO getPersonaById(String id) throws NotFoundException{
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id "+id+" not found."));
        return this.toOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws UnprocesableException {
        this.validar(personaInputDTO);
        Persona persona = PersonaInputDTO.toPersona(personaInputDTO);
        persona.setCreated_date(new Date());
        Persona persona1 = personaRepo.save(persona);
        return this.toOutputDTO(persona1);
    }

    @Override
    public PersonaOutputDTO putPersona(String id, PersonaInputDTO personaInputDTO) throws NotFoundException, UnprocesableException{
        this.validar(personaInputDTO);
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id "+id+" not found."));
        persona.setPassword(personaInputDTO.password());
        persona.setUsuario(personaInputDTO.usuario());
        persona.setName(personaInputDTO.name());
        persona.setUsuario(personaInputDTO.usuario());
        persona.setSurname(personaInputDTO.surname());
        persona.setCompany_email(personaInputDTO.company_email());
        persona.setPersonal_email(personaInputDTO.personal_email());
        persona.setCity(personaInputDTO.city());
        persona.setActive(personaInputDTO.active());
        persona.setImagen_url(personaInputDTO.imagen_url());
        persona.setTermination_date(personaInputDTO.termination_date());
        personaRepo.save(persona);
        return this.toOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO delPersona(String id) throws NotFoundException{
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id "+id+" not found."));
        PersonaOutputDTO personaOutputDTO = this.toOutputDTO(persona);
        personaRepo.delete(persona);
        return personaOutputDTO;
    }

    private PersonaOutputDTO toOutputDTO(Persona persona){
        return new PersonaOutputDTO(
                persona.getId_persona(),
                persona.getUsuario(),
                persona.getName(),
                persona.getSurname(),
                persona.getCompany_email(),
                persona.getPersonal_email(),
                persona.getCity(),
                persona.getActive(),
                persona.getCreated_date(),
                persona.getImagen_url(),
                persona.getTermination_date()
        );
    }

    private void validar(PersonaInputDTO personaInputDTO) throws UnprocesableException{
        String usuario = personaInputDTO.usuario();
        if (usuario==null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (personaInputDTO.password()==null) throw new UnprocesableException("Error: password is null.");
        if (personaInputDTO.name()==null) throw new UnprocesableException("Error: name is null.");
        if (personaInputDTO.company_email()==null) throw new UnprocesableException("Error: Company_email is null.");
        if (personaInputDTO.personal_email()==null) throw new UnprocesableException("Error: Personal_email is null.");
        if (personaInputDTO.city()==null) throw new UnprocesableException("Error: City is null.");
        if (personaInputDTO.active()==null) throw new UnprocesableException("Error: Active is null");
    }
}
