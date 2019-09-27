package com.example.serversidekotlin

data class Employee(
    val id: Int,
    val name: String,
    val age: Int,
    var dept: String,
    var salary: Double
)

data class EmployeeUpdateReq(val dept: String?, val salary: Double?)