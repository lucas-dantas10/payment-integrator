package br.com.payment_integrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentIntegratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentIntegratorApplication.class, args);
	}

}
