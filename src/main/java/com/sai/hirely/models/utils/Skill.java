package com.sai.hirely.models.utils;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "skills",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_skill_contraint",
                columnNames = "name"
        )
)
public class Skill
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "skill_seq"
    )
    @SequenceGenerator(name = "skill_seq",allocationSize = 50)
    private Long id;
    @Column(unique = true)
    @Nonnull
    private String name;
    public Skill(){}
    public Skill(String name) {this.name = name;}
}
