package com.example.hotelBooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_booking")
public class RoomBooking {

    @Id
    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "hotel_id")
    private Long hotelId;

    private String username;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    private String status;

    @Transient
    private Room room;

    public void setRoom(Room room) {
        this.room = room;
        if (room != null) {
            this.roomId = room.getId();
            if (room.getHotel() != null) {
                this.hotelId = room.getHotel().getId();
            }
        }
    }
}
