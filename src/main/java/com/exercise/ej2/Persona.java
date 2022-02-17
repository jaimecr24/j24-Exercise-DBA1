package com.exercise.ej2;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Persona {

    @Id
    @GeneratedValue
    Integer id;

    @Size(min=6,max=10)
    @Column(nullable = false)
    String usuario;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String name;

    @Column
    String surname;

    @Email
    @Column(nullable = false)
    String company_email;

    @Email
    @Column(nullable = false)
    String personal_email;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    boolean active;

    @Column(nullable = false)
    Date created_date;

    @Column
    String imagen_url;

    @Column
    Date termination_date;
}
