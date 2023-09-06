package com.allianceever.projectERP.service;


import com.allianceever.projectERP.model.dto.DepartmentDto;
import com.allianceever.projectERP.model.dto.HolidayDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {





    DepartmentDto create(DepartmentDto departmentDto);



    DepartmentDto getById(Long id);


    List<DepartmentDto> getAll();

    void delete(Long Id);
}
