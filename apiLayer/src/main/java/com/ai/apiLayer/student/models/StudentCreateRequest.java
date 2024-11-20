package com.ai.apiLayer.student.models;

import jakarta.validation.constraints.NotBlank;

public class StudentCreateRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public StudentCreateRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public StudentCreateRequest() {}

    public void setFirstName(@NotBlank String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public @NotBlank String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank String lastName) {
        this.lastName = lastName;
    }
}
