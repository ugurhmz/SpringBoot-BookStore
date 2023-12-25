package com.ugurhmz.bookstore.repository;

import com.ugurhmz.bookstore.entities.PostCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCodeRepository extends JpaRepository<PostCode, Long> {
}
