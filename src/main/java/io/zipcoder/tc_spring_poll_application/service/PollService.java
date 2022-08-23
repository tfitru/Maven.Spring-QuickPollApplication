package io.zipcoder.tc_spring_poll_application.service;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository repository;


    public List<Poll> findAllPolls(){
        return repository.findAll();
    }



}
