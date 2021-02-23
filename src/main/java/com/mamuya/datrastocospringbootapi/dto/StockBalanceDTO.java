package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.StockBalance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockBalanceDTO implements DTOValidation<StockBalance> {

    private int id = -1;
    private EntrepriseDTO entrepriseDTO;
    private double stockBalanceAmount = -1;

    public StockBalanceDTO(StockBalance stockBalance) {
        setId(stockBalance.getId());
        setEntrepriseDTO(new EntrepriseDTO(stockBalance.getEntreprise()));
        setStockBalanceAmount(stockBalance.getStockBalanceAmount());
    }

    @Override
    public boolean hasAllValidMappings() {
        return (hasValid(entrepriseDTO) && hasValid(stockBalanceAmount));
    }

    @Override
    public boolean hasAnyValidMappings() {
        return (hasValid(entrepriseDTO) || hasValid(stockBalanceAmount));
    }

    @Override
    public StockBalance createEntity() {

        StockBalance stockBalance = new StockBalance();

        if(hasValid(id)){
            stockBalance.setId(id);
        }

        stockBalance.setEntreprise(entrepriseDTO.createEntity());
        stockBalance.setStockBalanceAmount(stockBalanceAmount);
        return stockBalance;
    }

    @Override
    public StockBalance updateEntity(StockBalance stockBalance) {

        if(hasValid(entrepriseDTO)){
            stockBalance.setEntreprise(entrepriseDTO.createEntity());
        }

        if(hasValid(stockBalanceAmount)){
            stockBalance.setStockBalanceAmount(stockBalanceAmount);
        }

        return stockBalance;
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
