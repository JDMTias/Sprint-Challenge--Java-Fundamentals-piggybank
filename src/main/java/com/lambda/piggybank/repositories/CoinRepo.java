package com.lambda.piggybank.repositories;

import com.lambda.piggybank.models.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepo extends CrudRepository <Coin, Long> {

}
