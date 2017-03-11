package de.adler.springboot_hibernate.controller;

import de.adler.springboot_hibernate.database.repository.CustomerRepository;
import org.junit.Rule;
import org.mockito.Mock;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ControllerTest {

    MockMvc mockMvc;

    static final String passKey = "abc1234";

    @Mock
    CustomerRepository customerRepositoryMock;

    @Rule
    public final JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("build/generated-snippets");

}
