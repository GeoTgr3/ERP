package com.allianceever.projectERP.config;

import com.allianceever.projectERP.model.dto.EmployeeDto;
import com.allianceever.projectERP.model.entity.Employee;
import com.allianceever.projectERP.repository.EmployeeRepo;
import com.allianceever.projectERP.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    private ModelMapper mapper;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> user = Optional.ofNullable(employeeRepo.findByEmail(username));
        return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("HEY username not found"));
    }
}