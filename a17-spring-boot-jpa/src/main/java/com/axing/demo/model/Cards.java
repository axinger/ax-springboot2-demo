package com.axing.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

@Entity(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy=false)
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name = "card_type")
    private String cardType;

}
