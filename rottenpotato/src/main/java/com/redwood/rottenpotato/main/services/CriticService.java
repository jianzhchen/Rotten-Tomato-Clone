package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.models.Critic;
import com.redwood.rottenpotato.main.repositories.CriticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CriticService {
    @Autowired
    private CriticRepository CriticRepository;


    public List<Map> getAllCriticNamesKeys(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (Critic temp : CriticRepository.findAll()) {
            Map<String, String> map = new HashMap<>();

            map.put("criticName", temp.getCriticName());
            map.put("criticKey",temp.getCriticKey());
            templist.add(map);
        }
        return templist;
    }

}
