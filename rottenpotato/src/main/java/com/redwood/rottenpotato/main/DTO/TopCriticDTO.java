package com.redwood.rottenpotato.main.DTO;

public class TopCriticDTO {

    private String criticKey;
    private Long num;

    public TopCriticDTO() {
    }

    public TopCriticDTO(String criticKey, Long num) {
        this.criticKey = criticKey;
        this.num = num;
    }

    public String getCriticKey() {
        return criticKey;
    }

    public void setCriticKey(String criticKey) {
        this.criticKey = criticKey;
    }

    public Long  getNum() {
        return num;
    }

    public void setNum(Long  num) {
        this.num = num;
    }
}
