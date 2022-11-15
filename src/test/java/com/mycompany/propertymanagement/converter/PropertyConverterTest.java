package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PropertyConverterTest {
    @InjectMocks
    private PropertyConverter propertyConverter;
    @Test
    @DisplayName("Test class for converting dto to entity in success scenario")
    void convertDTOtoEntity_Success(){
        PropertyDTO dto = new PropertyDTO();
        dto.setTitle("Dummy");
        dto.setPrice(12339.54);
        PropertyEntity propertyEntity=propertyConverter.convertDTOtoEntity(dto);
        Assertions.assertEquals(dto.getPrice(),propertyEntity.getPrice());
        Assertions.assertEquals(dto.getTitle(),propertyEntity.getTitle());

    }
    @Test
    @DisplayName("Test class for converting entity to dto in success scenario")
    void convertEntityToDTO_Success(){
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle("Dummy");
        propertyEntity.setPrice(12339.54);
        PropertyDTO dto=propertyConverter.convertEntitytoDTO(propertyEntity);
        Assertions.assertEquals(propertyEntity.getPrice(),dto.getPrice());
        Assertions.assertEquals(propertyEntity.getTitle(),dto.getTitle());

    }
}
