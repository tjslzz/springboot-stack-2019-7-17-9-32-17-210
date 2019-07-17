package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Criminal;
import com.tw.apistackbase.entity.CriminalSpecific;
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
    @Autowired
    private CriminalRepository criminalRepository;
    @Autowired
    private CriminalSpecificRepository criminalSpecificRepository;

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

    @Test
    @DirtiesContext
    public void should_return_true_procuratorate_info_given_one_to_one(){
        Procuratorate procuratorate = new Procuratorate("OOCL");
        procuratorateRepository.save(procuratorate);

        CriminalSpecific criminalSpecific = new CriminalSpecific("Jerry Kill Sean","Sean kill Laura");
        criminalSpecificRepository.save(criminalSpecific);
        Criminal criminal = new Criminal("Jerry Kill Felicity",Long.valueOf("20190717205055"),criminalSpecific,procuratorate);
        criminalRepository.save(criminal);

        Criminal result = criminalRepository.findAllByName("Jerry Kill Felicity").get(0);

        String json = "{\"id\":3,\"name\":\"Jerry Kill Felicity\",\"time\":20190717205055,\"criminalSpecific\":{\"id\":2,\"objective\":\"Jerry Kill Sean\",\"subjective\":\"Sean kill Laura\"},\"procuratorate\":{\"id\":1,\"name\":\"OOCL\"}}";
        Assertions.assertEquals(json,result.toString());
    }
}