package com.example.serversidekotlin

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Controller(
    private val employeeService: EmployeeService = EmployeeService()
) {
    @PostMapping("/employee")
    fun createEmployee(@RequestBody employee: Employee): ResponseEntity<String>{
        employeeService.createEmployee(employee)
        return ResponseEntity.status(HttpStatus.CREATED).build<String>()
    }

    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable("id") id: Int) = employeeService.getEmployee(id)
}
