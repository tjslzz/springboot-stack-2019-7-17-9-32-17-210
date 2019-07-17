package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Procuratorate;
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
public class ProcuratorateRepositoryTest {

    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Test
    @DirtiesContext
    public void should_return_true_size_given_correct_procuratorate(){
        Procuratorate procuratorate = new Procuratorate("OOCL");
        procuratorateRepository.save(procuratorate);
        List<Procuratorate> result = (List<Procuratorate>) procuratorateRepository.findAll();
        Assertions.assertEquals(1,result.size());
    }

    @Test
    @DirtiesContext
    public void should_return_exception_given_repeat_procuratorate(){
        Procuratorate procuratorate = new Procuratorate("OOCL");
        procuratorateRepository.save(procuratorate);
        Procuratorate procuratorate2 = new Procuratorate("OOCL");
        procuratorateRepository.save(procuratorate2);
        Assertions.assertThrows(Exception.class,()->procuratorateRepository.findAll());
    }

    @Test
    @DirtiesContext
    public void should_return_true_procuratorate_info_given_1(){
        Procuratorate procuratorate = new Procuratorate("OOCL");
        procuratorateRepository.save(procuratorate);
        Procuratorate result = procuratorateRepository.findById(Long.valueOf(1)).orElse(null);
        String json = "{\"id\":1,\"name\":\"OOCL\"}";
        Assertions.assertEquals(json,result.toString());
    }
}