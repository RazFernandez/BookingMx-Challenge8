package com.bookmx.api;

import com.bookmx.api.dto.ReservacionRequest;
import com.bookmx.domain.Hotel;
import com.bookmx.domain.Reservacion;
import com.bookmx.repository.ReservacionRepository;
import com.bookmx.service.ReservacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservacionController.class)
class ReservacionControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean ReservacionService service;
    @MockBean ReservacionRepository repo;

    @Test
    void post_crear_201() throws Exception {
        var req = new ReservacionRequest(1L,"Camila","cami@test.com",2,
                LocalDate.of(2025,11,20), LocalDate.of(2025,11,22));
        var resp = Reservacion.builder()
                .id(10L).hotel(Hotel.builder().id(1L).build())
                .nombreHuesped("Camila").email("cami@test.com")
                .numPersonas(2).fechaInicio(req.fechaInicio()).fechaFin(req.fechaFin()).build();

        Mockito.when(service.crear(any())).thenReturn(resp);

        mvc.perform(post("/api/reservaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10));
    }

    @Test
    void get_listar_200() throws Exception {
        Mockito.when(repo.findAll()).thenReturn(List.of());
        mvc.perform(get("/api/reservaciones")).andExpect(status().isOk());
    }
}
