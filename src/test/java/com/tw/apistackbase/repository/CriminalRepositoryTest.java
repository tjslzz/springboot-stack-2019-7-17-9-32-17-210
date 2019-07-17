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

    @Test
    @DirtiesContext
    public void should_return_true_given_id_of_criminal(){
        Criminal criminal = new Criminal("Jerry Kill Felicity",Long.valueOf("20190717195312"));
        criminalRepository.save(criminal);
        Criminal result = criminalRepository.findById(Long.valueOf(1)).orElse(null);
        String json = "{\"id\":1,\"name\":\"Jerry Kill Felicity\",\"time\":20190717195312}";
        Assertions.assertEquals(json,result.toString());
    }

    @Test
    @DirtiesContext
    public void should_return_asc_list_given_id_of_criminal(){
        Criminal criminal1 = new Criminal("Jerry Kill Felicity",Long.valueOf("20190717195312"));
        Criminal criminal2 = new Criminal("Jerry Kill Laura",Long.valueOf("20190717195702"));
        criminalRepository.save(criminal1);
        criminalRepository.save(criminal2);
        List<Criminal> criminals = criminalRepository.findAllByOrderByTimeDesc();
        String json = "[{\"id\":2,\"name\":\"Jerry Kill Laura\",\"time\":20190717195702}, {\"id\":1,\"name\":\"Jerry Kill Felicity\",\"time\":20190717195312}]";
        Assertions.assertEquals(json,criminals.toString());
    }


    @Test
    @DirtiesContext
    public void should_return_true_list_given_name_of_criminal(){
        Criminal criminal1 = new Criminal("Jerry Kill Felicity",Long.valueOf("20190717195312"));
        Criminal criminal2 = new Criminal("Jerry Kill Felicity",Long.valueOf("20190717195702"));
        criminalRepository.save(criminal1);
        criminalRepository.save(criminal2);
        List<Criminal> criminals = criminalRepository.findAllByName("Jerry Kill Felicity");
        String json = "[{\"id\":1,\"name\":\"Jerry Kill Felicity\",\"time\":20190717195312}, {\"id\":2,\"name\":\"Jerry Kill Felicity\",\"time\":20190717195702}]";
        Assertions.assertEquals(json,criminals.toString());
    }
}