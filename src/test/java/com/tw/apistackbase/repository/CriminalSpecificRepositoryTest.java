package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Criminal;
import com.tw.apistackbase.entity.CriminalSpecific;
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
public class CriminalSpecificRepositoryTest {

    @Autowired
    private CriminalSpecificRepository criminalSpecificRepository;
    @Autowired
    private CriminalRepository criminalRepository;

    @Test
    @DirtiesContext
    public void should_return_size_given_correct_criminal_specific(){
        CriminalSpecific criminalSpecific = new CriminalSpecific("Jerry Kill Sean","Sean kill Laura");
        criminalSpecificRepository.save(criminalSpecific);
        List<CriminalSpecific> criminalSpecifics = (List<CriminalSpecific>) criminalSpecificRepository.findAll();
        Assertions.assertEquals(1,criminalSpecifics.size());
    }

    @Test
    @DirtiesContext
    public void should_not_return_nothing_given_null_criminal_specific(){
        CriminalSpecific criminalSpecific = new CriminalSpecific();
        criminalSpecificRepository.save(criminalSpecific);
        Assertions.assertThrows(Exception.class,()->criminalSpecificRepository.findAll());
    }

    @Test
    @DirtiesContext
    public void should_return_true_criminal_specific_given_id(){
        CriminalSpecific criminalSpecific = new CriminalSpecific("Jerry Kill Sean","Sean kill Laura");
        criminalSpecificRepository.save(criminalSpecific);
        CriminalSpecific result = criminalSpecificRepository.findById(Long.valueOf("1")).orElse(null);
        String json = "{\"id\":1,\"objective\":\"Jerry Kill Sean\",\"subjective\":\"Sean kill Laura\"}";
        Assertions.assertEquals(json,result.toString());
    }


    @Test
    @DirtiesContext
    public void should_return_true_criminal_info_given_one_to_one(){
        CriminalSpecific criminalSpecific = new CriminalSpecific("Jerry Kill Sean","Sean kill Laura");
        criminalSpecificRepository.save(criminalSpecific);
        Criminal criminal = new Criminal("Jerry Kill Felicity",Long.valueOf("20190717205055"),criminalSpecific);
        criminalRepository.save(criminal);
        Criminal result = criminalRepository.findAllByName("Jerry Kill Felicity").get(0);
        String json = "{\"id\":2,\"name\":\"Jerry Kill Felicity\",\"time\":20190717205055,\"criminalSpecific\":{\"id\":1,\"objective\":\"Jerry Kill Sean\",\"subjective\":\"Sean kill Laura\"}}";
        Assertions.assertEquals(json,result.toString());
    }


    @Test
    @DirtiesContext
    public void should_return_true_info_given_new_specific(){
        CriminalSpecific criminalSpecific = new CriminalSpecific("Jerry Kill Sean","Sean kill Laura");
        criminalSpecificRepository.save(criminalSpecific);
        Criminal criminal = new Criminal("Jerry Kill Felicity",Long.valueOf("20190717205055"),criminalSpecific);
        criminalRepository.save(criminal);
        CriminalSpecific criminalSpecific2 = new CriminalSpecific("Jerry Kill Laura","Laura kill Sean");
        criminalSpecificRepository.save(criminalSpecific2);

        Criminal criminal2 = criminalRepository.findAllByName("Jerry Kill Felicity").get(0);
        criminal2.setCriminalSpecific(criminalSpecific2);
        criminalRepository.save(criminal2);

        Criminal result = criminalRepository.findAllByName("Jerry Kill Felicity").get(0);

        String json = "{\"id\":2,\"name\":\"Jerry Kill Felicity\",\"time\":20190717205055,\"criminalSpecific\":{\"id\":3,\"objective\":\"Jerry Kill Laura\",\"subjective\":\"Laura kill Sean\"}}";
        Assertions.assertEquals(json,result.toString());
    }
}