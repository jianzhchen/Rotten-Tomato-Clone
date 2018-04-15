package com.redwood.rottenpotato.repositories;

import com.redwood.rottenpotato.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Item, Long>
{
    List<Item> findByName(String name);
}
