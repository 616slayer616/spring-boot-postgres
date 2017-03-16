package de.adler.springboot_postgres.controller;

import de.adler.springboot_postgres.database.repository.CustomerRepository;
import org.junit.Rule;
import org.mockito.Mock;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ControllerTest {

    MockMvc mockMvc;

    @Mock
    CustomerRepository customerRepositoryMock;

    @Rule
    public final JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("build/generated-snippets");

}
