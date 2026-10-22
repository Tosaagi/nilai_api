package com.mahasiswa.client;

public class LayananTidakTersediaException extends RuntimeException {

    public LayananTidakTersediaException(String pesan) {
        super(pesan);
    }
}
