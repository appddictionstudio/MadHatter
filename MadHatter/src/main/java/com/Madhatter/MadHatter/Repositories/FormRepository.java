package com.Madhatter.MadHatter.Repositories;

import java.util.Optional;
import com.Madhatter.MadHatter.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Optional<Form> findByFormNumber(String formNumber);

    @Query(value = "SELECT MAX(form_number) FROM form WHERE form_number LIKE %:formNumGroup%", nativeQuery = true)
    String findLastFormNumber(String formNumGroup);
}