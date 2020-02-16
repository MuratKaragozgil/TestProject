package com.shopping.cart.controller;

import com.shopping.cart.model.*;
import com.shopping.cart.model.dto.DeliveryCostDTO;
import com.shopping.cart.repository.*;
import com.shopping.cart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Murat Karagözgil
 */

@RequestMapping("/initDefaultData")
@RestController
public class DataInitController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping(value = "/onlyParamData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Boolean> initOnlyParamData() {
        saveOnlyParamData();
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping(value = "/initFullData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Boolean> initFullData() {
        saveOnlyParamData();
        saveCartData();
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @Transactional
    public void saveOnlyParamData() {
        Category category1 = Category.builder().title("Bilgisayar").build();
        category1 = categoryRepository.save(category1);

        Category category2 = Category.builder().title("Telefon").build();
        category2 = categoryRepository.save(category2);

        Category category3 = Category.builder().title("Kitap").build();
        category3 = categoryRepository.save(category3);

        Category category4 = Category.builder().title("Araba").build();
        category4 = categoryRepository.save(category4);


        Product product1 = Product.builder().category(category1).title("Lenovo v130").price(new BigDecimal("1500")).build();
        productRepository.save(product1);

        Product product2 = Product.builder().category(category1).title("Acer A315").price(new BigDecimal("1900")).build();
        productRepository.save(product2);

        Product product3 = Product.builder().category(category1).title("Asus X540").price(new BigDecimal("2400")).build();
        productRepository.save(product3);

        Product product4 = Product.builder().category(category1).title("HP 250").price(new BigDecimal("4000")).build();
        productRepository.save(product4);

        Product product5 = Product.builder().category(category1).title("Lenovo IP330").price(new BigDecimal("2500")).build();
        productRepository.save(product5);

        Product product6 = Product.builder().category(category1).title("Apple Macbook Air").price(new BigDecimal("6000")).build();
        productRepository.save(product6);

        Product product7 = Product.builder().category(category2).title("Xiaomi Redmi Note 8").price(new BigDecimal("1820")).build();
        productRepository.save(product7);

        Product product8 = Product.builder().category(category2).title("Huawei Y6").price(new BigDecimal("1100")).build();
        productRepository.save(product8);

        Product product9 = Product.builder().category(category2).title("Samsung Galaxy A2").price(new BigDecimal("700")).build();
        productRepository.save(product9);

        Product product10 = Product.builder().category(category2).title("iPhone 11").price(new BigDecimal("7000")).build();
        productRepository.save(product10);

        Product product11 = Product.builder().category(category2).title("Samsung Note 10").price(new BigDecimal("10000")).build();
        productRepository.save(product11);

        Product product12 = Product.builder().category(category2).title("Huawei P20 Lite").price(new BigDecimal("1500")).build();
        productRepository.save(product12);

        Product product13 = Product.builder().category(category3).title("Karamazov Kardeşler").price(new BigDecimal("23")).build();
        productRepository.save(product13);

        Product product14 = Product.builder().category(category3).title("Satranç").price(new BigDecimal("7")).build();
        productRepository.save(product14);

        Product product15 = Product.builder().category(category3).title("Suç ve Ceza").price(new BigDecimal("15")).build();
        productRepository.save(product15);

        Product product16 = Product.builder().category(category3).title("Kumarbaz").price(new BigDecimal("10")).build();
        productRepository.save(product16);

        Product product17 = Product.builder().category(category3).title("İlyada").price(new BigDecimal("25")).build();
        productRepository.save(product17);

        Product product18 = Product.builder().category(category3).title("Aforizmalar").price(new BigDecimal("8")).build();
        productRepository.save(product18);

        Product product19 = Product.builder().category(category4).title("Mercedes C-180").price(new BigDecimal("194000")).build();
        productRepository.save(product19);

        Product product20 = Product.builder().category(category4).title("Mini Cooper").price(new BigDecimal("14000")).build();
        productRepository.save(product20);

        Product product21 = Product.builder().category(category4).title("Audi Q7").price(new BigDecimal("510000")).build();
        productRepository.save(product21);

        Product product22 = Product.builder().category(category4).title("Opel Astra").price(new BigDecimal("56000")).build();
        productRepository.save(product22);

        Product product23 = Product.builder().category(category4).title("Volvo S80").price(new BigDecimal("14000")).build();
        productRepository.save(product23);

        Product product24 = Product.builder().category(category4).title("BMW 5.20").price(new BigDecimal("190000")).build();
        productRepository.save(product24);


        Campaign campaign1 = Campaign.builder().title("%20 İndirim").category(category1).discountAmount(new BigDecimal("20"))
                .itemLimit(3).discountType(DiscountType.RATE).build();
        campaignRepository.save(campaign1);

        Campaign campaign2 = Campaign.builder().title("%50 İndirim").category(category2).discountAmount(new BigDecimal("50"))
                .itemLimit(5).discountType(DiscountType.RATE).build();
        campaignRepository.save(campaign2);

        Campaign campaign3 = Campaign.builder().title("5 TL İndirim").category(category3).discountAmount(new BigDecimal("5"))
                .itemLimit(5).discountType(DiscountType.AMOUNT).build();
        campaignRepository.save(campaign3);


        Coupon coupon1 = Coupon.builder().title("%10 Kupon İndirim").discountAmount(new BigDecimal("10")).minPurchaseAmount(new BigDecimal(100))
                .discountType(DiscountType.RATE).build();
        couponRepository.save(coupon1);

        Coupon coupon2 = Coupon.builder().title("50 TL Kupon İndirim").discountAmount(new BigDecimal("50")).minPurchaseAmount(new BigDecimal(20))
                .discountType(DiscountType.AMOUNT).build();
        couponRepository.save(coupon2);
    }

    @Transactional
    public void saveCartData() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        Long id = shoppingCart.getId();

        shoppingCartService.addItem(id, 5L);
        shoppingCartService.addItem(id, 6L);
        shoppingCartService.addItem(id, 6L);
        shoppingCartService.addItem(id, 6L);
        shoppingCartService.addItem(id, 10L);
        shoppingCartService.addItem(id, 11L);
        shoppingCartService.addItem(id, 14L);
        shoppingCartService.addItem(id, 16L);
        shoppingCartService.addItem(id, 17L);
        shoppingCartService.addItem(id, 18L);
        shoppingCartService.addItem(id, 19L);
        shoppingCartService.addItem(id, 21L);
        shoppingCartService.addItem(id, 23L);

        shoppingCartService.applyCampaign(id, 30L);
        shoppingCartService.applyCoupon(id, 32L);


        DeliveryCostDTO deliveryCostDTO = new DeliveryCostDTO(new BigDecimal("2"), new BigDecimal("2"));
        shoppingCartService.applyDelivery(id, deliveryCostDTO);
    }

}

