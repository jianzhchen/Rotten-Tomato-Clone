package com.redwood.rottenpotato.main.services;


import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TVService {
    @Autowired
    private TVRepository TVRepository;

    public List<Map> top10TVDatePage(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (TV temp : TVRepository.findTop10ByOrderByTVDateDesc(PageRequest.of(page, 10))) {
            Map<String, String> map = new HashMap<>();

            map.put("TVName", temp.getTVName());
            map.put("TVDate", temp.getTVDate());
            map.put("key",temp.getTVKey());
            DateFormat formatter = new SimpleDateFormat("MMM-dd");
            templist.add(map);
        }
        return templist;
    }
}
