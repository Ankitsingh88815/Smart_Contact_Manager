package com.scm.scm20.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long Id;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;
}
