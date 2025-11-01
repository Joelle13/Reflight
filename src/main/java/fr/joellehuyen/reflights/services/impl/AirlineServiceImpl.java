package fr.joellehuyen.reflights.services.impl;

import fr.joellehuyen.reflights.dtos.AirlineDto;
import fr.joellehuyen.reflights.models.Airline;
import fr.joellehuyen.reflights.repositories.AirlineRepository;
import fr.joellehuyen.reflights.services.AirlineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository airlineRepository;

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline createAirline(AirlineDto airlineDto) {
        Airline airline = new Airline();
        airline.setName(airlineDto.getName().toUpperCase());
        log.info("airline created {}", airlineDto.getName());
        return airlineRepository.save(airline);
    }
}
