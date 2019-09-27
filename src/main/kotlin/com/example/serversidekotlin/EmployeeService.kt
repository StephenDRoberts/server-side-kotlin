package com.example.serversidekotlin

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class EmployeeService {

    companion object{
        val employeeDb = mutableMapOf(
            1 to Employee(1, "Steve Roberts", 34,"Bet-Mgt", 5.00),
            2 to Employee(2, "Someone Else", 25,"BET", 10.00)
        )
    }

    fun createEmployee(employee: Employee) = employeeDb.put(employee.id, employee)

    fun getEmployee(id: Int) = employeeDb[id]?.toMono()
}