package com.microservices.ReviewMs.review;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private float rating;

    private int companyId;
}

/*
Reasons for Having a Default Constructor:
Required by JPA:

JPA (Java Persistence API) requires that entity classes have a public or protected no-argument constructor. This is because JPA uses reflection to create instances of your entity classes when retrieving data from the database.
        Serialization:

A default constructor is often required for frameworks (like Jackson) that serialize and deserialize objects, especially when converting JSON data to Java objects and vice versa.
Flexibility:

Including a no-argument constructor can make it easier to create instances of your entities without needing to provide initial values for all fields, which can be helpful in testing or when using frameworks that instantiate objects.
*/
