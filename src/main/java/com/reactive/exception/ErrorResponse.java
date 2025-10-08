package com.reactive.exception;

public class ErrorResponse {

    private int statusCode;
    private String messageError;
    private String description;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "statusCode=" + statusCode +
                ", messageError='" + messageError + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
