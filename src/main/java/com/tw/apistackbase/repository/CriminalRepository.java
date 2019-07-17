package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Criminal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CriminalRepository extends CrudRepository<Criminal,Long> {

    List<Criminal> findAllByOrderByTimeDesc();
}
