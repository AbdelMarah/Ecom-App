package com.youtube.ecommerce.entity;

//import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
//@Table(name = "user_123")
//@Qualifier(“myBean”) //permet de donner un nom à ce bean, en l'occurrence “myBean” (si cette annotation n’est pas définie, le nom par défaut est le nom de la classe)
//En réalité, il n’y a pas vraiment une meilleure façon de faire, les 2 sont valides. La configuration via XML est la façon historique de faire, et on la retrouve encore sur de nombreux projets. Tandis que la configuration par annotations est ce qui s’utilise de plus en plus, notamment par la démocratisation de Spring Boot.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La génération de la clé primaire se fera à partir d’une identité propre au SGBD. Il utilise un type de colonne spéciale à la base de données. Exemple pour MySQL, il s’agit d’un AUTO_INCREMENT.
    //@GeneratedValue
    private Integer userId; //An int is a 32-bit integer; a long is a 64-bit integer. / privete UUID id : generate random id unique
    //@Column(unique=true)
    private String userName;
    //@Column(name="first_name")
    //@JsonProperty("Firstname")
    private String userFirstName;
    private String userLastName;
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId"),
            inverseForeignKey = @ForeignKey(name="ROLE_NAME")
    )
    //@JoinColumn(name="ADDR_ID", referencedColumnName="ID")
    private Set<Role> role = new HashSet<Role>();
    private String numero;
    private String mail;
    private String adresse; //A voir

    /*  fetch = FetchType. : have two options for how roles should be loaded:
            To load it together with the rest(id,name,...) of the fields (EAGER), or
            To load it on-demand (LAZY) when you call the getRole() method.

         cascade = CascadeType.ALL :
         What Is Cascading?
            Entity relationships often depend on the existence of another entity, for example the Person–Address relationship. Without the Person, the Address entity doesn’t have any meaning of its own. When we delete the Person entity, our Address entity should also get deleted.
            Cascading is the way to achieve this. When we perform some action on the target entity, the same action will be applied to the associated entity.

            1. JPA Cascade Type
                All JPA-specific cascade operations are represented by the javax.persistence.CascadeType enum containing entries:
                    ALL
                    PERSIST
                    MERGE
                    REMOVE
                    REFRESH
                    DETACH
            2. Hibernate Cascade Type
                Hibernate supports three additional Cascade Types along with those specified by JPA. These Hibernate-specific Cascade Types are available in org.hibernate.annotations.CascadeType:
                    REPLICATE
                    SAVE_UPDATE
                    LOCK
     */

    /*
    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE_MAPPING",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"),
            foreignKey = @ForeignKey(name="FK__STUDENT_COURSE_MAPPING__STUDENT"),
            inverseForeignKey = @ForeignKey(name="FK__STUDENT_COURSE_MAPPING__COURSE"),
            uniqueConstraints = @UniqueConstraint(name="UK__STUDENT_COURSE_MAPPING",columnNames = {"STUDENT_ID","COURSE_ID"})
    )
    private Set<Course> courses;

     */

}
