package com.redwood.rottenpotato.main.services;


import com.redwood.rottenpotato.main.models.Critic;
import com.redwood.rottenpotato.main.models.CriticReview;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.CriticReviewRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CriticReviewService {
    @Autowired
    private CriticReviewRepository CriticReviewRepository;
    @Autowired
    private MovieRepository MovieRepository;

    public List<Map> getHighestRatingMovies(Model model, int page){
        List<Map> templist = new ArrayList<>();
        List<Object[]> crList = CriticReviewRepository.findTopByAvgScore(8, PageRequest.of(0,10));
        Object[] objTemp;
        Movie tempMovie;
        for(int i = 0; i <= 9; i++){
            Map<String, String> map = new HashMap<>();

            objTemp = crList.get(i);
            tempMovie = MovieRepository.findByMovieKey( objTemp[0].toString());

            if (tempMovie == null){
                continue;
            }

            map.put("movieName", tempMovie.getName());
            map.put("movieKey", tempMovie.getMovieKey());
            map.put("rate",  objTemp[1].toString());
            templist.add(map);
        }
        return templist;
    }
}
