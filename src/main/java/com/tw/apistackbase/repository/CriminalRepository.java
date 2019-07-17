package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Criminal;
import org.springframework.data.repository.CrudRepository;

public interface CriminalRepository extends CrudRepository<Criminal,Long> {
}
