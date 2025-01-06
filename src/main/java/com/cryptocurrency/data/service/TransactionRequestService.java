package com.cryptocurrency.data.service;

public class TransactionRequestService {
    private Long userId;
    private Long cryptoId;
    private double amountInvested;

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCryptoId() { return cryptoId; }

    public void setCryptoId(Long cryptoId) { this.cryptoId = cryptoId; }

    public double getAmountInvested() { return amountInvested; }

    public void setAmountInvested(double amountInvested) { this.amountInvested = amountInvested; }
}
