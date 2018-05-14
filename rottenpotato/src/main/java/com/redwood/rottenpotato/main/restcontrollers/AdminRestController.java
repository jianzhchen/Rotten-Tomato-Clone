package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.repositories.CriticApplicationRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/1/admin")
public class AdminRestController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tVRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private CriticApplicationRepository criticApplicationRepository;

    @PostMapping("add")
    public String add(@RequestParam("isMovie") boolean isMovie, @RequestParam("name") String name, @RequestParam("info") String info, @RequestParam("boxoffice") long boxOffice) {
        if (isMovie) {
            Movie movie = new Movie();
            movie.setName(name);
            movie.setInfo(info);
            movie.setBoxOffice(boxOffice);
            movie.setMovieKey(UUID.randomUUID().toString().replace("-", ""));
            movieRepository.save(movie);
            return jsonService.constructStatusMessage(AjaxCallStatus.OK);
        } else {
            TV tv = new TV();
            tv.setTVName(name);
            tv.setTVInfo(info);
            tv.setTVKey(UUID.randomUUID().toString().replace("-", ""));
            tVRepository.save(tv);
            return jsonService.constructStatusMessage(AjaxCallStatus.OK);
        }
    }
    @Transactional
    @PostMapping("delete")
    public String delete(@RequestParam("key") String key) {
        movieRepository.removeByMovieKey(key);
        tVRepository.removeByTVKey(key);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
    @Transactional
    @PostMapping("rejectCriticApp")
    public String rejectCriticApp(@RequestParam("userId") long userId) {
        criticApplicationRepository.removeByUserId(userId);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

}
