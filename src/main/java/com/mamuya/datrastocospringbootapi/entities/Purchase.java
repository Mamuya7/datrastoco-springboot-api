package com.mamuya.datrastocospringbootapi.entities;

import com.mamuya.datrastocospringbootapi.utility.DateTimeUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Purchase {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "purchase_quantity", nullable = false)
    private double quantity = 0.0;

    @Column(name = "purchase_amount", nullable = false)
    private double amount = 0.0;

    @Column(name = "purchase_payment", nullable = false)
    private String payment;

    @Column(name = "created_at", nullable = false)
    private String createdAt = DateTimeUtility.getCurrentDateTime();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = DateTimeUtility.getCurrentDateTime();

}
