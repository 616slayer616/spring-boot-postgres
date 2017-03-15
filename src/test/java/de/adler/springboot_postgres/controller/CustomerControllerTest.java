package de.adler.springboot_postgres.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adler.springboot_postgres.database.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class CustomerControllerTest extends ControllerTest {

    private static final String lastName = "Bauer";

    private static final String URL_CUSTOMER = "customer";
    private static final String URL_CUSTOMER_REST = "/" + URL_CUSTOMER;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .apply(documentationConfiguration(this.restDocumentation).uris()
                        .withScheme("http").withHost("example.com").withPort(80))
                .build();
    }

    @Test
    public void getCustomersByLastNameTest() throws Exception {
        Customer bauerRef = new Customer("Jack", lastName);
        List<Customer> bauerList = new ArrayList<>();
        bauerList.add(bauerRef);
        when(customerRepositoryMock.findByLastName(lastName)).thenReturn(bauerList);

        List<FieldDescriptor> fields = new ArrayList<>();
        fields.add(fieldWithPath("[]").description("List of users with specified last name."));
        fields.add(fieldWithPath("[].firstName").description("First name"));
        fields.add(fieldWithPath("[].lastName").description("Last name"));

        MvcResult mvcResult = this.mockMvc.perform(get(URL_CUSTOMER_REST + "/" + lastName)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(URL_CUSTOMER + "GET",
                        responseFields(fields)
                )).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<Customer> resultList = new ObjectMapper().readValue(content, new TypeReference<List<Customer>>() {
        });
        Assert.assertThat(resultList.get(0), is(bauerRef));

        verify(customerRepositoryMock, times(1)).findByLastName(lastName);
    }

    @Test
    public void saveCustomerTest() throws Exception {
        Customer bauerRef = new Customer("Jack", lastName);
        ObjectMapper mapper = new ObjectMapper();
        String bauerJSON = mapper.writeValueAsString(bauerRef);
        when(customerRepositoryMock.save(bauerRef)).thenReturn(bauerRef);

        List<FieldDescriptor> fields = new ArrayList<>();
        fields.add(fieldWithPath("firstName").description("First name"));
        fields.add(fieldWithPath("lastName").description("Last name"));

        MvcResult mvcResult = this.mockMvc.perform(put(URL_CUSTOMER_REST)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bauerJSON))
                .andExpect(status().isCreated())
                .andDo(document(URL_CUSTOMER + "PUT",
                        responseFields(fields)
                )).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Customer result = new ObjectMapper().readValue(content, new TypeReference<Customer>() {
        });
        Assert.assertThat(result, is(bauerRef));

        verify(customerRepositoryMock, times(1)).save(bauerRef);
    }

    @Test
    public void saveCustomerDuplicateTest() throws Exception {
        Customer bauerRef = new Customer("Jack", lastName);
        ObjectMapper mapper = new ObjectMapper();
        String bauerJSON = mapper.writeValueAsString(bauerRef);
        doThrow(new DataIntegrityViolationException("")).when(customerRepositoryMock).save(bauerRef);


        MvcResult mvcResult = this.mockMvc.perform(put(URL_CUSTOMER_REST)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bauerJSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertThat(content, is(""));

        verify(customerRepositoryMock, times(1)).save(bauerRef);
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        Customer bauerRef = new Customer("Jack", lastName);
        ObjectMapper mapper = new ObjectMapper();
        String bauerJSON = mapper.writeValueAsString(bauerRef);

        MvcResult mvcResult = this.mockMvc.perform(delete(URL_CUSTOMER_REST)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bauerJSON))
                .andExpect(status().isAccepted())
                .andDo(document(URL_CUSTOMER + "DELETE"))
                .andReturn();

        verify(customerRepositoryMock, times(1)).delete(bauerRef);
    }

}