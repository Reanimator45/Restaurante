package com.example.Restaurante.dtos;

public class EmployeeErrorDTO extends EmployeeDTO{

    private String ErrorMsg;


    public EmployeeErrorDTO(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
