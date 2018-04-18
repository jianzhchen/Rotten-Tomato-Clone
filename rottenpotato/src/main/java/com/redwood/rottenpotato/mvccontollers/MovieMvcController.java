package com.redwood.rottenpotato.mvccontollers;

import com.redwood.rottenpotato.models.Temp;
import com.redwood.rottenpotato.repositories.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieMvcController {

    @Autowired
    private TempRepository tempRepository;

    @GetMapping(value = "searchResult.html")
    public String greeting(@RequestParam("term") String searchTerm, Model model) {

        List<Temp> resultList = tempRepository.searchByName(searchTerm);

        model.addAttribute("info", resultList.get(0).getInfo());
        return "searchResult.html";
    }
}
