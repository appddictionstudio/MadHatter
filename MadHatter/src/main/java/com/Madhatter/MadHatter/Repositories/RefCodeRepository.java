package com.Madhatter.MadHatter.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringPath;

import com.Madhatter.MadHatter.models.QRefCode;
// import org.springframework.stereotype.Repository;
import com.Madhatter.MadHatter.models.RefCode;

@Repository
public interface RefCodeRepository extends JpaRepository<RefCode, Long>, QuerydslPredicateExecutor<RefCode>, QuerydslBinderCustomizer<QRefCode> {
	
	@Override
    default public void customize(QuerydslBindings bindings, QRefCode root) {
        bindings.bind(String.class).first(
          (StringPath path, String value) -> path.containsIgnoreCase(value));        
        
        
        
//        bindings.bind(root.lastModifiedDate)
//        .all((path, value) -> {
//            List<? extends Date> dates = new ArrayList<>(value);
//            if (dates.size() == 1) {
//                return Optional.of(path.eq(dates.get(0)));
//            } else {
//                Date from = dates.get(0);
//                Date to = dates.get(1);
//                return Optional.of(path.between(from, to));
//            }
//        });
    }
	
	List<RefCode> findByParentId(Long pid);
	List<RefCode> findByParentIdIsNull();
//	List<RefCode> findByLastModifiedDateGreaterThan(Date lmd);
	List<RefCode> findByNm(String nm);
	List<RefCode> findByIdIn(List<Long> ids);
//    RefCode findByNumbRefCode(String number);
	
	List<RefCode> findByParentRefCodeNmAndObsoleteYnAllIgnoreCaseOrderBySortOrder(String nm, String obsoleteYn);
	Page<RefCode> findByLastModifiedDateGreaterThanOrderByLastModifiedDateDesc(Date lmd, Pageable page);
	
//	@Query("select r from RefCode r where r.parentId = 5")
//	List<RefCode> getBltCategory();
//	
	
	@Query("select r from RefCode r where parentRefCode.nm = 'Bulletin Board - Categories' ")
	List<RefCode> getBltCategory();
}
