package com.lambda.piggybank.controllers;

import com.lambda.piggybank.models.Coin;
import com.lambda.piggybank.repositories.CoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CoinController {

    public List<Coin> coinList = new ArrayList<>();
    private String checkPlural(Coin coin) {
        if (coin.getQuantity() > 1) {
            return coin.getNameplural();
        } else return coin.getName();
    }

    private float getTotal() {
        float total = 0;
        for (Coin coin : coinList) {
            total += coin.getQuantity() * coin.getValue();
        }
        return total;
    }
    @Autowired
    CoinRepo coinrepo;

    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> listAllCoins() {
        coinList.clear();
        coinrepo.findAll().iterator().forEachRemaining(coinList::add);
        for (Coin coin : coinList) {
            System.out.println(coin.getQuantity() + " " + checkPlural(coin));
        }
        System.out.print("The piggy bank holds $" + (getTotal()));
        return new ResponseEntity<>(HttpStatus.OK);

//    1 Quarter
//    1 Dime
//    5 Dollars
//    3 Nickels
//    7 Dimes
//    1 Dollar
//    10 Pennies
//    The piggy bank holds 7.3
    }


}
