package com.example.demo.infrastructure.contant;

public enum Message {

    SUCCESS("Success");
    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
