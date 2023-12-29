package com.example.teacherprojectbackend;

import com.example.teacherprojectbackend.model.Gender;
import com.example.teacherprojectbackend.model.Lesson;
import com.example.teacherprojectbackend.model.Teacher;
import com.example.teacherprojectbackend.repository.ITeacherRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TeacherProjectBackendApplication {

	@Bean
	CommandLineRunner runner(ITeacherRepository teacherRepository) {
		Teacher teacher = new Teacher();
		teacher.setFirstName("test");
		teacher.setLastName("test");
		teacher.setGender(Gender.MALE);
		teacher.setEmail("zisis@gmail.com");
		teacher.setCreateTime(LocalDateTime.now());
		teacherRepository.save(teacher);

		return args -> {
			for (int i = 0; i < 5; i++) {
				var faker = new Faker();
				Name name = faker.name();
				String firstName = name.firstName();
				String lastName = name.lastName();
				String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com";

				Lesson mathimatika = new Lesson();
				mathimatika.setName("MATHIMATIKA");

				Teacher teacher2 = new Teacher(
						firstName,
						lastName,
						email,
						mathimatika,
						Gender.MALE,
						LocalDateTime.now()
				);
				teacherRepository.save(teacher2);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TeacherProjectBackendApplication.class, args);
	}

}
