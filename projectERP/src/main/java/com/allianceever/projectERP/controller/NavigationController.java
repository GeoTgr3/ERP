package com.allianceever.projectERP.controller;

import com.allianceever.projectERP.model.dto.EmployeeDto;
import com.allianceever.projectERP.model.dto.EstimatesInvoicesDto;
import com.allianceever.projectERP.model.dto.LeavesDto;
import com.allianceever.projectERP.model.entity.*;
import com.allianceever.projectERP.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor

public class NavigationController {
    private EmployeeService employeeService;
    private HolidayService holidayService;
    private LeavesService leavesService;
    private LeaveTypeService leaveTypeService;
    private EstimatesInvoicesService estimatesInvoicesService;
    private PaymentService paymentService;
    private ExpensesService expensesService;
    private DepartmentService departmentService;



    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/index.html")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/login.html")
        public String login(Model model,EmployeeDto employeeDto){
        model.addAttribute("employee", employeeDto);

        return "login";
        }


    //
    @PreAuthorize("hasAuthority('management')")

    @GetMapping("/employees.html")
    public String ListEmployees(Model model){
        model.addAttribute("employees", employeeService.getAll());

        model.addAttribute("departments", departmentService.getAll());

        return "employees";
    }



    @RequestMapping("/profile.html")
    public String Profile(){
        return "profile";
    }

    @GetMapping("/profile.html/{id}")
    public String getProfile(@PathVariable("id") Long employeeID, Model model){
        model.addAttribute("employee", employeeService.getById(employeeID));
        return "profile";
    }




    @GetMapping("/holidays.html")
    public String ListHolidays(Model model){
        model.addAttribute("holidays", holidayService.getAllHolidaysOrderedByDate());
        return "holidays";
    }


    @GetMapping("/leave-type.html")
    @PreAuthorize("hasAuthority('management')")

    public String ListLeaveTypes(Model model){
        model.addAttribute("leaveTypes", leaveTypeService.getAllLeaveType());
        return "leave-type";
    }

    @PreAuthorize("hasAuthority('management') or (hasAuthority('gestion') or hasAuthority('finance'))")

    @GetMapping("/leaves-employee.html")
        public String ListLeaves(Model model){

        model.addAttribute("leaves", leavesService.getAllLeavesOrderedByDate());
        Leaves leave = new Leaves();
        model.addAttribute("oneLeave", leave);

        model.addAttribute("leavesTypes",leaveTypeService.getAllLeaveType());

        return "leaves-employee";

        }


    @GetMapping("/leaves.html")
    @PreAuthorize("hasAuthority('management')")
    public String Leaves(Model model){

        model.addAttribute("leaves", leavesService.getAllLeavesOrderedByDate());
        Leaves leave = new Leaves();
        model.addAttribute("oneLeave", leave);

        model.addAttribute("leavesTypes",leaveTypeService.getAllLeaveType());

        return "leaves";

    }
    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/create-estimate.html")
    public String create_estimate(Model model){

        return "create-estimate";

    }

    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")


    @GetMapping("/estimates.html")
    public String estimates(Model model){
        model.addAttribute("estimates", estimatesInvoicesService.getAllEstimates());

        return "estimates";

    }


    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/edit-estimate.html")
    public String editEstimates(Model model){
        return "edit-estimate";
    }

    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/estimate-view.html")
    public String viewEstimates(Model model){
        return "estimate-view";
    }
////////////////

    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/create-invoice.html")
    public String create_invoice(Model model){

        return "create-invoice";

    }
    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/invoices.html")
    public String invoices(Model model){
        model.addAttribute("estimates", estimatesInvoicesService.getAllInvoices());
        return "invoices";

    }

    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/edit-invoice.html")
    public String editInvoices(Model model){
        return "edit-invoice";
    }


    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/invoice-view.html")
    public String viewInvoices(Model model){
        return "invoice-view";
    }


    ////////////payment

    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/payments.html")
    public String payments(Model model){

        model.addAttribute("payments", paymentService.getAll());

        return "payments";
    }

    ///////Expenses
    @PreAuthorize("hasAuthority('management') or hasAuthority('finance')")

    @GetMapping("/expenses.html")
    public String expenses(Model model){
        Expenses expense = new Expenses();
        model.addAttribute("oneExpense", expense);
        model.addAttribute("expenses", expensesService.getAllExpensesOrderedByDate());

        return "expenses";
    }


    //////department
    @PreAuthorize("hasAuthority('management')")

    @GetMapping("/departments.html")
    public String department(Model model){
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("departments", departmentService.getAll());

        return "departments";
    }



}
