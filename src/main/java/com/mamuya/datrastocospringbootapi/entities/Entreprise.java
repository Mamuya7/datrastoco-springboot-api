package com.mamuya.datrastocospringbootapi.entities;

import com.mamuya.datrastocospringbootapi.utility.DateTimeUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "entreprises")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Entreprise {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "entreprise_name", nullable = false, unique = true)
    private String name;

    @Column(name = "entreprise_code", nullable = false, unique = true)
    private String code;

    @Column(name = "entreprise_location", nullable = false)
    private String location;

    @Column(name = "created_at", nullable = false)
    private String createdAt = DateTimeUtility.getCurrentDateTime();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = DateTimeUtility.getCurrentDateTime();
}
