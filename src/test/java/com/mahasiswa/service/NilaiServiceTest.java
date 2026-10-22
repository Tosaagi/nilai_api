package com.mahasiswa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mahasiswa.client.LayananTidakTersediaException;
import com.mahasiswa.client.Mahasiswa;
import com.mahasiswa.client.MahasiswaClient;
import com.mahasiswa.model.Nilai;
import com.mahasiswa.model.Transkrip;
import com.mahasiswa.repository.NilaiRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NilaiServiceTest {

    private static final Mahasiswa ANDI = new Mahasiswa("50421001", "Andi Saputra");

    @Mock
    private NilaiRepository repository;

    @Mock
    private MahasiswaClient mahasiswaClient;

    @InjectMocks
    private NilaiService service;

    private Nilai nilaiRpl(String npm, int angka) {
        return new Nilai(0, npm, "IT-041", "Rekayasa Perangkat Lunak 2", angka);
    }

    @Test
    void nilaiDisimpanBilaNpmTerdaftar() {
        // Arrange: layanan mahasiswa tiruan mengenal NPM Andi
        Nilai n = nilaiRpl("50421001", 86);
        when(mahasiswaClient.cariNpm("50421001")).thenReturn(Optional.of(ANDI));
        when(repository.save(n)).thenReturn(n);

        // Act
        Nilai hasil = service.tambahNilai(n);

        // Assert
        assertEquals("A", hasil.getHuruf());
        verify(repository).save(n);
    }

    @Test
    void npmTidakTerdaftarDitolak() {
        Nilai n = nilaiRpl("50421099", 90);
        when(mahasiswaClient.cariNpm("50421099")).thenReturn(Optional.empty());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> service.tambahNilai(n));

        assertEquals("NPM 50421099 tidak terdaftar", e.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void angkaTidakSahDitolakTanpaMemanggilLayananMahasiswa() {
        Nilai n = nilaiRpl("50421001", 120);

        assertThrows(IllegalArgumentException.class, () -> service.tambahNilai(n));

        verify(mahasiswaClient, never()).cariNpm(anyString());
        verify(repository, never()).save(any());
    }

    @Test
    void transkripMenggabungkanDataDuaLayanan() {
        when(mahasiswaClient.cariNpm("50421001")).thenReturn(Optional.of(ANDI));
        when(repository.findByNpmOrderByKodeMk("50421001"))
                .thenReturn(List.of(nilaiRpl("50421001", 86)));

        Transkrip t = service.getTranskrip("50421001");

        assertEquals("Andi Saputra", t.nama());
        assertEquals(1, t.daftarNilai().size());
    }

    @Test
    void layananMahasiswaMatiDiteruskan() {
        LayananTidakTersediaException mati =
                new LayananTidakTersediaException("Layanan mahasiswa mati");
        when(mahasiswaClient.cariNpm("50421001")).thenThrow(mati);

        assertThrows(LayananTidakTersediaException.class,
                () -> service.getTranskrip("50421001"));
    }

    @Test
    void kodeMkSalahFormatDitolak() {
        Nilai n = new Nilai(0, "50421001", "IT41", "Pemrograman Web", 74);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> service.tambahNilai(n));

        assertEquals("Format kode mata kuliah harus seperti IT-041",
                e.getMessage());
        verify(mahasiswaClient, never()).cariNpm(anyString());
    }
}
