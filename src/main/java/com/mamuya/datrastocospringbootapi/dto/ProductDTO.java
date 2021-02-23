package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO implements DTOValidation<Product>{

    private int id = -1;
    private String prdname;
    private String prdcompany;
    private String prdsize;
    private String prdtype;

    public ProductDTO(Product product) {
        setId(product.getId());
        setPrdname(product.getName());
        setPrdcompany(product.getCompany());
        setPrdsize(product.getSize());
        setPrdtype(product.getType());
    }

    @Override
    public boolean hasAllValidMappings() {

        return (
                hasValid(prdname)
                && hasValid(prdcompany)
                && hasValid(prdsize)
                );
    }

    @Override
    public boolean hasAnyValidMappings() {

        return (
                hasValid(prdname)
                || hasValid(prdsize)
                || hasValid(prdcompany)
                || hasValid(prdtype)
                );
    }

    @Override
    public Product createEntity() {
        Product product = new Product();

        if(hasValid(id)){
            product.setId(id);
        }

        product.setName(prdname);
        product.setCompany(prdcompany);
        product.setSize(prdsize);
        product.setType(prdtype);

        return product;
    }

    @Override
    public Product updateEntity(Product entity) {

        if(hasValid(prdname)){
            entity.setName(prdname);
        }

        if(hasValid(prdcompany)){
            entity.setCompany(prdcompany);
        }

        if(hasValid(prdsize)){
            entity.setSize(prdsize);
        }

        if(hasValid(prdtype)){
            entity.setType(prdtype);
        }

        return entity;
    }

    @Override
    public boolean hasValid(String item) {

        return ((item != null) && (item.trim().length() > 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item >= 1;
    }
}
