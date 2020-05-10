package com.wildadventures.msreservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.wildadventures.msreservations.proxies")
public class MsReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReservationsApplication.class, args);
	}

}
