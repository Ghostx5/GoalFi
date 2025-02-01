package com.aspirefinance.goalfi

data class User(
    val Finance: Finance = Finance(),
    val name: String? = null

)
data class Finance(
    val MonthlySalary: Int? = null,
    val ZipCode: String? = null
)