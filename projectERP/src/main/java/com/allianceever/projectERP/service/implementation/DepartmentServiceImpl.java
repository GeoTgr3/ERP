package com.allianceever.projectERP.service.implementation;

import com.allianceever.projectERP.exception.ResourceNotFoundException;
import com.allianceever.projectERP.model.dto.DepartmentDto;
import com.allianceever.projectERP.model.dto.HolidayDto;
import com.allianceever.projectERP.model.entity.Department;
import com.allianceever.projectERP.model.entity.Holiday;
import com.allianceever.projectERP.repository.DepartmentRepo;
import com.allianceever.projectERP.service.DepartmentService;
import com.allianceever.projectERP.service.HolidayService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {



    private DepartmentRepo departmentRepo;

    private ModelMapper mapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo, ModelMapper mapper) {
        this.departmentRepo = departmentRepo;
        this.mapper = mapper;
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        // convert DTO to entity
        Department department = mapper.map(departmentDto, Department.class);
        Department newDepartment = departmentRepo.save(department);

        // convert entity to DTO
        return mapper.map(newDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getById(Long id) {
        Optional<Department> department = departmentRepo.findById(id);
        return mapper.map(department,DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAll() {
        List<Department> departments = departmentRepo.findAll();

        return departments.stream()
                .map(department -> mapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional

    public void delete(Long Id) {
        Optional<Optional<Department>> departmentOptional = Optional.ofNullable(departmentRepo.findById(Id));

        if (departmentOptional.isPresent()) {
            Optional<Department> department = departmentOptional.get();
            departmentRepo.deleteById(Id);
        } else {
            throw new ResourceNotFoundException("Department is not exist with given Id: " + Id);
        }
    }

}
