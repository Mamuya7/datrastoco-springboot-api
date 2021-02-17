package com.mamuya.datrastocospringbootapi.entities;

import com.mamuya.datrastocospringbootapi.utility.DateTimeUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_company", nullable = false)
    private String company;

    @Column(name = "product_size", nullable = false)
    private String size;

    @Column(name = "product_type")
    private String type;

    @Column(name = "created_at", nullable = false)
    private String createdAt = DateTimeUtility.getCurrentDateTime();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = DateTimeUtility.getCurrentDateTime();

}
