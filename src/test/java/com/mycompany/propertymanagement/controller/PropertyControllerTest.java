package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {
    @InjectMocks//Mockito is going to create a proxy/dummy object of our propertyController and inject it our property controller test file
    private PropertyController propertyController;
    @Mock//Mockito will give memory to PropertyService,and it will inject this dummy/proxy PropertyService object inside the proxy/dummy object of PropertyController
    private PropertyService propertyService;
    @Test
    @DisplayName("Test Success Scenario for saving property")
    void testSaveProperty(){
        PropertyDTO dto =new PropertyDTO();
        dto.setTitle("Dummy Property");
        dto.setAddress("Dummy address");
        dto.setDescription("Dummy description");
        dto.setPrice(dto.getPrice());
        PropertyDTO savedProperty=new PropertyDTO();
        savedProperty.setId(1L);
        savedProperty.setTitle(dto.getTitle());
        //Do not make the actual call for propertyService.saveProperty(dto) inside the controller rather than return the dummy object savedProperty
        Mockito.when(propertyService.saveProperty(dto)).thenReturn(savedProperty);
        ResponseEntity<PropertyDTO>responseEntity= propertyController.saveProperty(dto);
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCodeValue());
    }
    @Test
    @DisplayName("Test Success Scenario for fetching all properties")
    void testGetAllProperties(){
        List<PropertyDTO> propertyList = new ArrayList<>();
        PropertyDTO dto = new PropertyDTO();
        dto.setId(1L);
        dto.setTitle("Dummy Property");
        propertyList.add(dto);
        //Do not make the actual call for propertyService.getAllProperties() inside the controller rather than return the dummy object List<PropertyDTO>propertyList from the controller
        Mockito.when(propertyService.getAllProperties()).thenReturn(propertyList);
        ResponseEntity<List<PropertyDTO>>responseEntity = propertyController.getAllProperties();
        assertEquals(1,responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
    }
    @Test
    @DisplayName("Test Success Scenario for only updating property price")
    void testUpdatePropertyPrice(){
        PropertyDTO dto = new PropertyDTO();
        dto.setPrice(54549.54);
        Mockito.when(propertyService.updatePropertyPrice(Mockito.any(),Mockito.anyLong())).thenReturn(dto);
        ResponseEntity<PropertyDTO> responseEntity=propertyController.updatePropertyPrice(dto,1L);
        assertEquals(54549.54,responseEntity.getBody().getPrice());
        assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());

    }
}
