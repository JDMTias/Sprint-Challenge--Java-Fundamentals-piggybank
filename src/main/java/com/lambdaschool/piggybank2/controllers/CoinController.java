package com.lambdaschool.piggybank2.controllers;

import com.lambdaschool.piggybank2.models.Coin;
import com.lambdaschool.piggybank2.repos.CoinRepo;
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
        if(coin.getQuantity() > 1) {
            return coin.getNameplural();
        }else{
            return coin.getName();
        }
    }

    private double getTotal() {
        double total = 0;
        for (Coin coin : coinList){
            total += coin.getQuantity() * coin.getValue();
        }
        return total
    }

    @Autowired
    CoinRepo coinRepo;

    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> listAllCoins() {
        coinList.clear();
        coinRepo.findAll().iterator().forEachRemaining(coinList::add);
        for (Coin coin : coinList) {
            System.out.println(coin.getQuantity() + " " + checkPlural(coin));
        }
        System.out.println("\n PiggyBank holds $" + (getTotal()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


