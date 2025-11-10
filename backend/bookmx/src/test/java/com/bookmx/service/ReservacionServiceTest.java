package com.bookmx.service;

import com.bookmx.api.dto.ReservacionRequest;
import com.bookmx.domain.Hotel;
import com.bookmx.domain.Reservacion;
import com.bookmx.repository.HotelRepository;
import com.bookmx.repository.ReservacionRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservacionServiceTest {

    @Mock HotelRepository hotelRepo;
    @Mock ReservacionRepository reservacionRepo;
    @InjectMocks ReservacionService service;

    @BeforeEach void setup(){ MockitoAnnotations.openMocks(this); }

    @Test
    void crear_ok() {
        var hotel = Hotel.builder().id(1L).nombre("MX").estrellas(4).precioPorNoche(1000).build();
        when(hotelRepo.findById(1L)).thenReturn(Optional.of(hotel));
        when(reservacionRepo.save(any(Reservacion.class))).thenAnswer(a -> a.getArgument(0));

        var req = new ReservacionRequest(1L,"Camila","cami@test.com",2,
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));

        var r = service.crear(req);

        assertEquals("Camila", r.getNombreHuesped());
        verify(reservacionRepo).save(any(Reservacion.class));
    }

    @Test
    void crear_falla_fechasInvalidas() {
        var req = new ReservacionRequest(1L,"X","x@x.com",1,
                LocalDate.now().plusDays(3), LocalDate.now().plusDays(2));
        assertThrows(ValidationException.class, () -> service.crear(req));
        verifyNoInteractions(reservacionRepo);
    }

    @Test
    void crear_falla_hotelNoExiste() {
        when(hotelRepo.findById(99L)).thenReturn(Optional.empty());
        var req = new ReservacionRequest(99L,"X","x@x.com",1,
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        assertThrows(IllegalArgumentException.class, () -> service.crear(req));
    }
}
