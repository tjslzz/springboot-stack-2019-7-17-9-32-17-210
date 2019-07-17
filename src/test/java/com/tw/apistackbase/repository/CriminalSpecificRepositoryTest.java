package com.tw.apistackbase.repository;

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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CriminalSpecificRepositoryTest {

    @Autowired
    private CriminalSpecificRepository criminalSpecificRepository;

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
}