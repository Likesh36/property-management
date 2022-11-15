package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.CalculatorDTO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {
    @InjectMocks
    private CalculatorController calculatorController;

    /*Double num1;
    Double num2;
    Double num3;*/
    static Double num1;
    static Double num2;
    static Double num3;
    static Double num4;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all");
        num1 = 3.5;
        num2 = 3.5;
        num3 = 3.5;
        num4 = 3.5;

    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all");
        num1 = null;
        num2 = null;
        num3 = null;
        num4 = null;

    }

    /*@BeforeEach
    void init(){
        System.out.println("Before Each");
        num1=3.4;
        num2=4.5;
        num3=5.4;
    }
    @AfterEach
    void destroy(){
        System.out.println("After Each");
        num1=null;
        num2=null;
        num3=null;
    }*/
    @BeforeEach
    void init() {
        System.out.println("Before Each");
    }

    @AfterEach
    void destroy() {
        System.out.println("After Each");
    }

    @Test
    @DisplayName("Test addition success scenario")
    void testAddFunction_Success() {
        System.out.println("Test addition success scenario");
        Double result = calculatorController.add(num1, num2, num3);
        //expected 13.3
        //Always perform assertion
        //Assertions.assertEquals(10.5, result);
        assertEquals(10.5, result);
    }

    @Test
    //@Disabled
    @DisplayName("Test addition failure scenario")
    void testAddFunction_Failure() {
        System.out.println("Test addition failure scenario");
        Double result = calculatorController.add(num1 - 0.5, num2, num3);
        //deliberately gave wrong value expected 13.3
        //Always perform assertion
        Assertions.assertNotEquals(10.5, result);
    }

    @Test
    @DisplayName("Test subtraction for num1>num2 scenario")
    public void testSubFunction_num1_grt_num2() {
        Double result = calculatorController.subtract(num1 + 1, num2);
        Assertions.assertEquals(1.0, result);
    }

    @Test
    @DisplayName("Test subtraction for num1<num2 scenario")
    public void testSubFunction_num1_lst_num2() {
        Double result = calculatorController.subtract(num1, num2 + 1);
        Assertions.assertEquals(1.0, result);
    }

    @Test
    @DisplayName("Test multiplication")
    void testMultiply() {
        CalculatorDTO calculatorDTO = new CalculatorDTO();
        calculatorDTO.setNum1(num1);
        calculatorDTO.setNum2(num2);
        calculatorDTO.setNum3(num3);
        calculatorDTO.setNum4(3.5);
        //calculatorDTO.setNum4(num4);

        ResponseEntity<Double>responseEntity=calculatorController.multiply(calculatorDTO);
        assertEquals(150.0625,responseEntity.getBody());
        assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCodeValue(),"Expecting the status as CREATED");
    }
}
