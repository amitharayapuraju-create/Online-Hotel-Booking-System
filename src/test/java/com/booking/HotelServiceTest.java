package com.booking;

import com.booking.dao.HotelDAO;
import com.booking.model.Hotel;
import com.booking.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelServiceTest {

    private HotelService hotelService;
    private HotelDAO hotelDAO;

    @BeforeEach
    void setUp() throws Exception {

        hotelService = new HotelService();

        hotelDAO = mock(HotelDAO.class);

        Field field = HotelService.class.getDeclaredField("hotelDAO");
        field.setAccessible(true);
        field.set(hotelService, hotelDAO);
    }

    // ================= CREATE =================

    @Test
    void testCreateHotel() {

        Hotel hotel = new Hotel();

        when(hotelDAO.createHotel(hotel)).thenReturn(1L);

        Long result = hotelService.createHotel(hotel);

        assertNotNull(result);
        assertEquals(1L, result);

        verify(hotelDAO, times(1)).createHotel(hotel);
    }

    @Test
    void testCreateHotelWithNull() {

        Long result = hotelService.createHotel(null);

        assertNull(result);

        verify(hotelDAO, never()).createHotel(any());
    }

    @Test
    void testCreateHotelFailure() {

        Hotel hotel = new Hotel();

        when(hotelDAO.createHotel(hotel)).thenReturn(null);

        Long result = hotelService.createHotel(hotel);

        assertNull(result);

        verify(hotelDAO, times(1)).createHotel(hotel);
    }

    // ================= READ BY ID =================

    @Test
    void testGetHotelById() {

        Hotel hotel = new Hotel();

        when(hotelDAO.getHotelById(1L)).thenReturn(hotel);

        Hotel result = hotelService.getHotelById(1L);

        assertNotNull(result);
        assertEquals(hotel, result);

        verify(hotelDAO, times(1)).getHotelById(1L);
    }

    @Test
    void testGetHotelByInvalidId() {

        Hotel result = hotelService.getHotelById(0L);

        assertNull(result);

        verify(hotelDAO, never()).getHotelById(any());
    }

    @Test
    void testGetHotelByIdNotFound() {

        when(hotelDAO.getHotelById(999L)).thenReturn(null);

        Hotel result = hotelService.getHotelById(999L);

        assertNull(result);

        verify(hotelDAO, times(1)).getHotelById(999L);
    }

    // ================= READ ALL =================

    @Test
    void testGetAllHotels() {

        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();

        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);

        when(hotelDAO.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelService.getAllHotels();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(hotelDAO, times(1)).getAllHotels();
    }

    // ================= UPDATE =================

    @Test
    void testUpdateHotel() {

        Hotel hotel = new Hotel();
        hotel.setHotelId(1L);

        when(hotelDAO.updateHotel(hotel)).thenReturn(true);

        boolean result = hotelService.updateHotel(hotel);

        assertTrue(result);

        verify(hotelDAO, times(1)).updateHotel(hotel);
    }

    @Test
    void testUpdateHotelWithNull() {

        boolean result = hotelService.updateHotel(null);

        assertFalse(result);

        verify(hotelDAO, never()).updateHotel(any());
    }

    @Test
    void testUpdateHotelWithInvalidId() {

        Hotel hotel = new Hotel();
        hotel.setHotelId(0L);

        boolean result = hotelService.updateHotel(hotel);

        assertFalse(result);

        verify(hotelDAO, never()).updateHotel(any());
    }

    @Test
    void testUpdateHotelFailure() {

        Hotel hotel = new Hotel();
        hotel.setHotelId(1L);

        when(hotelDAO.updateHotel(hotel)).thenReturn(false);

        boolean result = hotelService.updateHotel(hotel);

        assertFalse(result);

        verify(hotelDAO, times(1)).updateHotel(hotel);
    }

    // ================= DELETE =================

    @Test
    void testDeleteHotel() {

        when(hotelDAO.deleteHotel(1L)).thenReturn(true);

        boolean result = hotelService.deleteHotel(1L);

        assertTrue(result);

        verify(hotelDAO, times(1)).deleteHotel(1L);
    }

    @Test
    void testDeleteHotelWithInvalidId() {

        boolean result = hotelService.deleteHotel(0L);

        assertFalse(result);

        verify(hotelDAO, never()).deleteHotel(any());
    }

    @Test
    void testDeleteHotelFailure() {

        when(hotelDAO.deleteHotel(1L)).thenReturn(false);

        boolean result = hotelService.deleteHotel(1L);

        assertFalse(result);

        verify(hotelDAO, times(1)).deleteHotel(1L);
    }
}