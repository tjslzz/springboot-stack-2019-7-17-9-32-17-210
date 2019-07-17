package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Prosecutor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProsecutorRepositoryTest {

    @Autowired
    private ProsecutorRepository prosecutorRepository;

    @Test
    @DirtiesContext
    public void should_return_true_prosecutor_given_correct_prosecutor(){
        Prosecutor prosecutor = new Prosecutor("jerryLi");
        prosecutorRepository.save(prosecutor);
        List<Prosecutor> result = (List<Prosecutor>) prosecutorRepository.findAll();
        Assertions.assertEquals(1,result.size());
    }

    @Test
    @DirtiesContext
    public void should_return_exception_given_correct_prosecutor(){
        Prosecutor prosecutor = new Prosecutor();
        prosecutorRepository.save(prosecutor);
        Assertions.assertThrows(Exception.class,()->prosecutorRepository.findAll());
    }


}