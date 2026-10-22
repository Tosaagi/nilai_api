# Layanan Nilai

![CI](https://github.com/Tosaagi/nilai_api/actions/workflows/ci.yml/badge.svg)

Layanan REST untuk menyimpan nilai mata kuliah dan menampilkan transkrip mahasiswa.

## Menjalankan

1. Nyalakan MySQL dan buat basis data `nilai_db`.
2. Jalankan layanan mahasiswa (`mahasiswa_api`) di port 8081.
3. Jalankan layanan ini dari NetBeans. Layanan berjalan di port 8082.

## Endpoint

| Metode | Alamat | Kegunaan |
|---|---|---|
| POST | /api/nilai | Menyimpan nilai baru |
| GET | /api/nilai/{npm} | Menampilkan transkrip mahasiswa |

## Menguji

Klik kanan proyek di NetBeans, lalu pilih Test. Uji tidak memerlukan MySQL.

## Tim Pengembang

- Andi Saputra (50421001)
- Budi Santoso (50421002)
