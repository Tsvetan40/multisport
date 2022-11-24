package com.demo.multisport.entities.page;

import com.demo.multisport.entities.center.Center;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double rate;

    @OneToOne(mappedBy = "rating")
    private Center center;
}
