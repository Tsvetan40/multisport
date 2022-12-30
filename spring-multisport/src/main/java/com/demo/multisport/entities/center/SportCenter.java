package com.demo.multisport.entities.center;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "SportCenter")
public class SportCenter extends Center{

    public SportCenter(String name, String address, String description, Set<String> pictures) {
        super(name, address, description, pictures);
    }
}
