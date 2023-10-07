package com.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String price;

    private String showingtime;

    private String synopsis;

    private String imageurl;



}
