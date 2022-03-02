package com.project.siren.user.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicResponse<T> {
    public boolean success;
    public T       body;

    public static <T> BasicResponse<T> success(T body) {
        return new BasicResponse<>(true, body);
    }

    public static <T> BasicResponse<T> fail(T data) {
        return new BasicResponse<>(false, data);
    }

    public static <T> BasicResponse<T> create(Boolean success, T data) {
        return new BasicResponse<>(success, data);
    }
}
