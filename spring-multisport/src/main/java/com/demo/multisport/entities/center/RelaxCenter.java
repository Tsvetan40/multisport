package com.demo.multisport.entities.center;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "RelaxCenter")
@AllArgsConstructor
@NoArgsConstructor
public class RelaxCenter extends Center{
    @ElementCollection
    private Set<String> services;
}
