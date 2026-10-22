# Layanan Nilai

Layanan REST untuk menyimpan nilai dan menyusun transkrip mahasiswa.

## Menjalankan

1. Nyalakan MySQL dan buat basis data `nilai_db`.
2. Jalankan layanan mahasiswa (`mahasiswa_api`) di port 8081.
3. Jalankan layanan ini dari NetBeans. Layanan berjalan di port 8082.

## Endpoint

| Metode | Alamat | Kegunaan |
|---|---|---|
| POST | /api/nilai | Menyimpan nilai baru |
| GET | /api/nilai/{npm} | Menampilkan transkrip mahasiswa |

## Tim Pengembang

- Andi Saputra (50421001)
- Budi Santoso (50421002)
