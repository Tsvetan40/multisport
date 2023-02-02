package com.demo.multisport.entities.center;



import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "SportCenter")
public class SportCenter extends Center implements Serializable {

    public SportCenter (){
        super();
    }

    public SportCenter withName(String name) {
        this.setName(name);
        return this;
    }

    public SportCenter withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    public SportCenter withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public SportCenter withPictures(Set<String> pictures) {
        this.setPictures(pictures);
        return this;
    }

    public SportCenter withPlan(Set<Plan> plans) {
        this.setPlans(plans);
        return this;
    }

    public SportCenter withComments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public SportCenter withRating(Rating rating) {
        this.setRating(rating);
        return this;
    }

    public SportCenter withId(Long id) {
        this.setId(id);
        return this;
    }
}
