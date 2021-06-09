package com.utn.parcial.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.parcial.AbstractController;
import com.utn.parcial.controllers.CumpleController;
import com.utn.parcial.controllers.PersonaController;
import com.utn.parcial.models.Factura;
import com.utn.parcial.services.CumpleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = CumpleController.class)
public class CumpleControllerTest extends AbstractController {
    @MockBean
    CumpleService cumpleService;
    List<Factura> facturas= List.of(new Factura("PEPE JEJE","EURO",24254),new Factura("PEPa JaJE","EURO",24254));

    @Test
    public void testFindFacturaFromCumpleHttp200() throws Exception {
        Mockito.when(cumpleService.findFacturaFromCumple(ArgumentMatchers.anyInt()))
                .thenReturn(facturas);

        final ResultActions resultActions=givenController().perform(MockMvcRequestBuilders
                .get("/cumples/{idCumple}/factura",1)
                .contentType(MediaType.APPLICATION_JSON));
        final MvcResult result=resultActions.andReturn();

        Assertions.assertEquals(200,result.getResponse().getStatus());
        Assertions.assertEquals(facturas,new ObjectMapper().readValue(result.getResponse().getContentAsString(),new TypeReference<List<Factura>>(){}));
    }

    @Test
    public void testFindFacturaFromCumpleHttp204() throws Exception {
        Mockito.when(cumpleService.findFacturaFromCumple(ArgumentMatchers.anyInt()))
                .thenReturn(Collections.emptyList());

        final ResultActions resultActions=givenController().perform(MockMvcRequestBuilders
                .get("/cumples/{idCumple}/factura",1)
                .contentType(MediaType.APPLICATION_JSON));
        final MvcResult result=resultActions.andReturn();

        Assertions.assertEquals(204,result.getResponse().getStatus());
        Assertions.assertEquals(Collections.emptyList(),new ObjectMapper().readValue(result.getResponse().getContentAsString(),new TypeReference<List<Factura>>(){}));
    }
}
