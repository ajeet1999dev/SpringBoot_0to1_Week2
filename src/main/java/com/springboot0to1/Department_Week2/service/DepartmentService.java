package com.springboot0to1.Department_Week2.service;

import com.springboot0to1.Department_Week2.dto.DepartmentDTO;
import com.springboot0to1.Department_Week2.enitity.DepartmentEntity;
import com.springboot0to1.Department_Week2.exception.ResourceNotFoundException;
import com.springboot0to1.Department_Week2.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartmentDTO) {

        DepartmentEntity departmentEntity = modelMapper.map(inputDepartmentDTO,DepartmentEntity.class);

        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }

    public List<DepartmentDTO> detAllDepartments() {

        List<DepartmentEntity> departmentEntity = departmentRepository.findAll();

        return departmentEntity
                .stream()
                .map(departmentEntity1 -> modelMapper.map(departmentEntity1,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);

        return departmentEntity
                .map(departmentEntity1 -> modelMapper.map(departmentEntity1,DepartmentDTO.class));
    }

    public Boolean isExists(Long id){
        Boolean existsOrNot = departmentRepository.existsById(id);

        if(!existsOrNot) throw new ResourceNotFoundException("Department not found with id "+id);
        return true;
    }

    public DepartmentDTO updateDepartmentById(Long departmentId, DepartmentDTO inputDepartmentDTO) {

         isExists(departmentId);
//        if(!isExists(departmentId))
//            return null;
        DepartmentEntity departmentEntity = modelMapper.map(inputDepartmentDTO,DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentPartiallyById(Long departmentId, Map<String, Object> updates) {

        isExists(departmentId);
//        if(!isExists(departmentId))
//            return null;
        DepartmentEntity toSaveDepartmentEntity = departmentRepository.findById(departmentId).orElse(null);

        updates.forEach((field,value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,toSaveDepartmentEntity,value);
        });

        return modelMapper.map(departmentRepository.save(toSaveDepartmentEntity),DepartmentDTO.class);

    }

    public Boolean deleteDepartmentById(long departmentId) {

        isExists(departmentId);

        departmentRepository.deleteById(departmentId);
        return true;
    }
}
