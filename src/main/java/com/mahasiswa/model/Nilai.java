package com.mahasiswa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nilai")
public class Nilai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "npm", nullable = false, length = 8)
    private String npm;

    @Column(name = "kode_mk", nullable = false, length = 10)
    private String kodeMk;

    @Column(name = "nama_mk", nullable = false, length = 50)
    private String namaMk;

    @Column(name = "angka", nullable = false)
    private int angka;

    // constructor tanpa parameter, wajib untuk Hibernate
    public Nilai() {
    }

    public Nilai(int id, String npm, String kodeMk, String namaMk, int angka) {
        this.id = id;
        this.npm = npm;
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.angka = angka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public int getAngka() {
        return angka;
    }

    public void setAngka(int angka) {
        this.angka = angka;
    }

    // dihitung dari nilai angka, tidak disimpan di basis data
    public String getHuruf() {
        return NilaiHuruf.dari(angka);
    }
}
