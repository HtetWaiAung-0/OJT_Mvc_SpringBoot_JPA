package com.ojt.mvc_student_jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MvcStudentJpaApplicationTests {
   

	@Test
	  public void applicationStarts() {
		MvcStudentJpaApplication.main(new String[] {});
	  }
}