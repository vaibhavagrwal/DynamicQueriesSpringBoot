package com.sub.sublayer.models;


import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    String id;
    String name;
}
