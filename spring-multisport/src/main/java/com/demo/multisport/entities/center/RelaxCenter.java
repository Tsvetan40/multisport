package com.demo.multisport.entities.center;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "RelaxCenter")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RelaxCenter extends Center{
    @ElementCollection
    private Set<String> services;

    public RelaxCenter withServices(Set<String> services) {
        this.services = services;
        return this;
    }

    public RelaxCenter withPlans(Set<Plan> plans) {
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

    public RelaxCenter withPictures(Set<String> pictures) {
        this.setPictures(pictures);
        return this;
    }

    public RelaxCenter withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public RelaxCenter withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    public RelaxCenter withName(String name) {
        this.setName(name);
        return this;
    }
}
