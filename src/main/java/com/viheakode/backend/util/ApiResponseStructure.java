package com.viheakode.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseStructure {
    public static ResponseEntity<Object> responseSuccess(String msg, Object data, HttpStatus httpStatus){
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("message", msg);
        objectMap.put("data", data);
        objectMap.put("status", httpStatus.value());
        return new ResponseEntity<>(objectMap, httpStatus);
    }

    public static ResponseEntity<Object> responseError(String error, String msg, String path, HttpStatus httpStatus){
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("timestamp", LocalDateTime.now());
        objectMap.put("error", error);
        objectMap.put("message", msg);
        objectMap.put("path", path);
        objectMap.put("status", httpStatus.value());
        return new ResponseEntity<>(objectMap, httpStatus);
    }
}
