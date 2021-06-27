package br.com.supera.game.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    
    @Id
    @GeneratedValue
    public long id;
    
    private BigDecimal price;
    
    @OneToMany
    @Valid
    List<ProductToCart> products = new ArrayList<ProductToCart>();
    
    private int shipping;
    
    private BigDecimal totalPrice;
    
    
    public BigDecimal getTotalPrice(){
            totalPrice = new BigDecimal(0);
            totalPrice = totalPrice.add(getPrice().add(new BigDecimal(getShipping())));
        return totalPrice;
    }
    
    public BigDecimal getPrice(){
        price = new BigDecimal(0);
        for(ProductToCart p : products){
            price = price.add(new BigDecimal(p.product.price.doubleValue() * p.quantity));
        }
        return price;
    }
    
    private int getShipping(){
        shipping = 0;
        if(getPrice().doubleValue() > 250){
            return shipping;
        }
        for(ProductToCart p : products){
            shipping += 10;
        }
        return shipping;
    }
}
