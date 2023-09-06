package com.allianceever.projectERP.controller;

import com.allianceever.projectERP.model.dto.DepartmentDto;
import com.allianceever.projectERP.model.dto.HolidayDto;
import com.allianceever.projectERP.service.DepartmentService;
import com.allianceever.projectERP.service.HolidayService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.allianceever.projectERP.controller.EmployeeController.getStrings;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ModelAndView createDepartment(@ModelAttribute() DepartmentDto departmentDto){
        DepartmentDto createdDepartment = departmentService.create(departmentDto);
        return new ModelAndView("redirect:/departments.html");
    }





    // Build Delete Employee REST API
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")  Long id){
        DepartmentDto departmentDto = departmentService.getById(id);

        departmentService.delete(id);
        return ResponseEntity.ok("Holiday deleted successfully!");
    }


}
