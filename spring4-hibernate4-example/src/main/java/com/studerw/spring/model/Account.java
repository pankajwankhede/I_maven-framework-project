package com.studerw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="ACCOUNT")
public class Account {
    @Id @GeneratedValue
    private long id;
    private double cashBalance;
    private String name;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getCashBalance() {
        return cashBalance;
    }
    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + ", balance: " + cashBalance + ", name: " + name;

    }
}