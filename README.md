# Refleksi Modul 2

## Refleksi 1 – Code Quality Issues

Pas jalanin SonarCloud scan lewat GitHub Actions, hasilnya aman nggak ada issue yang ketangkep. Tapi ada beberapa hal yang saya benerin sendiri pas review kode manual

1. Redundant `public` di interface. Di Java, method dalam interface itu udah otomatis `public`, jadi nulis `public` lagi itu sebenernya nggak perlu. Saya hapus aja biar kodenya lebih bersih dan nggak bikin bingung.

2. Double semicolon (`;;`). Di `ProductServiceImpl.java` bagian `findAll()` ada typo kecil, dua titik koma sekaligus. Java sih masih bisa jalan, tapi tetep saya benerin biar kode nya rapi.

## Refleksi 2 – CI/CD Implementation

Menurut saya, implementasi CI/CD yang udah dibuat ini udah memenuhi definisi CI dan CD. Di sisi CI, setiap ada push atau pull request langsung otomatis ngejalanin unit test, ngukur code coverage pakai JaCoCo, scan kualitas kode pakai SonarCloud, dan cek keamanan dependency lewat OSSF Scorecard, semua tanpa harus ngapa-ngapain manual. Di sisi CD, setiap perubahan yang masuk ke branch `main` langsung otomatis kedeploy ke Railway pakai Dockerfile, jadi versi terbaru aplikasi langsung live tanpa perlu deploy sendiri. Menurut saya ini bagus, karena kode yang naik ke production udah pasti udah lewat serangkaian pengecekan otomatis duluan