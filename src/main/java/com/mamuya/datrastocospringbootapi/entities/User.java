package com.mamuya.datrastocospringbootapi.entities;

import com.mamuya.datrastocospringbootapi.utility.DateTimeUtility;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "created_at", nullable = false)
    private String createdAt = DateTimeUtility.getCurrentDateTime();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = DateTimeUtility.getCurrentDateTime();
}
