package com.redwood.rottenpotato.main.mvccontollers;


        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VideoController {
    @GetMapping(value = "/v/all")
    public String videlDetail(Model model){

        return "video.html";
    }
}
