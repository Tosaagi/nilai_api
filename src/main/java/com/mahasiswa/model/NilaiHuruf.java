package com.mahasiswa.model;

/** Mengubah nilai angka 0 sampai 100 menjadi nilai huruf A sampai E. */
public final class NilaiHuruf {

    private NilaiHuruf() {
        // kelas utilitas, objeknya tidak perlu dibuat
    }

    public static String dari(int angka) {
        if (angka < 0 || angka > 100) {
            throw new IllegalArgumentException("Nilai angka harus 0 sampai 100");
        }
        if (angka >= 85) {
            return "A";
        } else if (angka >= 70) {
            return "B";
        } else if (angka >= 55) {
            return "C";
        } else if (angka >= 40) {
            return "D";
        }
        return "E";
    }
}
