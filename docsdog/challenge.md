Challenge: Currency Conversion Service

Objective

Membangun REST API sederhana menggunakan Spring Boot yang dapat melakukan konversi mata uang dan mendukung konfigurasi berdasarkan environment.

⸻

Requirement 1 - Converter Service

Buat REST API berikut:

Endpoint

GET /api/converter

Request

GET /api/converter?amount=100&from=USD&to=IDR

Response

{
  "amount": 100,
  "from": "USD",
  "to": "IDR",
  "rate": 16000,
  "result": 1600000
}

⸻

Conversion Rules

From	To	Rate
USD	IDR	16000
IDR	USD	0.0000625
SGD	IDR	12500
IDR	SGD	0.00008

Rate disimpan di dalam application configuration.

⸻

Requirement 2 - Custom Converter Component

Buat custom Spring Component:

CurrencyConverter

Method:

BigDecimal convert(
    BigDecimal amount,
    String from,
    String to
);

Controller tidak boleh melakukan perhitungan langsung.

Semua proses konversi harus melalui service dan converter.

⸻

Requirement 3 - Environment Variable

Aplikasi harus membaca environment variable berikut:

APP_NAME=Currency Conversion Service

Buat endpoint:

GET /api/info

Response:

{
  "applicationName": "Currency Conversion Service"
}

Jika environment variable tidak ditemukan:

{
  "applicationName": "Unknown Application"
}

⸻

Requirement 4 - Active Profile

Buat 2 profile:

application-dev.yml

app:
  message: Running in Development

application-prod.yml

app:
  message: Running in Production

Endpoint:

GET /api/profile

Response saat DEV:

{
  "message": "Running in Development"
}

Response saat PROD:

{
  "message": "Running in Production"
}

⸻

Requirement 5 - Validation

Validasi:

amount

Tidak boleh:

<= 0

from

Tidak boleh kosong

to

Tidak boleh kosong

Contoh response error:

{
  "message": "Amount must be greater than zero"
}

⸻

Bonus Challenge

Tambahkan endpoint:

GET /api/rates

Response:

[
  {
    "from": "USD",
    "to": "IDR",
    "rate": 16000
  },
  {
    "from": "SGD",
    "to": "IDR",
    "rate": 12500
  }
]

Rate dibaca menggunakan:

@ConfigurationProperties

bukan hardcoded.

⸻

Penilaian

Kriteria	Bobot
Spring Boot Structure	20%
Service Layer	20%
Custom Converter	20%
Environment Variable	15%
Active Profile	15%
Validation	10%

⸻

Expected Package Structure

com.company.converter
├── controller
│   └── ConverterController
│
├── service
│   └── ConverterService
│
├── converter
│   └── CurrencyConverter
│
├── config
│   └── RateProperties
│
├── dto
│   ├── ConversionRequest
│   └── ConversionResponse
│
└── Application

Challenge ini cukup bagus untuk interview junior–middle Spring Boot karena menguji:

* REST API
* Dependency Injection
* Service Layer
* Custom Converter Pattern
* @ConfigurationProperties
* Environment Variables
* Active Profiles (dev, prod)
* Validation (@Valid)
* Clean Architecture dasar

Tanpa perlu database sehingga peserta bisa fokus ke fundamental Spring Boot.
