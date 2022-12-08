/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.movie.interfaces;

import com.app.movie.entities.Movie;
import com.app.movie.entities.Score;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IScoreRepository extends MongoRepository<Score, String> {
    @Query(value= "{movies.name : ?0, clients.email : ?1}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Score> getScoresByMoviesAndClient(String name, String email);// puede ser mas preciso con id

    //@Query(value= "{clients.name : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
   // List<Score> getScoreByClients(String name);
}
