package com.mahasiswa.service;

public class MahasiswaTidakDitemukanException extends RuntimeException {

    public MahasiswaTidakDitemukanException(String npm) {
        super("Mahasiswa dengan NPM " + npm + " tidak ditemukan");
    }
}
