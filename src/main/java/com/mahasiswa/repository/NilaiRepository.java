package com.mahasiswa.repository;

import com.mahasiswa.model.Nilai;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NilaiRepository extends JpaRepository<Nilai, Integer> {

    // WHERE npm = ? ORDER BY kode_mk
    List<Nilai> findByNpmOrderByKodeMk(String npm);
}
