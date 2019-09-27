package com.example.serversidekotlin

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Service
class EmployeeService {

    companion object {
        val employeeDb = mutableMapOf(
            1 to Employee(1, "Steve Roberts", 34, "Bet-Mgt", 5.00),
            2 to Employee(2, "Someone Else", 25, "BET", 10.00)
        )
    }

    fun createEmployee(employee: Employee) = employeeDb.put(employee.id, employee)

    fun getEmployee(id: Int) = employeeDb[id]?.toMono()

    //GET all employees according to certain criteria
    //passing in default values of null for our criteria so that if they're not specified then a default
    //value can be a fallback
    fun getAllEmployees(minAge: Int? = null, minSalary: Double? = null)
            = employeeDb.values.toFlux()
                .filter { it.age >= minAge ?: Int.MIN_VALUE}
                .filter { it.salary >= minSalary ?: Double.MIN_VALUE}

    fun updateEmployee(id: Int, updateEmployee: EmployeeUpdateReq){
        //!! ensures that employee with that ID is in our DB
        val employeeOnDb = employeeDb[id]!!

        employeeDb[id] = Employee(employeeOnDb.id, employeeOnDb.name, employeeOnDb.age,
            updateEmployee.dept ?: employeeOnDb.dept,
            updateEmployee.salary ?: employeeOnDb.salary
        )
    }

}