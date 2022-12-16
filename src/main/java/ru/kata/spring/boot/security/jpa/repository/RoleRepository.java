package ru.kata.spring.boot.security.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot.security.jpa.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "select u from Role u join fetch u.users where u.name= :name")
    Role getByName(@Param("name") String name);
}