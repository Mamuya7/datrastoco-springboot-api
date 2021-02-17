package com.mamuya.datrastocospringbootapi.entities;

import com.mamuya.datrastocospringbootapi.utility.DateTimeUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Permission {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "permission_entity", nullable = false)
    private String entity;

    @Column(name = "permission_type", nullable = false)
    private String type;

    @Column(name = "created_at", nullable = false)
    private String createdAt = DateTimeUtility.getCurrentDateTime();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = DateTimeUtility.getCurrentDateTime();


}
