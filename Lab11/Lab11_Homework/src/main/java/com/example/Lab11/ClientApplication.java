package com.example.Lab11;

import com.example.Lab11.Model.Author;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {

            //Get all authors
            Author[] authors = restTemplate.getForObject("http://localhost:8081/authors", Author[].class);
            if (authors != null) {
                Arrays.stream(authors).forEach(System.out::println);
            }

            System.out.println("--------------------------------------------------");

            // Get an author by id
            Author author = restTemplate.getForObject("http://localhost:8081/authors/1", Author.class);
            System.out.println(author);

            System.out.println("--------------------------------------------------");


            // Create a new author
            Author newAuthor = new Author(2, "Ioana");
            Author createdAuthor = restTemplate.postForObject("http://localhost:8081/authors", newAuthor, Author.class);
            System.out.println(createdAuthor);

            System.out.println("--------------------------------------------------");
            

            // Update an author
            Author updatedAuthor = new Author(2, "Iustin");
            restTemplate.put("http://localhost:8081/authors/2", updatedAuthor);

            System.out.println("--------------------------------------------------");

            // Delete an author
            restTemplate.delete("http://localhost:8081/authors/2");

/*
             */
        };
    }
}