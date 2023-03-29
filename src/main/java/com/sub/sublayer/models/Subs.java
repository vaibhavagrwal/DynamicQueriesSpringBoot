package com.sub.sublayer.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subs {
    @Id
    String id;
    String order_id;
}
