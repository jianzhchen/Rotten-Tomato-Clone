package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.CriticReview;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.repositories.ActorRepository;
import com.redwood.rottenpotato.main.repositories.CriticReviewRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ActorMvcController {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tvRepository;
    @Autowired
    private CriticReviewRepository criticReviewRepository;


    @RequestMapping("/c/{actorKey}")
    public String actorPage(@PathVariable("actorKey") String actorKey, Model model)
    {
        Actor actor = actorRepository.findByActorKey(actorKey);
        if (actor == null)
        {
            model.addAttribute("error", "actor not found");
        }
        else
        {
            model.addAttribute("actorKey", actor.getActorKey());
            model.addAttribute("name", actor.getActorName());
            String birthplace = actor.getBirthplace();
            model.addAttribute("birthplace", birthplace);
            String birthday = actor.getBirthday();
            model.addAttribute("birthday", birthday);
            String info = actor.getActorInfo();
            model.addAttribute("info", info);

            //filmography
            //1. get movies contains actor as a part of cast
            List<Movie> movies = this.movieRepository.searchByActorKey(actor.getActorKey());
            List<TV> tv = this.tvRepository.searchByActorKey(actor.getActorKey());

            //2. create hashmaps of movies, and put all hashmaps into a list
            List<HashMap> filmography = new ArrayList<>();
            for (Movie mv : movies)
            {
                //one review
                HashMap<String, String> temp = new HashMap<>();
                temp.put("key", mv.getMovieKey());
                temp.put("name", mv.getName());
                temp.put("url","/m/"+mv.getMovieKey());
                temp.put("img","movieImages/"+mv.getMovieKey());
                filmography.add(temp);
            }
            for (TV t : tv)
            {
                //one review
                HashMap<String, String> temp = new HashMap<>();
                temp.put("key", t.getTVKey());
                temp.put("name", t.getTVName());
                temp.put("url","/t/"+t.getTVKey());
                temp.put("img","tvImages/"+t.getTVKey());

                filmography.add(temp);
            }

            model.addAttribute("filmography", filmography);
        }

        return "actorPage.html";
    }
}
