package com.demo.multisport.entities.center;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SportCenter")
public class SportCenter extends Center{
}
