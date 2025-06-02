package ru.javadaddy.repository;

import ru.javadaddy.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Optional<Room> findById(int roomId);

    List<Room> findAll();

    List<LocalDate> findDatesByRoomId(int roomId);

    void saveNewReservation(int roomId, List<LocalDate> dates);
}
