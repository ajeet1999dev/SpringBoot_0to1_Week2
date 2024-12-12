package com.springboot0to1.Department_Week2.controller;

import com.springboot0to1.Department_Week2.dto.DepartmentDTO;
import com.springboot0to1.Department_Week2.exception.ResourceNotFoundException;
import com.springboot0to1.Department_Week2.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartmentDTO){
        return new ResponseEntity<>(departmentService.createNewDepartment(inputDepartmentDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){

        return ResponseEntity.ok(departmentService.detAllDepartments());


    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<Optional<DepartmentDTO>> getDepartmentById(@PathVariable Long departmentId){

        Optional<DepartmentDTO> departmentDTO= departmentService.getDepartmentById(departmentId);

//        if(departmentDTO.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok(departmentDTO);

        return departmentDTO.map(departmentDTO1 -> ResponseEntity.ok(departmentDTO)).orElseThrow(() -> new ResourceNotFoundException("Department not found with id "+departmentId));

    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@PathVariable Long departmentId, @RequestBody @Valid DepartmentDTO inputDepartmentDTO){

        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentId,inputDepartmentDTO));
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentPartiallyById(@PathVariable Long departmentId,@RequestBody Map<String,Object> updates){

        return ResponseEntity.ok(departmentService.updateDepartmentPartiallyById(departmentId,updates));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable long departmentId){

        Boolean gotDeleted = departmentService.deleteDepartmentById(departmentId);
        if(gotDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
