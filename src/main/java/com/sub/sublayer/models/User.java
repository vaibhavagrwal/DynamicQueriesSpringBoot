package com.sub.sublayer.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class User {
    int id;
    String name;
}
