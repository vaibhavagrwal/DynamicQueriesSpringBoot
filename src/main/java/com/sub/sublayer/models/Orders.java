package com.sub.sublayer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Orders {
    @Id
    String id;
    String user_id;
}
