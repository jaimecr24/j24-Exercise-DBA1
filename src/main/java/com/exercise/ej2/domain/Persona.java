package com.exercise.ej2.domain;

import com.exercise.ej2.shared.StringSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_seq")
    @GenericGenerator(
            name = "persona_seq",
            strategy = "com.exercise.ej2.shared.StringSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    //@Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    //@Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id_persona;

    @Size(min=6,max=10)
    @NotBlank(message = "usuario es nulo")
    private String usuario;

    @NotBlank(message = "password es nulo")
    private String password;

    @NotBlank(message = "nombre es nulo")
    private String name;

    private String surname;

    @NotBlank(message = "company_email es nulo")
    private String company_email;

    @NotBlank(message = "personal_email es nulo")
    private String personal_email;

    @NotBlank(message = "ciudad es nulo")
    private String city;

    @NotBlank(message = "active es nulo") // Clase Boolean sí puede ser nulo
    private Boolean active;

    @NotBlank(message = "created_date es nulo")
    private Date created_date;

    private String imagen_url;

    private Date termination_date;
}
