package com.jackdang.controller.members.dto;

import lombok.*;

public class AuthDto {

    @Getter @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthLoginRequest{
        private String phone;
        private String password;
    }

    @Getter @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthLoginResponse{
        private String phone;
        private String status;

        public AuthLoginResponse(String status){
            this.status = status;
        }
    }
}