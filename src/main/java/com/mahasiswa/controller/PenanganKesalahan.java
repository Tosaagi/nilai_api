package com.mahasiswa.controller;

import com.mahasiswa.client.LayananTidakTersediaException;
import com.mahasiswa.service.MahasiswaTidakDitemukanException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PenanganKesalahan {

    @ExceptionHandler(MahasiswaTidakDitemukanException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> tidakDitemukan(MahasiswaTidakDitemukanException e) {
        return Map.of("pesan", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> tidakValid(IllegalArgumentException e) {
        return Map.of("pesan", e.getMessage());
    }

    @ExceptionHandler(LayananTidakTersediaException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Map<String, String> tidakTersedia(LayananTidakTersediaException e) {
        return Map.of("pesan", e.getMessage());
    }
}
