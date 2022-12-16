package ru.kata.spring.boot.security.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurity312JpaApplicationTests {

	@Test
	void contextLoads() {
	}
//@Query("select c from User c join fetch c.roles where c.userName= :username")
}