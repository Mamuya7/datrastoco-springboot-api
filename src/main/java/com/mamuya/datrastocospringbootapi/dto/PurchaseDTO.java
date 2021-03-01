package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseDTO implements DTOValidation<Purchase> {

    private int id = -1;
    private StockDTO stock;
    private double quantity = -1;
    private double amount = -1;
    private String payment;
    private boolean quantityChanged = false;

    public PurchaseDTO(Purchase purchase) {
        setId(purchase.getId());
        setStock(new StockDTO(purchase.getStock()));
        setQuantity(purchase.getQuantity());
        setAmount(purchase.getAmount());
        setPayment(purchase.getPayment());
    }

    @Override
    public boolean hasAllValidMappings() {

        return (
                hasValid(stock)
                    && hasValid(quantity)
                    && hasValid(amount)
                    && hasValid(payment)
        );
    }

    @Override
    public boolean hasAnyValidMappings() {
        return (
                hasValid(stock)
                    || hasValid(quantity)
                    || hasValid(amount)
                    || hasValid(payment)
        );
    }

    @Override
    public Purchase createEntity() {
        Purchase purchase = new Purchase();

        if(hasValid(id)){
            purchase.setId(id);
        }

        purchase.setStock(stock.createEntity());
        purchase.setQuantity(quantity);
        purchase.setAmount(amount);
        purchase.setPayment(payment);
        return purchase;
    }

    @Override
    public Purchase updateEntity(Purchase purchase) {

        if (hasValid(stock)){
            purchase.setStock(stock.createEntity());
        }

        if(hasValid(quantity)){
            double q1 = purchase.getStock().getQuantity();

            purchase.getStock().setQuantity(q1 - purchase.getQuantity() + quantity);

            purchase.setQuantity(quantity);

            setQuantityChanged(true);
        }

        if (hasValid(amount)){
            purchase.setAmount(amount);
        }

        if (hasValid(payment)){
            purchase.setPayment(payment);
        }
        return purchase;
    }

    @Override
    public boolean hasValid(String item) {

        return ((item != null) && (item.trim().length() > 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item >= 1;
    }

    private boolean hasValid(double quantity) {
        return quantity >= 0;
    }

    private boolean hasValid(StockDTO stockDTO) {
        return stockDTO != null;
    }
}
