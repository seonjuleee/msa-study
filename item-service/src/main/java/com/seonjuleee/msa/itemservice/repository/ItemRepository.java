package com.seonjuleee.msa.itemservice.repository;

import com.seonjuleee.msa.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
