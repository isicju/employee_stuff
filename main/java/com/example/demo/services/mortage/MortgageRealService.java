package com.example.demo.services.mortage;

public class MortgageRealService implements MortgageService {

    public Integer mortgage(Integer salary){
        return (int)(salary * 1.5);
    }

}
