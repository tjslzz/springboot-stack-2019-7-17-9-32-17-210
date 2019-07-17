package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Procuratorate;
import com.tw.apistackbase.entity.Prosecutor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProsecutorRepositoryTest {

    @Autowired
    private ProsecutorRepository prosecutorRepository;
    @Autowired
    private ProcuratorateRepository procuratorateRepository;

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

    @Test
    @DirtiesContext
    public void should_return_true_info_given_1(){
        Prosecutor prosecutor = new Prosecutor("jerryLi");
        prosecutorRepository.save(prosecutor);
        Prosecutor result = prosecutorRepository.findById(Long.valueOf(1)).orElse(null);
        String json = "{\"id\":1,\"name\":\"jerryLi\"}";
        Assertions.assertEquals(json,result.toString());
    }

    @Test
    @DirtiesContext
    public void should_return_true_list_info_given_many_to_one(){
        Prosecutor prosecutor1 = new Prosecutor("jerryLi");
        Prosecutor prosecutor2 = new Prosecutor("felicity");
        prosecutorRepository.save(prosecutor1);
        prosecutorRepository.save(prosecutor2);
        List<Prosecutor> prosecutors = new ArrayList<>();
        prosecutors.add(prosecutor1);prosecutors.add(prosecutor2);

        Procuratorate procuratorate = new Procuratorate("Laura",prosecutors);
        procuratorateRepository.save(procuratorate);

        List<Procuratorate> result = (List<Procuratorate>) procuratorateRepository.findAll();

        String json = "{\"id\":3,\"name\":\"Laura\",\"prosecutors\":[{\"id\":1,\"name\":\"jerryLi\"}, {\"id\":2,\"name\":\"felicity\"}]}";
        Assertions.assertEquals(json,result.get(0).toString());
    }
}