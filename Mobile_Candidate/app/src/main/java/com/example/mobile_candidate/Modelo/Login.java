package com.example.mobile_candidate.Modelo;

import java.util.UUID;

public class Login {
    private UUID uuid;
    private String username;
    private String password;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID value) {
        this.uuid = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String value) {
        this.salt = value;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String value) {
        this.md5 = value;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String value) {
        this.sha1 = value;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String value) {
        this.sha256 = value;
    }
}
