package com.utn.parcial.services;

import com.utn.parcial.exceptions.NotFoundException;
import com.utn.parcial.models.*;
import com.utn.parcial.models.Currency;
import com.utn.parcial.repositories.CumpleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CumpleServiceTest {
    final Float EURO_PRICE=150f;
    final Float CUMPLE_PRICE=25000f;

    @Mock
    CumpleRepository cumpleRepository;
    @Mock
    ApiService apiService;
    @InjectMocks
    CumpleService cumpleService;
    @Mock
    PersonaService personaService;
    CumpleService cumpleServiceSpy;
    Persona persona;
    Cumpleanitos cumple;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        persona=Jugador.builder().currency(new Currency(1,CurrencyType.EURO,200f)).build();
        cumple=Cumpleanitos.builder().invitados(new HashSet<>()).build();
        cumpleServiceSpy = spy(cumpleService);
    }

    @Test
    void testAddCumple(){
        when(cumpleRepository.save(any(Cumpleanitos.class))).thenReturn(cumple);

        cumpleService.addCumple(cumple);

        verify(cumpleRepository,times(1)).save(any(Cumpleanitos.class));
    }

    @Test
    void testFindByIdNotThrows(){
        when(cumpleRepository.findById(anyInt())).thenReturn(Optional.of(cumple));

        assertDoesNotThrow(() -> cumpleService.findById(1));

        verify(cumpleRepository,times(1)).findById(anyInt());
    }

    @Test
    void testFindByIdThrows(){
        when(cumpleRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,() -> cumpleService.findById(1));

        verify(cumpleRepository,times(1)).findById(anyInt());
    }

    @Test
    void testFindFacturaFromCumple() {
        when(apiService.getEuro()).thenReturn(EURO_PRICE);
        when(apiService.getDolar()).thenReturn(100f);
        cumple.setInvitados((Set.of(persona)));
        doReturn(cumple).when(cumpleServiceSpy).findById(anyInt());

        List<Factura> result = cumpleServiceSpy.findFacturaFromCumple(1);

        Assertions.assertEquals(CUMPLE_PRICE/EURO_PRICE, result.get(0).getAmount());
        verify(cumpleServiceSpy,times(1)).findById(anyInt());
    }

    @Test
    void testputCumplaniero(){
        doReturn(cumple).when(cumpleServiceSpy).findById(anyInt());
        when(personaService.findById(anyInt())).thenReturn(persona);
        when(cumpleRepository.save(any(Cumpleanitos.class))).thenReturn(cumple);

        cumpleServiceSpy.putCumpleaniero(1,1);

        verify(cumpleRepository,times(1)).save(any(Cumpleanitos.class));
    }

    @Test
    void testaddInvitado(){
        doReturn(cumple).when(cumpleServiceSpy).findById(anyInt());
        when(personaService.findById(anyInt())).thenReturn(persona);
        when(cumpleRepository.save(any(Cumpleanitos.class))).thenReturn(cumple);

        cumpleServiceSpy.addInvitado(1,1);

        verify(cumpleRepository,times(1)).save(any(Cumpleanitos.class));
    }
}
