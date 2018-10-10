package com.boot.contract.ifs;

public interface EmailService {
    void send(String from, String to, String title, String body);
}
