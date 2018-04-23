package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActorMvcController {

    @Autowired
    private ActorRepository actorRepository;

    @RequestMapping("/c/{actorKey}")
    public String actorPage(@PathVariable("actorKey") String actorKey, Model model) {
        Actor actor = actorRepository.findByActorKey(actorKey);
        if (actor == null) {
            model.addAttribute("error", "actor not found");
        } else {
            model.addAttribute("name", actor.getActorName());
            String birthplace = actor.getBirthplace();
            model.addAttribute("birthplace", birthplace);
            String birthday = actor.getBirthday();
            model.addAttribute("birthday", birthday);
            String info = actor.getActorInfo();
            model.addAttribute("info", info);
        }
        return "actorPage.html";
    }
}
