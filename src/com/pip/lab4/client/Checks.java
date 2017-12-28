package com.pip.lab4.client;

public class Checks {
    private Long id;
    private Double x;
    private Double y;
    private Double r;
    private Boolean result;
    private Long user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
