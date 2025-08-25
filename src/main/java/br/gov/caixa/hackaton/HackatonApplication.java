package br.gov.caixa.hackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HackatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackatonApplication.class, args);
		System.out.println("✅ Aplicação iniciada com sucesso!");
		System.out.println("Url base da aplicação: http://localhost:8080");
		System.out.println("Url da documentação swagger da aplicação: http://localhost:8080/swagger-ui/index.html");
		System.out.println("Url da collection postman da aplicação: http://localhost:8080/collection");
	}

}
