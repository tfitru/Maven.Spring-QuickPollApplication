package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import io.zipcoder.tc_spring_poll_application.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PollController {

    @Autowired
    private PollService service;
    @Autowired
    PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @RequestMapping(value="/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {


        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(value="/polls", method=RequestMethod.POST)
    // parameter of type @RequestBody Poll poll
    // Tells Spring that entire request body needs to be converted
    // to an instance of Poll
    // delegates the poll persistence to PollRepository save method
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(newPollUri);
        poll = pollRepository.save(poll);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /*
    The code snippet below enables us to access an individual poll.
    The value attribute in the @RequestMapping takes a URI template /polls/{pollId}.
    The placeholder {pollId} along with @PathVarible annotation allows Spring to examine the request URI path and extract the pollId parameter value.
    Inside the method, we use the PollRepository’s findOne finder method to read the poll and pass it as part of a ResponseEntity.
     */

    @Valid
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
        verifyPoll(pollId);
        Poll p = pollRepository.findOne(pollId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @Valid
    @RequestMapping(value="/polls/{pollID}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        //save the entity
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        verifyPoll(pollId);
        pollRepository.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/pollsp", method=RequestMethod.GET)
    private APIResponse<List<Poll>> getPolls(){
        List<Poll> allPolls = service.findAllPolls();
        return new APIResponse<>(allPolls.size(), allPolls);
    }






    public void verifyPoll(Long pollId) {
        if(!pollRepository.exists(pollId)){
            throw new ResourceNotFoundException("This id does not exist");
        }
    }




}
