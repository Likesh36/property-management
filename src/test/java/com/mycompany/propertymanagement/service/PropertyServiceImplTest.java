package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {
    @InjectMocks
    private PropertyServiceImpl propertyServiceImpl;
    @Mock
    private PropertyConverter propertyConverter;
    @Mock
    private PropertyRepository propertyRepository;

    @Test
    @DisplayName("Test for saving property in success scenario")
    void TestSaveProperty_Success() {
        PropertyDTO dto = new PropertyDTO();
        dto.setTitle("Dummy");
        dto.setDescription("Dummy Description");
        dto.setPrice(45549.63);

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Dummy");
        propertyEntity.setDescription("Dummy Description");
        propertyEntity.setPrice(45549.63);

        PropertyEntity savedEntity = new PropertyEntity();
        savedEntity.setTitle("Dummy");
        savedEntity.setDescription("Dummy Description");
        savedEntity.setPrice(45549.63);
        savedEntity.setId(1L);

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Dummy");
        savedDTO.setDescription("Dummy Description");
        savedDTO.setPrice(45549.63);
        savedDTO.setId(1L);


        Mockito.when(propertyConverter.convertDTOtoEntity(Mockito.any())).thenReturn(propertyEntity);
        Mockito.when(propertyRepository.save(Mockito.any())).thenReturn(savedEntity);
        Mockito.when(propertyConverter.convertEntitytoDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO result = propertyServiceImpl.saveProperty(dto);
        Assertions.assertEquals(savedDTO.getId(), result.getId());
    }

    @Test
    @DisplayName("Test for fetching property list in success scenario")
    void testAllProperties() {
        List<PropertyEntity> propertyEntities = new ArrayList<>();
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(1L);
        propertyEntity.setTitle("Dummy");
        propertyEntities.add(propertyEntity);

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Dummy");
        savedDTO.setDescription("Dummy Description");
        savedDTO.setPrice(45549.63);
        savedDTO.setId(1L);

        Mockito.when(propertyRepository.findAll()).thenReturn(propertyEntities);
        Mockito.when(propertyConverter.convertEntitytoDTO(Mockito.any())).thenReturn(savedDTO);
        List<PropertyDTO> propertyDTOList = propertyServiceImpl.getAllProperties();
        Assertions.assertEquals(1, propertyDTOList.size());

    }

    @Test
    @DisplayName("Test for updating property in success scenario")
    void TestUpdateProperty_Success() {

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setTitle("Dummy");
        savedDTO.setDescription("Dummy Description");
        savedDTO.setPrice(45549.63);
        savedDTO.setId(1L);
        savedDTO.setAddress("Country");

        PropertyEntity pe = new PropertyEntity();
        pe.setId(1L);
        pe.setTitle("Dummy");
        pe.setDescription("Dummy Description");
        pe.setPrice(45549.63);
        pe.setAddress("Country");

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntitytoDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO updatedProperty = propertyServiceImpl.updateProperty(savedDTO, 1L);

        Assertions.assertEquals(savedDTO.getTitle(), updatedProperty.getTitle());
        Assertions.assertEquals(savedDTO.getPrice(), updatedProperty.getPrice());
        Assertions.assertEquals(savedDTO.getAddress(), updatedProperty.getAddress());
        Assertions.assertEquals(savedDTO.getDescription(), updatedProperty.getDescription());

    }

    @Test
    @DisplayName("Test for updating property description in success scenario")
    void TestUpdatePropertyDescription_Success() {

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setDescription("Dummy updated Description");
        savedDTO.setPrice(45549.63);
        savedDTO.setId(1L);
        savedDTO.setAddress("Country");
        savedDTO.setTitle("Dummy");

        PropertyEntity pe = new PropertyEntity();
        pe.setId(1L);
        pe.setTitle("Dummy");
        pe.setDescription("Dummy Description");
        pe.setPrice(45549.63);
        pe.setAddress("Country");

        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntitytoDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO updatedProperty = propertyServiceImpl.updatePropertyDescription(savedDTO, 1L);
        Assertions.assertEquals(savedDTO.getDescription(), updatedProperty.getDescription());
    }
    @Test
    @DisplayName("Test for updating property description in failure scenario")
    void TestUpdatePropertyDescription_Failure() {

        PropertyDTO savedDTO = new PropertyDTO();
        when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        BusinessException exception = Assertions.assertThrows(BusinessException.class,()->{
            PropertyDTO updatedProperty = propertyServiceImpl.updatePropertyDescription(savedDTO, 1L);
        });

        Assertions.assertEquals(exception,exception.getMessage());
    }
}
