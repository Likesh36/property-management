package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {
    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO) {
        PropertyEntity pe = new PropertyEntity();
        pe.setTitle(propertyDTO.getTitle());
        pe.setDescription(propertyDTO.getDescription());
        pe.setPrice(propertyDTO.getPrice());
        pe.setAddress(propertyDTO.getAddress());
        return pe;
    }

    public PropertyDTO convertEntitytoDTO(PropertyEntity propertyEntity) {
        PropertyDTO dto = new PropertyDTO();
        dto.setId(propertyEntity.getId());
        dto.setTitle(propertyEntity.getTitle());
        dto.setDescription(propertyEntity.getDescription());
        dto.setPrice(propertyEntity.getPrice());
        dto.setAddress(propertyEntity.getAddress());
        return dto;
    }
}
