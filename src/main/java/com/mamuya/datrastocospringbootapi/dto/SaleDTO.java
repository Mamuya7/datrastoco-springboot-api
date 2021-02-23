package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaleDTO implements DTOValidation<Sale> {

    private int id = -1;
    private StockDTO stockDTO;
    private double quantity = -1;
    private double amount = -1;
    private String payment;

    public SaleDTO(Sale sale) {
        setId(sale.getId());
        setStockDTO(new StockDTO(sale.getStock()));
        setQuantity(sale.getQuantity());
        setAmount(sale.getAmount());
        setPayment(sale.getPayment());
    }

    @Override
    public boolean hasAllValidMappings() {

        return (
                hasValid(stockDTO)
                        && hasValid(quantity)
                        && hasValid(amount)
                        && hasValid(payment)
        );
    }

    @Override
    public boolean hasAnyValidMappings() {
        return (
                hasValid(stockDTO)
                        || hasValid(quantity)
                        || hasValid(amount)
                        || hasValid(payment)
        );
    }

    @Override
    public Sale createEntity() {

        Sale sale = new Sale ();

        if(hasValid(id)){
            sale.setId(id);
        }

        sale.setStock(stockDTO.createEntity());
        sale.setQuantity(quantity);
        sale.setAmount(amount);
        sale.setPayment(payment);
        return sale;
    }

    @Override
    public Sale updateEntity(Sale sale) {


        if (hasValid(stockDTO)){
            sale.setStock(stockDTO.createEntity());
        }

        if(hasValid(quantity)){
            sale.setQuantity(quantity);
        }

        if (hasValid(amount)){
            sale.setAmount(amount);
        }

        if (hasValid(payment)){
            sale.setPayment(payment);
        }
        return sale;
    }

    @Override
    public boolean hasValid(String item) {

        return ((item != null) && (item.trim().length() > 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item >= 1;
    }

    private boolean hasValid(double item) {
        return item >= 0;
    }

    private boolean hasValid(Object item) {
        return item != null;
    }
}
