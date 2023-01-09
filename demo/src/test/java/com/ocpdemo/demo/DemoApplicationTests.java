package com.ocpdemo.demo;

import com.ocpdemo.demo.Exeception.ApiRequestException;
import com.ocpdemo.demo.people.People;
import com.ocpdemo.demo.people.PeopleController;
import com.ocpdemo.demo.people.PeopleRepository;
import com.ocpdemo.demo.people.PeopleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DemoApplicationTests {

	private MockMvc mockMvc;
	@Autowired
	private PeopleService peopleService;

	@MockBean
	private PeopleRepository peopleRepository;

	People Mathan = new People(1L,
			           "Mathan",
					   "Mathan@foo.com",
					   "USA",
			   LocalDate.of(1993, AUGUST, 19));

	People Jacko = new People( 2L,
			"Jacko",
			"Jacko@foo.com",
			"UK",
			LocalDate.of(1994, OCTOBER, 12));


	@Test
	public void getPeopleTest(){
        when(peopleRepository.findAll()).thenReturn(Stream.of(new People(1L,
				"Mathan",
				"Mathan@foo.com",
				"USA",
				LocalDate.of(1993, AUGUST, 19)), new People( 2L,
				"Jacko",
				"Jacko@foo.com",
				"UK",
				LocalDate.of(1994, OCTOBER, 12)), new People(3L,
				"Jack",
				"Jack@foo.com",
				"Scotland",
				LocalDate.of(1993, OCTOBER, 11))).collect(Collectors.toList()));
		assertEquals(3, peopleService.getPeole().size());
	}

	@Test
	public void AddPeopleTest(){
		People Jessie = new People(5L,"Jessie", "Jessie@Facebook.com", "Segambut", LocalDate.of(1999, AUGUST, 19));
		when(peopleRepository.save(Jessie)).thenReturn(Jessie);
		assertEquals(Jessie, peopleService.addPeople(Jessie));
	}

	@Test
	public void DeletePeopleTest(){
		peopleRepository.deleteById(1L);
		assertThat(peopleRepository.existsById(1L)).isFalse();
	}

	@Test
	public void UpdatePeopleTest() {
		String Place = "Singapore";
		People people = new People(1L,
				"Mathan",
				"Mathan@foo.com", Place,
				LocalDate.of(1993, AUGUST, 19));
		people.setId(1L);
		peopleRepository.save(people);
		assertThat(people.getPlace()).isEqualTo(Place);
 	}

}
