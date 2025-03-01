package model;

import java.time.LocalDateTime;

/**
 * O înregistrare care stochează informații despre o factură.
 */
public record Bill(int orderNumber, String clientName, String productName, int quantity, LocalDateTime timeStamp) { }
