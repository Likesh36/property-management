package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.UserDTO;

import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity convertDTOtoEntity(UserDTO userDTO) {
        UserEntity ue = new UserEntity();
        ue.setOwnerEmail(userDTO.getOwnerEmail());
        ue.setOwnerName(userDTO.getOwnerName());
        ue.setPassword(userDTO.getPassword());
        ue.setPhone(userDTO.getPhone());
        return ue;
    }

    public UserDTO convertEntitytoDTO(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setOwnerEmail(userEntity.getOwnerEmail());
        dto.setOwnerName(userEntity.getOwnerName());
        dto.setPhone(userEntity.getPhone());
        return dto;
    }
}
