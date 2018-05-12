package com.redwood.rottenpotato.main.services;


import com.redwood.rottenpotato.main.models.Critic;
import com.redwood.rottenpotato.main.models.CriticReview;
import com.redwood.rottenpotato.main.repositories.CriticReviewRepository;
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

    public List<Map> getHighestRatingMovies(Model model){
        List<Map> templist = new ArrayList<>();

        List<CriticReview> crList = CriticReviewRepository.findAll();

        return templist;
    }
}
