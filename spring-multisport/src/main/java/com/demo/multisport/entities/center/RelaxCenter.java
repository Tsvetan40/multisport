package com.demo.multisport.entities.center;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "RelaxCenter")
@Getter
@Setter
public class RelaxCenter extends Center implements Serializable {

    public RelaxCenter() {
        super();
    }

    @NonNull
    @ElementCollection
    private Set<String> services;


    public RelaxCenter withName(String name) {
        this.setName(name);
        return this;
    }

    public RelaxCenter withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    public RelaxCenter withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public RelaxCenter withPictures(Set<String> pictures) {
        this.setPictures(pictures);
        return this;
    }

    public RelaxCenter withPlan(Set<Plan> plans) {
        this.setPlans(plans);
        return this;
    }

    public RelaxCenter withComments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public RelaxCenter withRating(Rating rating) {
        this.setRating(rating);
        return this;
    }

    public RelaxCenter withServices(@NonNull Set<String> services) {
        this.services = services;
        return this;
    }
}
