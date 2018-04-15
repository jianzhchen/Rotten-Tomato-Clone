package com.redwood.rottenpotato;

import com.redwood.rottenpotato.models.Item;
import com.redwood.rottenpotato.repositories.MovieRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RottenpotatoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(RottenpotatoApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(RottenpotatoApplication.class);

    /*@Bean
    public CommandLineRunner demo( MovieRepository repository)
    {
        return (args) ->
        {
            // save a couple of customers
            Set<String> videos1 = new HashSet<String>();
            videos1.add("video1-1");
            videos1.add("video1-2");
            Set<String> photos1 = new HashSet<String>();
            photos1.add("photo1-1");
            photos1.add("photo1-2");
            //repository.save(new Item("Item1", videos1, photos1));

            Set<String> videos2 = new HashSet<String>();
            videos2.add("video2-1");
            videos2.add("video2-2");
            Set<String> photos2 = new HashSet<String>();
            photos2.add("photo2-1");
            photos2.add("photo2-2");
            //repository.save(new Item("Item2", videos2, photos2));

            // fetch all customers
            log.info("*****************************************************************************");
            log.info("Items found with findAll():");
            log.info("-------------------------------");
            for(Item item : repository.findAll())
            {
                log.info(item.toString());
            }
            log.info("");



            // fetch customers by name
            log.info("Item found with findByName('Item1'):");
            log.info("--------------------------------------------");
            //log.info(repository.findByName("Item1").toString());
            repository.findByName("Item1").forEach(item ->
            {
                log.info(item.toString());
            });
            log.info("");
        };
    }*/

}
