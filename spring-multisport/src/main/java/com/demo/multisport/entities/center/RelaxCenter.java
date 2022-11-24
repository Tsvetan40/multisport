package com.demo.multisport.entities.center;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "RelaxCenter")
public class RelaxCenter extends Center{
    @ElementCollection
    private Set<String> services;
}
