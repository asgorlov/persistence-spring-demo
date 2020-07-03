package com.novaworld.persistence.dao;

import com.novaworld.persistence.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ango1019
 * Date: 03.07.2020
 * Time: 18:10
 */
public interface OfficerRepository extends JpaRepository<Officer, Integer> {

}