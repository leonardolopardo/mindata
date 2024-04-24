package com.mindata.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mindata.model.Superheroe;

@Service
@Repository
public interface SuperheroeService extends CrudRepository<Superheroe, Long> {
	@Query(value="select * from Superheroe sh where UPPER(sh.name) like UPPER(concat('%',:name,'%'))", nativeQuery=true)
	List<Superheroe> findSuperheroeByNameLike(@Param("name") String name);
}
