package com.redwood.rottenpotato.main.formControllers;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.repositories.CriticApplicationRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/1/admin")
public class adminFormController {

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
    public String add(@RequestParam("isMovie") boolean isMovie, @RequestParam("name") String name, @RequestParam("info") String info, @RequestParam("boxoffice") long boxOffice, @RequestParam("cast") String cast) {
        if (isMovie) {
            Movie movie = new Movie();
            movie.setName(name);
            movie.setInfo(info);
            movie.setBoxOffice(boxOffice);
            movie.setMovieKey(name + "_" + UUID.randomUUID().toString().replace("-", ""));
            movie.setCast(cast);
            movieRepository.save(movie);
            return jsonService.constructStatusMessage(AjaxCallStatus.OK);
        } else {
            TV tv = new TV();
            tv.setTVName(name);
            tv.setTVInfo(info);
            tv.setTVKey(name + "_" + UUID.randomUUID().toString().replace("-", ""));
            tv.setTVCast(cast);
            tVRepository.save(tv);
            return jsonService.constructStatusMessage(AjaxCallStatus.OK);
        }
    }
}
