package com.mahasiswa.client;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class MahasiswaClient {

    private final RestClient restClient;

    public MahasiswaClient(RestClient.Builder builder,
            @Value("${layanan.mahasiswa.url}") String url) {
        this.restClient = builder.baseUrl(url).build();
    }

    public Optional<Mahasiswa> cariNpm(String npm) {
        try {
            Mahasiswa m = restClient.get()
                    .uri("/api/mahasiswa/npm/{npm}", npm)
                    .retrieve()
                    .body(Mahasiswa.class);
            return Optional.ofNullable(m);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();            // NPM tidak terdaftar
        } catch (ResourceAccessException e) {
            throw new LayananTidakTersediaException(
                    "Layanan mahasiswa tidak dapat dihubungi");
        }
    }
}
