package com.novaworld.persistence.dao;

import com.novaworld.persistence.entity.Officer;
import com.novaworld.persistence.entity.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ango1019
 * Date: 02.07.2020
 * Time: 23:26
 */
@SuppressWarnings({"SqlNoDataSourceInspection"})
@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class JpaOfficerDaoTest {
    @Autowired
    @Qualifier("jpaOfficerDao")
    private OfficerDao dao;

    @Autowired
    private JdbcTemplate template;

    private final RowMapper<Integer> idMapper = (rs, num) -> rs.getInt("id");

    @Test
    void testSave() {
        Officer officer = new Officer(Rank.ADMIRAL, "Pavel", "Korzh");
        dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    void findOneThatExist() {
        template.query("SELECT id FROM officers", idMapper)
                .forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertTrue(officer.isPresent());
                    assertEquals(id, officer.get().getId());
                });
    }

    @Test
    void findOneThatDoesNotExist() {
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
        template.query("SELECT id FROM officers", idMapper)
                .forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertTrue(officer.isPresent());
                    dao.delete(officer.get());
                });
        assertEquals(0, dao.count());
    }

    @Test
    void existById() {
        template.query("SELECT id FROM officers", idMapper)
                .forEach(id -> assertTrue(dao.existById(id)));
    }

    @Test
    void doesNotExist() {
        List<Integer> ids = template.query("SELECT id FROM officers", idMapper);
        assertThat(ids, not(contains(999)));
    }
}