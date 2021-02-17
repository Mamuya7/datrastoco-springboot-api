package com.mamuya.datrastocospringbootapi.entities;

import com.mamuya.datrastocospringbootapi.utility.DateTimeUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name", nullable = false)
    private String name;

    @Column(name = "role_code", nullable = false)
    private String code;

    @Column(name = "role_type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Permission> permissions;

    @Column(name = "created_at", nullable = false)
    private String createdAt = DateTimeUtility.getCurrentDateTime();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = DateTimeUtility.getCurrentDateTime();

}
