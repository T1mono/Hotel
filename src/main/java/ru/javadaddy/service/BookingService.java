package ru.javadaddy.service;

import ru.javadaddy.model.Room;
import ru.javadaddy.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

public class BookingService {
    private final RoomRepository roomRepository;

    public BookingService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        // TODO: Реализовать проверку доступности номера
        List<LocalDate> datesByRoomId = roomRepository.findDatesByRoomId(room.id());

        boolean isAvailable = true;

        if (checkIn == null) {
            throw  new IllegalArgumentException("checkIn не может быть null");
        }
        if (checkOut == null) {
            throw new IllegalArgumentException("checkOut не может быть null");
        }

        if (datesByRoomId.isEmpty()) {
            return isAvailable;
        }

        if (checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("checkIn должен быть раньше checkOut");
        }

        for(LocalDate localDate : datesByRoomId) {
            if (localDate.isBefore(checkIn) && localDate.isBefore(checkOut)){
                return !isAvailable;
            }

//            if (localDate.isBefore(checkIn) )
        }

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
