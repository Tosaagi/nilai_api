package com.mahasiswa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class NilaiHurufTest {

    // satu nilai di tengah setiap partisi
    @Test
    void nilaiDiTengahRentang() {
        assertEquals("A", NilaiHuruf.dari(92));
        assertEquals("B", NilaiHuruf.dari(77));
        assertEquals("C", NilaiHuruf.dari(60));
        assertEquals("D", NilaiHuruf.dari(47));
        assertEquals("E", NilaiHuruf.dari(20));
    }

    // kedua ujung setiap partisi
    @ParameterizedTest
    @CsvSource({
        "100, A", "85, A",
        "84, B", "70, B",
        "69, C", "55, C",
        "54, D", "40, D",
        "39, E", "0, E"
    })
    void nilaiBatas(int angka, String huruf) {
        assertEquals(huruf, NilaiHuruf.dari(angka));
    }

    // tepat di luar rentang yang sah
    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    void nilaiDiLuarRentangDitolak(int angka) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> NilaiHuruf.dari(angka));
        assertEquals("Nilai angka harus 0 sampai 100", e.getMessage());
    }
}
