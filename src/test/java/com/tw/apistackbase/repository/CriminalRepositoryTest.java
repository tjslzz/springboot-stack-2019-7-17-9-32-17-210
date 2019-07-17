package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Criminal;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CriminalRepositoryTest {

    @Autowired
    private CriminalRepository criminalRepository;

    @Test
    @DirtiesContext
    public void should_return_success_given_correct_criminal(){
        Criminal criminal = new Criminal("Jerry Kill Felicity",new Date().getTime());
        criminalRepository.save(criminal);
        List<Criminal> criminals = (List<Criminal>) criminalRepository.findAll();
        Assertions.assertEquals(1,criminals.size());
    }

    @Test
    @DirtiesContext
    public void should_not_return_success_given_null_criminal(){
        Criminal criminal = new Criminal();
        criminalRepository.save(criminal);
        Assertions.assertThrows(Exception.class,()->criminalRepository.findAll());
    }
}