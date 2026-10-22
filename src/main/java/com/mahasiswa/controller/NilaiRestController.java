package com.mahasiswa.controller;

import com.mahasiswa.model.Nilai;
import com.mahasiswa.model.Transkrip;
import com.mahasiswa.service.NilaiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nilai")
public class NilaiRestController {

    private final NilaiService service;

    public NilaiRestController(NilaiService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Nilai tambah(@RequestBody Nilai n) {
        return service.tambahNilai(n);
    }

    @GetMapping("/{npm}")
    public Transkrip transkrip(@PathVariable String npm) {
        return service.getTranskrip(npm);
    }
}
