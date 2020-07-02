package com.novaworld.persistence.dao;

import com.novaworld.persistence.entity.Officer;
import com.novaworld.persistence.entity.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ango1019
 * Date: 02.07.2020
 * Time: 17:01
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class JdbcOfficerDaoTest {
    @Autowired
    private OfficerDao dao;

    @Test
    void save() {
        Officer officer = new Officer(Rank.ADMIRAL, "Pavel", "Korzh");
        dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    void findByIdThatExist() {
        Optional<Officer> officer = dao.findById(1);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId());
    }

    @Test
    void findByIdThatDoesNotExist() {
        Optional<Officer> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    void findAll() {
        List<String> dbNames = dao.findAll().stream()
                                  .map(Officer::getLast)
                                  .collect(Collectors.toList());
        assertThat(dbNames, containsInAnyOrder("Bond", "Tompson", "Lil", "Sort", "Kren"));
    }

    @Test
    void count() {
        assertEquals(5, dao.count());
    }

    @Test
    void delete() {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertTrue(officer.isPresent());
                    dao.delete(officer.get());
                });
        assertEquals(0, dao.count());
    }

    @Test
    void existById() {
        IntStream.rangeClosed(1, 5).forEach(id -> assertTrue(dao.existById(id)));
    }
}