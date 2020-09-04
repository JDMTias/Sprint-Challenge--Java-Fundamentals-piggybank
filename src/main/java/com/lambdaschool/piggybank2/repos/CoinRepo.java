package com.lambdaschool.piggybank2.repos;

import com.lambdaschool.piggybank2.models.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepo extends CrudRepository<Coin, Long> {


}
