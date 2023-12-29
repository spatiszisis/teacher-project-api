package com.example.teacherprojectbackend;

import com.example.teacherprojectbackend.converter.TeacherConverter;
import com.example.teacherprojectbackend.model.Gender;
import com.example.teacherprojectbackend.model.Teacher;
import com.example.teacherprojectbackend.repository.ITeacherRepository;
import com.example.teacherprojectbackend.service.TeacherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@Testcontainers
@SpringBootTest
class TeacherProjectBackendApplicationTests {
	@Container
	public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
			.withDatabaseName("zisis-test")
			.withUsername("zisispatis")
			.withPassword("password");

	@DynamicPropertySource
	private static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
		registry.add(
				"spring.datasource.url",
				postgreSQLContainer::getJdbcUrl
		);

		registry.add(
				"spring.datasource.username",
				postgreSQLContainer::getUsername
		);

		registry.add(
				"spring.datasource.password",
				postgreSQLContainer::getPassword
		);
	}

	@Test
	void contextLoads() {
		System.out.println("Test");
	}

}
