package com.utn.parcial.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.parcial.AbstractController;
import com.utn.parcial.controllers.CumpleController;
import com.utn.parcial.models.Cumpleanitos;
import com.utn.parcial.models.Factura;
import com.utn.parcial.services.CumpleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CumpleController.class)
public class CumpleControllerTest extends AbstractController {
    @MockBean
    CumpleService cumpleService;
    List<Factura> facturas;
    Cumpleanitos cumpleanitos;
    ObjectMapper objectMapper;

    @BeforeAll
    void setUp(){
        facturas=List.of(new Factura("PEPE JEJE","EURO",24254),new Factura("PEPa JaJE","EURO",24254));
        cumpleanitos=Cumpleanitos.builder().id(1).build();
        objectMapper=new ObjectMapper();
    }

    @Test
    public void testAddCumple() throws Exception {
        when(cumpleService.addCumple(any(Cumpleanitos.class))).thenReturn(cumpleanitos);

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                .post("/cumples",1)
                .content(objectMapper.writeValueAsString(cumpleanitos))
                .contentType(MediaType.APPLICATION_JSON));
        final MvcResult result=resultActions.andReturn();

        assertEquals(201,result.getResponse().getStatus());
    }

    @Test
    public void testFindFacturaFromCumpleHttp200() throws Exception {
        when(cumpleService.findFacturaFromCumple(anyInt()))
                .thenReturn(facturas);

        final ResultActions resultActions=givenController().perform(MockMvcRequestBuilders
                .get("/cumples/{idCumple}/factura",1)
                .contentType(MediaType.APPLICATION_JSON));
        final MvcResult result=resultActions.andReturn();

        assertEquals(200,result.getResponse().getStatus());
        assertEquals(facturas,new ObjectMapper().readValue(result.getResponse().getContentAsString(),new TypeReference<List<Factura>>(){}));
    }

    @Test
    public void testFindFacturaFromCumpleHttp204() throws Exception {
        when(cumpleService.findFacturaFromCumple(anyInt()))
                .thenReturn(Collections.emptyList());

        final ResultActions resultActions=givenController().perform(MockMvcRequestBuilders
                .get("/cumples/{idCumple}/factura",1)
                .contentType(MediaType.APPLICATION_JSON));
        final MvcResult result=resultActions.andReturn();

        assertEquals(204,result.getResponse().getStatus());
        assertEquals(Collections.emptyList(),objectMapper.readValue(result.getResponse().getContentAsString(),new TypeReference<List<Factura>>(){}));
    }

    @Test
    public void testaddInvitado() throws Exception {
        when(cumpleService.addInvitado(anyInt(),anyInt()))
                .thenReturn(cumpleanitos);

        final ResultActions resultActions=givenController().perform(MockMvcRequestBuilders
                .put("/cumples/{idCumple}/invitados/{idInvitado}", 1,1)
                .contentType(MediaType.APPLICATION_JSON));
        final MvcResult result=resultActions.andReturn();

        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void testputCumpleaniero() throws Exception {
        when(cumpleService.putCumpleaniero(anyInt(),anyInt()))
                .thenReturn(cumpleanitos);

        final ResultActions resultActions=givenController().perform(MockMvcRequestBuilders
                .put("/cumples/{idCumple}/cumplaniero/{idPersona}", 1,1)
                .contentType(MediaType.APPLICATION_JSON));
        final MvcResult result=resultActions.andReturn();

        assertEquals(200,result.getResponse().getStatus());
    }
}
