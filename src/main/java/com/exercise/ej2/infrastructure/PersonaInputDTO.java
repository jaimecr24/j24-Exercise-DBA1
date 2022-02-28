package com.exercise.ej2.infrastructure;

import com.exercise.ej2.domain.Persona;

import java.util.Date;

public record PersonaInputDTO(
        String user,
        String password,
        String name,
        String surname,
        String company_email,
        String personal_email,
        String city,
        Boolean active,
        String imagen_url,
        Date termination_date
)
{
    public static Persona toPersona(PersonaInputDTO personaInputDTO){
        Persona persona = new Persona();
        persona.setPassword(personaInputDTO.password());
        persona.setUser(personaInputDTO.user());
        persona.setName(personaInputDTO.name());
        persona.setSurname(personaInputDTO.surname());
        persona.setCompany_email(personaInputDTO.company_email());
        persona.setPersonal_email(personaInputDTO.personal_email());
        persona.setCity(personaInputDTO.city());
        persona.setActive(personaInputDTO.active());
        persona.setImagen_url(personaInputDTO.imagen_url());
        persona.setTermination_date(personaInputDTO.termination_date());
        return persona;
    }
}

/*
@Data
public class PersonaInputDTO {
    private Integer id;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;
}*/
