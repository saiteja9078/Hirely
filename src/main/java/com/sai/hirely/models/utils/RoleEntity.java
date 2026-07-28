package com.sai.hirely.models.utils;

import com.sai.hirely.models.job.JobPosting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Table(
        name = "roles"
        ,uniqueConstraints = @UniqueConstraint(
                name = "unique_role",
                columnNames = "name"
)
)
@Entity()
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_seq")
    @SequenceGenerator(name = "role_seq",allocationSize = 50)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role")
    private List<JobPosting> jobPostings;
}
