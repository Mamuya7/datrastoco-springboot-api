package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockDTO implements DTOValidation<Stock> {

    private int id = -1;
    private EntrepriseDTO entreprise;
    private ProductDTO product;
    private double stckQnty = -1;
    private double stckByprice = -1;
    private double stckSllprice = -1;

    public StockDTO(Stock stock) {
        setId(stock.getId());
        setEntreprise(new EntrepriseDTO(stock.getEntreprise()));
        setProduct(new ProductDTO(stock.getProduct()));
        setStckQnty(stock.getQuantity());
        setStckByprice(stock.getBuyingPrice());
        setStckSllprice(stock.getSellingPrice());
    }

    @Override
    public boolean hasAllValidMappings() {

        return (
                hasValid(entreprise)
                    && hasValid(product)
                    && hasValid(stckQnty)
                    && hasValid(stckByprice)
                    && hasValid(stckSllprice)
        );
    }

    @Override
    public boolean hasAnyValidMappings() {

        return (
                hasValid(entreprise)
                    || hasValid(product)
                    || hasValid(stckQnty)
                    || hasValid(stckByprice)
                    || hasValid(stckSllprice)
        );
    }

    @Override
    public Stock createEntity() {

        Stock stock = new Stock();

        if(hasValid(id)){
            stock.setId(id);
        }

        stock.setEntreprise(entreprise.createEntity());
        stock.setProduct(product.createEntity());
        stock.setQuantity(stckQnty);
        stock.setBuyingPrice(stckByprice);
        stock.setSellingPrice(stckSllprice);
        return stock;
    }

    @Override
    public Stock updateEntity(Stock stock) {

        if(entreprise != null){
            stock.setEntreprise(entreprise.createEntity());
        }

        if(product != null){
            stock.setProduct(product.createEntity());
        }

        if(hasValid(stckQnty)){
            stock.setQuantity(stckQnty);
        }

        if(hasValid(stckByprice)){
            stock.setBuyingPrice(stckByprice);
        }

        if(hasValid(stckSllprice)){
            stock.setSellingPrice(stckSllprice);
        }

        return stock;
    }

    @Override
    public boolean hasValid(String item) {

        return ((item != null) && (item.trim().length() > 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item >= 1;
    }

    public boolean hasValid(Double item) {

        return item >= 0;
    }

    public boolean hasValid(Object item) {

        return item != null;
    }
}
