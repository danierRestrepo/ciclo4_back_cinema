/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.movie.service;

import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Client;
import com.app.movie.entities.Movie;
import com.app.movie.entities.Score;
import com.app.movie.repository.MovieRepository;
import com.app.movie.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScoreService {
    private final String SCORE_REGISTERED="La calificacion ya se encuentra registrada";
    private final String SCORE_SUCCESS="La calificación se registró correctamente";
    @Autowired
    ScoreRepository repository;

    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Score request) {
        ResponseDto response = new ResponseDto();
        List<Score> scoreClientAndMovie = repository.getByMovieAndClient(request.getMovie().getName(),request.getClient().getEmail());
        if(request.getScore().intValue()<1 || request.getScore().intValue()>5){
            response.status=false;
            response.message="La calificación enviada no está dentro de los valores esperados";
        } else if (scoreClientAndMovie.size()>0) {
            response.status=false;
            response.message=SCORE_REGISTERED;
        } else{
            repository.save(request);
            response.status=true;
            response.message=SCORE_SUCCESS;
            response.id= request.getId();
        }
        return response;
    }

    public Score update(Score score) {
        Score scoreToUpdate = new Score();

        Optional<Score> currentScore = repository.findById(score.getId());
        if (!currentScore.isEmpty()) {
            scoreToUpdate = score;
            scoreToUpdate=repository.save(scoreToUpdate);
        }
        return scoreToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
