package com.builzer.backend

import io.kotest.core.spec.style.BehaviorSpec

class Testest : BehaviorSpec({
    Given("Testest") {
        When("insert a new user") {
            Then("it should return the correct user") {
                println("Hello")
            }
        }
    }
})
