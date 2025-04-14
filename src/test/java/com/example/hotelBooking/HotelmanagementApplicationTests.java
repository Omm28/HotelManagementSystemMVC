package com.example.hotelBooking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.example.hotelBooking.config.SecurityConfig;

@SpringBootTest(classes = HotelmanagementApplication.class)
@Import(SecurityConfig.class)
@EnableWebSecurity
class HotelmanagementApplicationTests {

	@Test
	void contextLoads() {
	}
}