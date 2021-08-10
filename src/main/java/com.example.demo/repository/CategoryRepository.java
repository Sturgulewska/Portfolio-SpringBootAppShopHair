package com.example.demo.repository;

import com.example.demo.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    // @Query("SELECT s FROM CategoryEntity s WHERE s.id = :id AND s.active = :active")
    //   Optional<CategoryEntity> findById(Long id, Boolean active);

}
