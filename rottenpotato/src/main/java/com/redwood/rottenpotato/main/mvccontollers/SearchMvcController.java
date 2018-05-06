package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.NotInterested;
import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.repositories.ActorRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.NotInterestedRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import com.redwood.rottenpotato.main.services.MovieService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class SearchMvcController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tVRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotInterestedRepository notInterestedRepository;

    @GetMapping(value = "searchResult")
    public String search(@RequestParam("term") String searchTerm,
                         @RequestParam("type") String type, @RequestParam("page") int page, Model model, Principal principal) {
        List<String> filter = new ArrayList<>();
        if (principal != null) {
            User user = userRepository.findByEmail(principal.getName());
            List<NotInterested> notInteresteds = notInterestedRepository.findByUserId(user.getId());
            for (NotInterested notInterested : notInteresteds) {
                filter.add(notInterested.getItemKey());
            }
        }

        List<HashMap> result = new ArrayList<>();
        boolean hasNext = true;
        if (type.equals("movie")) {
            List<Movie> resultList = movieRepository.searchByName(searchTerm, PageRequest.of(page, 10));
            for (Movie movie : resultList) {
                if (filter.contains(movie.getMovieKey())) {
                    continue;
                }
                HashMap<String, String> movieDetail = new HashMap<>();
                movieDetail.put("name", movie.getName());
                movieDetail.put("info", movie.getInfo());
                movieDetail.put("key", movie.getMovieKey());
////            movieDetail.put("casts", movie.getCast());
//            movieDetail.put("casts", castTransfer(movie.getCast()));
                result.add(movieDetail);
            }
            if (movieRepository.searchByName(searchTerm, PageRequest.of(page + 1, 10)).size() <= 0) {
                hasNext = false;
            }

        } else if (type.equals("tv")) {
            List<TV> resultList2 = tVRepository.searchByName(searchTerm, PageRequest.of(page, 10));
            for (TV tv : resultList2) {
                if (filter.contains(tv.getTVKey())) {
                    continue;
                }
                HashMap<String, String> tvDetail = new HashMap<>();
                tvDetail.put("name", tv.getTVName());
                tvDetail.put("info", tv.getTVInfo());
                tvDetail.put("key", tv.getTVKey());
                result.add(tvDetail);
            }
            if (tVRepository.searchByName(searchTerm, PageRequest.of(page + 1, 10)).size() <= 0) {
                hasNext = false;
            }
        } else {
            List<Actor> resultList3 = actorRepository.searchByName(searchTerm, PageRequest.of(page, 10));
            for (Actor actor : resultList3) {
                HashMap<String, String> actorDetail = new HashMap<>();
                actorDetail.put("name", actor.getActorName());
                actorDetail.put("info", actor.getActorInfo());
                actorDetail.put("key", actor.getActorKey());
                result.add(actorDetail);
            }
            if (actorRepository.searchByName(searchTerm, PageRequest.of(page + 1, 10)).size() <= 0) {
                hasNext = false;
            }
        }
        model.addAttribute("result", result);
        model.addAttribute("hasNext", hasNext);
        return "searchResult.html";
    }

    public String castTransfer(String casts) {
        String castsStr = "";
        String[] castsArr = casts.split(",");

        for (int i = 0; i <= castsArr.length - 1 && i < 10; i++) {
            castsStr += castsArr[i].replace("_", " ") + ", ";
        }
        if (castsStr == "") {
            castsStr = "No Available";
        } else {
            castsStr = castsStr.substring(0, castsStr.length() - 2);
            castsStr += ".";
        }

        return castsStr;
    }


}
