package com.ojt.mvc_student_jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class ServletInitializerTest {
    @Mock
  private SpringApplicationBuilder springApplicationBuilder;

  @Test
  public void testIt() {
    ServletInitializer servletInitializer = new ServletInitializer();
    when(springApplicationBuilder.sources(MvcStudentJpaApplication.class)).thenReturn(springApplicationBuilder);

    SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

    verify(springApplicationBuilder).sources(MvcStudentJpaApplication.class);
    assertEquals(springApplicationBuilder,result);
}
}
