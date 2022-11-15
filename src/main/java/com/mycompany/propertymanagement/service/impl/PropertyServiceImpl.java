package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Configuration
//@Component
//@Repository
//@Controller
@Service
public class PropertyServiceImpl implements PropertyService {

    @Value("${pms.dummy:}")
    private String dummy;

    @Value("${spring.datasource.url:}")
    private String dbUrl;

    @Value("${spring.datasource.username:}")
    private String userName;

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
       Optional<UserEntity>optUe= userRepository.findById(propertyDTO.getUserId());
       if (optUe.isPresent()) {
           PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
           pe.setUserEntity(optUe.get());
           pe = propertyRepository.save(pe);
           propertyDTO = propertyConverter.convertEntitytoDTO(pe);
       }else{
           List<ErrorModel>errorModelList=new ArrayList<>();
           ErrorModel errorModel=new ErrorModel();
           errorModel.setCode("USER_ID_NOT_EXIST");
           errorModel.setMessage("User does not exist");
           errorModelList.add(errorModel);
           throw new BusinessException(errorModelList);
       }
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        System.out.println("Inside Service " + dummy);
        System.out.println("Inside Service " + dbUrl);
        System.out.println("Inside Service " + userName);

        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyList = new ArrayList<>();
        for (PropertyEntity pe : propertyEntityList) {
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            propertyList.add(dto);
        }
        return propertyList;

    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propertyList = new ArrayList<>();
        for (PropertyEntity pe : propertyEntityList) {
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            propertyList.add(dto);
        }
        return propertyList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO dto = null;
        Optional<PropertyEntity> optEnt = propertyRepository.findById(propertyId);
        if (optEnt.isPresent()) {
            PropertyEntity pe = optEnt.get();//data from database
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());
            pe.setAddress(propertyDTO.getAddress());
            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO dto = null;
        Optional<PropertyEntity> optEnt = propertyRepository.findById(propertyId);
        if (optEnt.isPresent()) {
            PropertyEntity pe = optEnt.get();//data from database
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO dto = null;
        Optional<PropertyEntity> optEnt = propertyRepository.findById(propertyId);
        if (optEnt.isPresent()) {
            PropertyEntity pe = optEnt.get();//data from database
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
