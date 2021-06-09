package com.utn.parcial.services;

import com.utn.parcial.models.Cumpleanitos;
import com.utn.parcial.models.Factura;
import com.utn.parcial.repositories.CumpleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class CumpleServiceTest {
    @Mock
    CumpleRepository cumpleRepository;
    @Mock
    ApiService apiService;
    @InjectMocks
    CumpleService cumpleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testFindFacturaFromCumple() {
        when(apiService.getEuro()).thenReturn(150f);
        when(apiService.getDolar()).thenReturn(100f);
        CumpleService cumpleServiceSpy=spy(CumpleService.class);
        when(cumpleServiceSpy.findById(1)).thenReturn(Cumpleanitos.builder().invitados(Collections.emptySet()).build());

        List<Factura> result = cumpleServiceSpy.findFacturaFromCumple(1);

        Assertions.assertEquals(Collections.emptyList(),result);
    }
}
