package io.zipcoder.tc_spring_poll_application.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface PagingAndSortingRepository<T, ID extends Serializable> extends Repository<T, ID> {



    Iterable<T> findAll(Sort sort);


    Page<T> findAll(Pageable pageable);



}
