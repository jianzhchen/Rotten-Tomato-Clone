package com.redwood.rottenpotato.mvccontollers;

import com.redwood.rottenpotato.models.Temp;
import com.redwood.rottenpotato.repositories.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class Homecontroller {

    @Autowired
    private TempRepository tempRepository;

    @Autowired
    private EntityManager entityManager;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = {"", "/", "index.html"})
    public String greeting(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }

        List<Map> templist = new ArrayList<>();
        for (Temp temp : tempRepository.findTop10ByOrderByBoxOfficeDesc(PageRequest.of(0, 10))) {
            Map<String, String> map = new HashMap<>();
            map.put("rate", "N/A");
            map.put("movieName", temp.getName());
            log.error(temp.getName());
            map.put("boxOffice", temp.getBoxOffice().toString());
            templist.add(map);
        }
        model.addAttribute("topBoxOffice", templist);

        return "index.html";
    }
}
