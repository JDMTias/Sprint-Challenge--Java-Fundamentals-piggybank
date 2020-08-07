package com.lambda.piggybank.controllers;

import com.lambda.piggybank.models.Coin;
import com.lambda.piggybank.repositories.CoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
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

    private double getTotal() {
        double total = 0;
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
        DecimalFormat df = new DecimalFormat("###.###");
        System.out.print("The piggy bank holds $" + (df.format(getTotal())));
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
