package com.example.hotelmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hotelBooking.HotelmanagementApplication;

@SpringBootTest(classes = HotelmanagementApplication.class) // 👈 Add this!
class HotelmanagementApplicationTests {

	@Test
	void contextLoads() {
	}
}
