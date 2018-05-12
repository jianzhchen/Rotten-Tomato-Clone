package com.redwood.rottenpotato.main.mvccontollers;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import java.io.File;
        import java.security.Principal;
        import java.util.ArrayList;
        import java.util.List;

@Controller
public class VideoController {
    @GetMapping(value = "/v/all")
    public String videlDetail(Model model, Principal principal){

        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }


        String currentDirectory;
        File file = new File("");
        currentDirectory = file.getAbsolutePath();
        currentDirectory += "/src/main/resources/static/Trailers";

        File folder = new File(currentDirectory.toString());

        File[] listOfFiles = folder.listFiles();

        if(listOfFiles==null){
            currentDirectory = file.getAbsolutePath();
            currentDirectory += "/src/main/resources/static/Trailers";
            folder = new File(currentDirectory);
            listOfFiles = folder.listFiles();
        }
//
//        if(listOfFiles==null){
//            currentDirectory = file.getAbsolutePath();
//            currentDirectory += "/src/main/resources/static/Trailers";
//            folder = new File(currentDirectory);
//            listOfFiles = folder.listFiles();
//        }
        List<String> trailerLists = new ArrayList<String>();

        if(listOfFiles != null){
            for(File fileName: listOfFiles){
                trailerLists.add(fileName.getName());
            }
        }

        model.addAttribute("testSamples", trailerLists);

        return "video.html";
    }
}
