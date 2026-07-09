package com.hari.sb.httpdemo.repo;

import com.hari.sb.httpdemo.dao.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

}
