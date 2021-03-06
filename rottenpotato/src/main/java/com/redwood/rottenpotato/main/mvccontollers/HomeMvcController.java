package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.CriticReview;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.services.CriticReviewService;
import com.redwood.rottenpotato.main.services.MovieService;
import com.redwood.rottenpotato.main.services.PrincipleService;
import com.redwood.rottenpotato.main.services.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeMvcController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private TVService TVService;
    @Autowired
    private CriticReviewService CriticReviewService;
    @Autowired
    private MovieRepository movieRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PrincipleService principleService;

    @GetMapping(value = {"", "/", "index.html"})
    public String greeting(Model model, Principal principal) {
        principleService.principalModel(model, principal);
        model.addAttribute("topBoxOffice", movieService.top10BoxWithPage(model, 0));
        model.addAttribute("comingSoon", movieService.top10InTheatersDatePage(model, 0));
        model.addAttribute("newTVShows", TVService.top10TVDatePage(model, 0));


        String currentDirectory;
        File file = new File("");
        currentDirectory = file.getAbsolutePath();
        currentDirectory += "/rottenpotato/src/main/resources/static/Trailers";
        File folder = new File(currentDirectory);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            currentDirectory = file.getAbsolutePath();
            currentDirectory += "/src/main/resources/static/Trailers";
            folder = new File(currentDirectory);
            listOfFiles = folder.listFiles();
        }

        List<String> trailerLists = new ArrayList<String>();
        int temp = 0;

        if (listOfFiles != null) {
            for (File fileName : listOfFiles) {
                temp = 1;
                trailerLists.add(fileName.getName());
            }
        }

        if (temp == 1) {
            model.addAttribute("hasTrailer", true);
        }

        model.addAttribute("testSamples", trailerLists);

        model.addAttribute("openingThisWeek", movieService.openingThisWeek(model, 0));
        model.addAttribute("highestRateMovie", CriticReviewService.getHighestRatingMovies(model, 0));

        List<Movie> winning = movieRepository.findOscarWinningYearDesc(PageRequest.of(0, 10));
        model.addAttribute("winningMovies", winning);
        model.addAttribute("tvCertified", TVService.tvCertifiedPick(model));

        return "index.html";
    }

    @GetMapping("/about")
    public String about() {
        return "about.html";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login.html";
    }

    @GetMapping("/service")
    public String servicePage() {
        return "termOfUse.html";
    }

    @GetMapping("/criticApplication")
    public String apply() {
        return "criticApplication.html";
    }

    @GetMapping("/FAQ")
    public String FAQ() {
        return "FAQ.html";
    }


}
