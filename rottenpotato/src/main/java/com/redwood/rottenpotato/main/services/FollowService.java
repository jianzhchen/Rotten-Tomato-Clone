package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.FCount;
import com.redwood.rottenpotato.main.models.Follow;
import com.redwood.rottenpotato.main.repositories.FCountRepository;
import com.redwood.rottenpotato.main.repositories.FollowRepository;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private FCountRepository fCountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;

    public String follow(String userEmail, long userId) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        Follow followEntity = new Follow();
        followEntity.setUserIdFrom(user.getId());
        followEntity.setUserIdTo(userId);
        followRepository.save(followEntity);
//        FCount fCount = fCountRepository.findByUserId(userId);
//        if(fCount==null){
//            fCount=new FCount();
//            fCount.setUserId(userId);
//            fCount.setFollowerCount(1);
//            fCountRepository.save(fCount);
//        }
//        else{
//            fCount.incFollowerCount();
//            fCountRepository.save(fCount);
//        }
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String unFollow(String userEmail, long userId) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        followRepository.removeByUserIdFromAndUserIdTo(user.getId(), userId);
        System.out.println(user.getId()+" "+userId);
//        FCount fCount = fCountRepository.findByUserId(userId);
//        if(fCount==null){
//            fCount=new FCount();
//            fCount.setUserId(userId);
//            fCount.setFollowerCount(0);
//            fCountRepository.save(fCount);
//        }
//        else{
//            fCount.decFollowerCount();
//            fCountRepository.save(fCount);
//        }
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

}
