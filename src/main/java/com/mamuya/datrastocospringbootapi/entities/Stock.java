package com.mamuya.datrastocospringbootapi.entities;

import com.mamuya.datrastocospringbootapi.utility.DateTimeUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "stock_quantity")
    private double quantity = 0.0;

    @Column(name = "stock_buying_price")
    private double buyingPrice = 0.0;

    @Column(name = "stock_selling_price")
    private double sellingPrice = 0.0;

    @Column(name = "created_at", nullable = false)
    private String createdAt = DateTimeUtility.getCurrentDateTime();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = DateTimeUtility.getCurrentDateTime();

}
