package com.example.birthday;

import com.example.birthday.controller.PersonRestController;
import com.example.birthday.services.Person;
import com.example.birthday.services.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonRestController.class)

class PersonRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRestController underTest;

    @MockBean
    private PersonService employeeSer;

    @Test
    void shouldReturnListOfEmployees() throws Exception {
        when(employeeSer.getPeople())
                .thenReturn(List.of(new Person(1L,"Delia", "Neagu", LocalDate.of(2001, 12, 04))));
        ResultActions resultActions = this.mvc
                .perform(MockMvcRequestBuilders
                        .get("/api/people"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].givenName").value("Delia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].familyName").value("Neagu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth").value("2001-12-04"));


    }

    @Test
    void shouldCreateEmployee() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders
                        .post("/api/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeId\": \"1\", \"givenName\": \"Delia\", \"familyName\": \"Neagu\", \"dateOFBirth\": \"2001-12-04\"}")
                ).andExpect(MockMvcResultMatchers.status().isOk());
        verify(employeeSer).savePerson(any(Person.class));

    }

    @Test
    void shouldGetSingleEmployeeById() throws Exception{
        when(employeeSer.getPeople())
                .thenReturn(List.of(new Person(1L,"Delia", "Neagu", LocalDate.of(2001, 12, 04))));
        ResultActions resultActions = this.mvc
                .perform(MockMvcRequestBuilders
                        .get("/api/people"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }




}