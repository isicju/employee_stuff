package com.example.demo.services.mortage;


public class MortgageServiceMock implements MortgageService {

    int mockedValue;

    public MortgageServiceMock(int mockedValue) {
        this.mockedValue = mockedValue;
    }

    @Override
    public Integer mortgage(Integer salary) {
        return mockedValue;
    }

}
