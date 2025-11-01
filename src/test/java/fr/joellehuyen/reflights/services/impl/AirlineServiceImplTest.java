package fr.joellehuyen.reflights.services.impl;

import fr.joellehuyen.reflights.models.Airline;
import fr.joellehuyen.reflights.repositories.AirlineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class AirlineServiceImplTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Test
    void shouldReturnAllAirlines() {
        Airline a1 = new Airline();
        a1.setId("1");
        a1.setName("AIR FRANCE");
        Airline a2 = new Airline();
        a2.setId("2");
        a2.setName("DELTA");

        given(airlineRepository.findAll()).willReturn(Arrays.asList(a1, a2));

        List<Airline> result = airlineService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("AIR FRANCE", result.getFirst().getName());
        then(airlineRepository).should().findAll();
    }

}
