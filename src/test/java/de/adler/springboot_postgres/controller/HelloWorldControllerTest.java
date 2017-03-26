package de.adler.springboot_postgres.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class HelloWorldControllerTest extends ControllerTest {

    @InjectMocks
    private HelloWorldController helloWorldController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(helloWorldController)
                .apply(documentationConfiguration(this.restDocumentation).uris()
                        .withScheme("http").withHost("example.com").withPort(80))
                .build();
    }

    @Test
    public void helloTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("root"
                )).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertThat(content, is("Hallol"));
    }
}