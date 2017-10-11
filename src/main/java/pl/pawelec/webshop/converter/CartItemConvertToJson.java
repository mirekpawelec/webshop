/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import pl.pawelec.webshop.model.CartItem;

/**
 *
 * @author mirek
 */
public class CartItemConvertToJson extends StdConverter<Set<CartItem>, Set<Map<String, Object>>>{

    @Override
    public Set<Map<String, Object>> convert(Set<CartItem> in) {
        return in.stream().map( new Function<CartItem, Map<String, Object>>(){
            @Override
            public Map<String, Object> apply(CartItem ci) {
                Map<String, Object> cartItemMap = new TreeMap<String, Object>();
                cartItemMap.put("manufacturer", ci.getProduct().getManufacturer());
                cartItemMap.put("productId", ci.getProduct().getProductId());
                cartItemMap.put("productNo", ci.getProduct().getProductNo());
                cartItemMap.put("productName", ci.getProduct().getName());
                cartItemMap.put("quantity", ci.getQuantity());
                cartItemMap.put("totalPrice", ci.getTotalPrice());
                return cartItemMap;
            }
        }).collect(Collectors.toSet());
    }
}
