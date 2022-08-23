package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.service.PollService;
import io.zipcoder.tc_spring_poll_application.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/pollsp")
public class PagingSortingController {

    @Autowired
    private PollService service;
    private VoteService voteService;

    @GetMapping
    private APIResponse<List<Poll>> getPolls(){
        List<Poll> allPolls = service.findAllPolls();
        return new APIResponse<>(allPolls.size(), allPolls);
    }

    @GetMapping
    private APIResponse<List<Poll>> getPolls(@PathVariable String field){
        List<Poll> allPolls = service.findAllPolls();
        return new APIResponse<>(allPolls.size(), allPolls);
    }


    @GetMapping
    private APIResponse<List<Vote>> getVotes(@PathVariable String field){
        List<Vote> allVotes = voteService.findAllVotes();
        return new APIResponse<>(allVotes.size(), allVotes);
    }





}
