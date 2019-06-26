package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufc.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
