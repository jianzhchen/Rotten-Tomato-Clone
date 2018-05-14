package com.redwood.rottenpotato.main.services;


import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.repositories.CriticReviewRepository;
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
    @Autowired
    private CriticReviewRepository CriticReviewRepository;


    public List<Map> top10TVDatePage(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (TV temp : TVRepository.findTop10ByOrderByTVDateDateDesc(PageRequest.of(page, 10))) {
            Map<String, String> map = new HashMap<>();

            map.put("TVName", temp.getTVName());
            map.put("TVDate", temp.getTVDate());
            map.put("key",temp.getTVKey());
            DateFormat formatter = new SimpleDateFormat("MMM-dd");
            templist.add(map);
        }
        return templist;
    }

    public List<Map> tvCertifiedPick(Model model){
        List<Map> tempList = new ArrayList<>();

        List<Object[]> crList = CriticReviewRepository.findTopByAvgScoreTV(5);
        Object[] objTemp;

        TV tempTV;
        for(int i = 0; i < crList.size() - 1; i++){
            Map<String, String> map = new HashMap<>();
            objTemp = crList.get(i);

            tempTV = TVRepository.findByTVKey(objTemp[0].toString());

            if(tempTV == null  | (double)objTemp[1] < 4.5){
                continue;
            }
            map.put("TVName", tempTV.getTVName());
            map.put("TVKey", tempTV.getTVKey());
            map.put("rate",  objTemp[1].toString());
            tempList.add(map);
        }
        return tempList;
    }
}
