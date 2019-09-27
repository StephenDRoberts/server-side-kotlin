package com.example.serversidekotlin

import org.springframework.stereotype.Service

@Service
class DepartmentService(
    private val employeeService: EmployeeService
) {

    //We need to get all employees first to then access their Department details
    fun getAllDepts() = employeeService.getAllEmployees()
        .map { it.dept }
        .distinct()
}