package com.example.serversidekotlin

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Controller(
    private val employeeService: EmployeeService = EmployeeService(),
    private val departmentService: DepartmentService = DepartmentService(employeeService)
) {
    @PostMapping("/employee")
    fun createEmployee(@RequestBody employee: Employee): ResponseEntity<String> {
        employeeService.createEmployee(employee)
        return ResponseEntity.status(HttpStatus.CREATED).build<String>()
    }

    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable("id") id: Int) = employeeService.getEmployee(id)

    @GetMapping("/employee")
    fun getAllEmployees(
        @RequestParam("minAge", required = false) minAge: Int?,
        @RequestParam("minSalary", required = false) minSalary: Double?
    ) = employeeService.getAllEmployees(minAge, minSalary)

    @GetMapping("/departments")
    fun getAllDepts() = departmentService.getAllDepts()

    @PutMapping("/employee/{id}")
    fun updateEmployee(@PathVariable("id") id: Int, @RequestBody updateEmployee: EmployeeUpdateReq) {
        employeeService.updateEmployee(id, updateEmployee)
    }
}
