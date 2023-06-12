package com.example.Birthday_processor2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Sql({"/data.sql"})
class PersonServiceImplTest5 {

    @Mock
    private EmployeeRepository employeeRepository;

    @Captor
    ArgumentCaptor<EmployeeEntity> employeeEntityArgumentCaptor;

    @Captor
    ArgumentCaptor<String> fromDateCaptor;
    @Captor
    ArgumentCaptor<String> toDateCaptor;

    @Captor
    ArgumentCaptor<Person> personArgumentCaptor;

    @Captor
    ArgumentCaptor<String> fromCaptor;
    @Captor
    ArgumentCaptor<String> toCaptor;

    @InjectMocks
    private PersonServiceImpl employeeService;
    private Person person1;
    private Person person2;
    List<Person> personList;


    @BeforeEach
    public void setUp() {
        personList = new ArrayList<>();
        person1 = new Person(1L, "Delia","Neagu", LocalDate.of(2001, 12, 4));
        person2 = new Person(2L, "Martin","Smith", LocalDate.of(1970, 12, 31));
        personList.add(person1);
        personList.add(person2);
    }
    @AfterEach
    public void tearDown() {
        person1 = person2 = null;
        personList = null;
    }

    @Test
    void givenEmployeeToAddShouldReturnAddedEmployee() throws Exception{

        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(new EmployeeEntity());

        employeeService.savePerson(person1);

        verify(employeeRepository).save(employeeEntityArgumentCaptor.capture());
        EmployeeEntity savedEntity = employeeEntityArgumentCaptor.getValue();

        assertThat(savedEntity).isEqualTo(
                new EmployeeEntity(1L, "Delia","Neagu", LocalDate.of(2001, 12, 4))
        );


    }

    @Test
    public void GivenGetAllUsersShouldReturnListOfAllUsers(){
        var entityToReturn1= new EmployeeEntity(1L,"Delia", "Neagu", LocalDate.of(2001, 12, 4));
        //stubbing mock to return specific data
        //employeeRepository.save(entityToReturn1);
        List<EmployeeEntity> employeeEntities = List.of(entityToReturn1);
        when(employeeRepository.findAll()).thenReturn(employeeEntities);
        List<Person> personList1 =employeeService.getPeople();
        assertEquals(personList1,List.of(new Person(1L,"Delia", "Neagu", LocalDate.of(2001, 12, 4))));
        verify(employeeRepository,times(1)).findAll();
    }

    @Test
    public void givenIdThenShouldReturnEmployeeOfThatId() {
        var entityToReturn1= new EmployeeEntity(1L,"Delia", "Neagu", LocalDate.of(2001, 12, 4));
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(entityToReturn1));
        Optional<Person> singleEmployee = employeeService.getSinglePerson(entityToReturn1.getEmployeeId());
        assertEquals(singleEmployee,Optional.of(new Person(1l,"Delia","Neagu", LocalDate.of(2001,12,4))));
    }

    @Test
    void testCallToRepositoryForFindUpcomingBirthdays() {

        // Given
        EmployeeEntity martinEmp = new EmployeeEntity(1L, "Martin", "Smith", LocalDate.of(1975, 12, 14));
        when(employeeRepository.findAllByMonthDayRange(anyString(), anyString()))
                .thenReturn(List.of(martinEmp));

        employeeService.SetClock(Clock.fixed(Instant.parse("2023-05-15T12:30:59Z"), ZoneId.systemDefault()));

        // When
        List<Person> employeesWithUpcomingBirthday = employeeService.findWithUpcomingBirthday(99);

        // Then
        verify(employeeRepository)
                .findAllByMonthDayRange(fromCaptor.capture(), toCaptor.capture());

        String fromCaptorValue = fromCaptor.getValue();
        String toCaptorValue= toCaptor.getValue();

        Assertions.assertAll(
                () -> assertThat(fromCaptorValue).isEqualTo("0515"),
                () -> assertThat(toCaptorValue).isEqualTo("0822")

        );

    }


}