package ru.javadaddy.service;

import ru.javadaddy.model.Room;
import ru.javadaddy.repository.RoomRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final RoomRepository roomRepository;

    public BookingService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        // TODO: Реализовать проверку доступности номера
        List<LocalDate> datesByRoomId = roomRepository.findDatesByRoomId(room.id());

        if (checkIn == null) {
            throw new IllegalArgumentException("checkIn не может быть null");
        }
        if (checkOut == null) {
            throw new IllegalArgumentException("checkOut не может быть null");
        }

        if (checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("checkIn должен быть раньше checkOut");
        }

        if (datesByRoomId.isEmpty()) {
            return true;
        }

        for (LocalDate localDate : datesByRoomId) {
            if (!localDate.isBefore(checkIn) && !localDate.isAfter(checkOut)) {
                return false;
            }
        }

        return true;
    }

    //TODO: 1. Проверить даты бронирования и если они свободны забронируй
    public boolean bookRoom(Room room, LocalDate checkIn, LocalDate checkOut) {
        // TODO: Реализовать логику бронирования номера
        if (!isRoomAvailable(room, checkIn, checkOut)) {
            return false;
        }

        List<LocalDate> datesByRoomId = roomRepository.findDatesByRoomId(room.id());
        List<LocalDate> updateBookedDates = new ArrayList<>(datesByRoomId);
        updateBookedDates.addAll(datesByRoomId);

        LocalDate date = checkIn;
        while (date.isBefore(checkOut)) {
            updateBookedDates.add(date);
            date = date.plusDays(1);
        }

        roomRepository.saveNewReservation(room.id(), updateBookedDates);
        return true;
    }

    public double calculatePrice(Room room, LocalDate checkIn, LocalDate checkOut) {
        // TODO: Реализовать расчет стоимости

        if (room == null || checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("room, checkIn, checkOut не могут быть null");
        }

        if (checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("Дата заезда не может быть позже даты выезда");
        }

        //Получаем цену за номер
        double price = room.pricePerNight();

        //Разница дней между checkIn и checkOut
        long night = ChronoUnit.DAYS.between(checkIn, checkOut);

        if (night < 1) {
            throw new IllegalArgumentException("Минимальное бронирование - 1 ночь");
        }

        return price * (double) night;
    }
}
