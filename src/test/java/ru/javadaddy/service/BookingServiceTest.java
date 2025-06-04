package ru.javadaddy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javadaddy.model.Room;
import ru.javadaddy.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private BookingService bookingService;

    private Room room;

    private List<LocalDate> dateList;

    @BeforeEach
    void setUp() {
        room = new Room(13, "Lux", 15_000);
        dateList = List.of(
                LocalDate.of(2025, 6, 15),
                LocalDate.of(2025, 6, 20)
        );
    }

    @Test
    void testIsRoomAvailableSuccess() {
        when(roomRepository.findDatesByRoomId(room.id())).thenReturn(dateList);

        boolean result = bookingService.isRoomAvailable(room, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 14));
        assertTrue(result, "Номера должны быть свободны в указанные даты");
        verify(roomRepository, times(1)).findDatesByRoomId(13);
    }

    @Test
    void testIsRoomAvailableFiled() {
        when(roomRepository.findDatesByRoomId(room.id())).thenReturn(dateList);

        boolean result = bookingService.isRoomAvailable(room, LocalDate.of(2025, 6, 15), LocalDate.of(2025, 6, 19));
        assertFalse(result, "Номера должны быть заняты в указанные даты");
        verify(roomRepository, times(1)).findDatesByRoomId(13);
    }

    @Test
    void testBookRoomSuccess() {
        when(roomRepository.findDatesByRoomId(room.id())).thenReturn(dateList);
        boolean result = bookingService.bookRoom(room, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 14));
        assertTrue(result, "Номера должны забронироваться в указанные даты");
        verify(roomRepository, times(1)).findDatesByRoomId(13);
    }

    @Test
    void testBookRoomFailed() {
        when(roomRepository.findDatesByRoomId(room.id())).thenReturn(dateList);
        boolean result = bookingService.bookRoom(room, LocalDate.of(2025, 6, 14), LocalDate.of(2025, 6, 18));
        assertFalse(result, "Номера не смогут забронироваться в указанные даты");
        verify(roomRepository, times(1)).findDatesByRoomId(13);
    }

    @Test
    void calculatePrice() {
        double price = bookingService.calculatePrice(room, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 14));
         assertEquals(60_000, price);
    }
}