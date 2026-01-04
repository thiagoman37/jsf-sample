package com.example;

import java.io.Serializable;

/**
 * ユーザーフォームデータを保持するDTO
 */
public class UserFormDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String userName;
    private Integer age;
    private String email;
    
    public UserFormDTO() {
    }
    
    public UserFormDTO(String userName, Integer age, String email) {
        this.userName = userName;
        this.age = age;
        this.email = email;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void clear() {
        this.userName = null;
        this.age = null;
        this.email = null;
    }
}

