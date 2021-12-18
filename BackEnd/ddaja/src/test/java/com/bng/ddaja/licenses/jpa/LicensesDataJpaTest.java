package com.bng.ddaja.licenses.jpa;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.licenses.repository.LicensesRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@AutoConfigureTestDatabase(replace=Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class LicensesDataJpaTest {
    private static final Logger LOG = LoggerFactory.getLogger(LicensesDataJpaTest.class);
    @Autowired
    private LicensesRepository licensesRepository;

    @Test
    public void licenseFindAll() {
        // given

        // when
        List<License> domainList = licensesRepository.findAll();

        // then
        assertNotNull(domainList);
    }
}