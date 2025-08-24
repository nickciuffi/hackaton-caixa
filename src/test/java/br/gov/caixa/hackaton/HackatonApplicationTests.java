package br.gov.caixa.hackaton;

import br.gov.caixa.hackaton.service.implementation.EventHubServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class HackatonApplicationTests {

	@Autowired
	private EventHubServiceImpl eventHubService;

	@Test
	void contextLoads() {
		assertNotNull(eventHubService);
	}
}

