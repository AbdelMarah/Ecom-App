package com.youtube.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La génération de la clé primaire se fera à partir d’une identité propre au SGBD. Il utilise un type de colonne spéciale à la base de données. Exemple pour MySQL, il s’agit d’un AUTO_INCREMENT.
    //@GeneratedValue
    private Integer roleId;
    private String roleName;
    private String roleDescription;

    }
