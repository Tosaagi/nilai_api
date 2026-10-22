package com.mahasiswa.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mahasiswa.client.LayananTidakTersediaException;
import com.mahasiswa.model.Nilai;
import com.mahasiswa.model.Transkrip;
import com.mahasiswa.service.NilaiService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NilaiRestController.class)
class NilaiRestControllerTest {

    private static final String PESAN_MATI =
            "Layanan mahasiswa tidak dapat dihubungi";

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private NilaiService service;

    @Test
    void transkripDijawab200() throws Exception {
        Nilai rpl = new Nilai(1, "50421001", "IT-041",
                "Rekayasa Perangkat Lunak 2", 86);
        Transkrip andi = new Transkrip("50421001", "Andi Saputra", List.of(rpl));
        when(service.getTranskrip("50421001")).thenReturn(andi);

        mvc.perform(get("/api/nilai/50421001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nama").value("Andi Saputra"))
                .andExpect(jsonPath("$.daftarNilai[0].huruf").value("A"));
    }

    @Test
    void nilaiBaruDijawab201() throws Exception {
        Nilai web = new Nilai(2, "50421001", "IT-045", "Pemrograman Web", 74);
        when(service.tambahNilai(any(Nilai.class))).thenReturn(web);
        String json = """
                {"npm": "50421001", "kodeMk": "IT-045",
                 "namaMk": "Pemrograman Web", "angka": 74}
                """;

        mvc.perform(post("/api/nilai")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.huruf").value("B"));
    }

    @Test
    void layananMahasiswaMatiDijawab503() throws Exception {
        when(service.getTranskrip("50421001"))
                .thenThrow(new LayananTidakTersediaException(PESAN_MATI));

        mvc.perform(get("/api/nilai/50421001"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.pesan").value(PESAN_MATI));
    }
}
