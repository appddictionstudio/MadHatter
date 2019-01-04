package com.Madhatter.MadHatter.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.Approval;


@Repository
public interface ApprovalRepositiry extends JpaRepository<Approval, Long> {}