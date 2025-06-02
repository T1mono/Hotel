package ru.javadaddy.repository;

import ru.javadaddy.model.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {
    //TODO: реализовать хранение состояния комнат и бронирований
    //TODO: реализовать инициализацию состояния

    private final List<Room> roomStorage = new ArrayList<>();
    private final Map<Integer, List<LocalDate>> bookingsByRoomId = new HashMap<>();

    @Override
    public Optional<Room> findById(int roomId) {
        //TODO: реализовать логику поиска комнаты по идентификатору
        if (roomId <= 0) {
            throw new IllegalArgumentException("roomId должен быть больше 0");
        }

        return Optional.ofNullable(roomStorage.get(roomId));
    }

    @Override
    public List<Room> findAll() {
        //TODO: реализовать логику поиска всех комнат
        return List.copyOf(roomStorage);
    }

    @Override
    public List<LocalDate> findDatesByRoomId(int roomId) {
        //TODO: реализовать логику поиска бронирований по roomId
        if (roomId <= 0) {
            throw new IllegalArgumentException("roomId должен быть больше 0");
        }

        return bookingsByRoomId.getOrDefault(roomId, List.of());
    }

    @Override
    public void saveNewReservation(int roomId, List<LocalDate> dates) {
        //TODO: реализовать логику создания новых бронирований
    }
}
