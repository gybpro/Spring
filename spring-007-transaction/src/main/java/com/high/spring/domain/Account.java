package com.high.spring.domain;

/**
 * 帐户
 *
 * @author programmer
 * @date 2023/11/05
 */
public class Account {
    private String actNo;
    private Double balance;

    @Override
    public String toString() {
        return "Account{" +
                "actNo='" + actNo + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Account() {
    }

    public Account(String actNo, Double balance) {
        this.actNo = actNo;
        this.balance = balance;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
