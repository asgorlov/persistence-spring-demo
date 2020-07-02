package com.novaworld.persistence.dao;

import com.novaworld.persistence.entity.Officer;

import java.util.List;
import java.util.Optional;

/**
 * @author ango1019
 * Date: 02.07.2020
 * Time: 16:26
 */
public interface OfficerDao {
    Officer save(Officer officer);
    Optional<Officer> findById(Integer id);
    List<Officer> findAll();
    long count();
    void delete(Officer officer);
    boolean existById(Integer id);
}