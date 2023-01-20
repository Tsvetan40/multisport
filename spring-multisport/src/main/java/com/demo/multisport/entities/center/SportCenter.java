package com.demo.multisport.entities.center;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@AllArgsConstructor
@DiscriminatorValue(value = "SportCenter")
@Builder
public class SportCenter extends Center{

    public SportCenter(String name, String address, String description, Set<String> pictures) {
        super(name, address, description, pictures);
    }

    public SportCenter withPlans(Set<Plan> plans) {
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

    public SportCenter withPictures(Set<String> pictures) {
        this.setPictures(pictures);
        return this;
    }

    public SportCenter withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public SportCenter withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    public SportCenter withName(String name) {
        this.setName(name);
        return this;
    }
}
