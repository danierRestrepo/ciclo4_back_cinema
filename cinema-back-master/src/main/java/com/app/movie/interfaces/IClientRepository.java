/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.movie.interfaces;

import com.app.movie.entities.Client;
import com.app.movie.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IClientRepository extends MongoRepository<Client, String> {
    @Query(value= "{name : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Client> getClientsByName(String name);
}
