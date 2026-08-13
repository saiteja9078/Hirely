package com.sai.hirely.service.valid;

public interface NotificationService{
    void sendAsync(String... args);
    void send(String... args);
}
