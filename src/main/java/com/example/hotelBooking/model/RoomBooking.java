package com.example.hotelBooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBooking {

    @Id
    private String reservationId;

    private Long hotelId;
    private Long roomId;
    private String username;

    private String checkInDate;
    private String checkOutDate;
}
