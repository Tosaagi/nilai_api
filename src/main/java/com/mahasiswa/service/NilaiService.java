package com.mahasiswa.service;

import com.mahasiswa.client.Mahasiswa;
import com.mahasiswa.client.MahasiswaClient;
import com.mahasiswa.model.Nilai;
import com.mahasiswa.model.Transkrip;
import com.mahasiswa.repository.NilaiRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NilaiService {

    private final NilaiRepository repository;
    private final MahasiswaClient mahasiswaClient;

    public NilaiService(NilaiRepository repository,
            MahasiswaClient mahasiswaClient) {
        this.repository = repository;
        this.mahasiswaClient = mahasiswaClient;
    }

    public Nilai tambahNilai(Nilai n) {
        validasi(n);
        // NPM diperiksa ke layanan mahasiswa melalui REST API
        if (mahasiswaClient.cariNpm(n.getNpm()).isEmpty()) {
            throw new IllegalArgumentException(
                    "NPM " + n.getNpm() + " tidak terdaftar");
        }
        n.setId(0);                     // id selalu dibuat basis data
        return repository.save(n);
    }

    public Transkrip getTranskrip(String npm) {
        Mahasiswa m = mahasiswaClient.cariNpm(npm)
                .orElseThrow(() -> new MahasiswaTidakDitemukanException(npm));
        List<Nilai> daftar = repository.findByNpmOrderByKodeMk(npm);
        return new Transkrip(m.npm(), m.nama(), daftar);
    }

    private void validasi(Nilai n) {
        if (n.getNpm() == null || !n.getNpm().matches("\\d{8}")) {
            throw new IllegalArgumentException("NPM harus 8 digit angka");
        }
        if (n.getKodeMk() == null || n.getKodeMk().isBlank()) {
            throw new IllegalArgumentException("Kode mata kuliah wajib diisi");
        }
        if (n.getNamaMk() == null || n.getNamaMk().isBlank()) {
            throw new IllegalArgumentException("Nama mata kuliah wajib diisi");
        }
        if (n.getAngka() < 0 || n.getAngka() > 100) {
            throw new IllegalArgumentException("Nilai angka harus 0 sampai 100");
        }
    }
}
