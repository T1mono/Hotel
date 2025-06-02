package ru.javadaddy.service;

import ru.javadaddy.model.Room;
import ru.javadaddy.repository.RoomRepository;

import java.time.LocalDate;

public class BookingService {
    private final RoomRepository roomRepository;

    public BookingService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        // TODO: Реализовать проверку доступности номера
        return false;
    }

    public boolean bookRoom(Room room, LocalDate checkIn, LocalDate checkOut) {
        // TODO: Реализовать логику бронирования номера
        return false;
    }

    public double calculatePrice(Room room, LocalDate checkIn, LocalDate checkOut) {
        // TODO: Реализовать расчет стоимости
        return 0.0;
    }
}
