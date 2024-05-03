# README

## Overview

The "paymentapi" repository provides a REST API for creating, reading, and updating payments. This API is designed to facilitate seamless payment processing and management for various applications.

## API ENDPOINTS (Read swagger documentation for details)

- **BASE ENDPOINT: /bancobase**

- **POST: /api/v1/payments**
  - Creates a payment given a valid RequestBody
- **GET: /api/v1/payments/{id}**
  - Returns a payment given a valid, otherwise an error will be notify as Json
- **PUT: /api/v1/payments/{id}/{status}**
  - Updates and Returns a payment status given a valid id, otherwise an error will be notify as Json

## PRE-REQUIREMENTS:

- git (https://git-scm.com/downloads)
- java 17 (https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- gradle (https://gradle.org/install/)
- docker compose (https://www.docker.com/products/docker-desktop/)

## PRE-RUN REST API:

1. Create a folder of your choice
2. Open a command promp
3. Navigate to the folder you created in the first step
4. Execute: `git clone https://github.com/ivanyavila/paymentapi.git`

## RUN REST API:

1. Navigate to `your_folder\paymentapi\src\main\resources`
2. Execute: `docker compose up -d`
3. Navigate back to `your_folder\paymentapi\`
4. Execute: `./gradle build`
5. Execute: `./gradlew bootRun`

## SWAGGER LOCATION:

- http://localhost:8080/bancobase/swagger-ui/index.html

## MySQL SERVICE:

- database: bancobase
- port: 3306
- user: payment
- password: pass

## RABBIMQ UI SERVICE:

- port: 15672
- user: guest
- pass: guest

## STOP REST API:

1. Go to the same command promp where REST API is running
2. Press "Ctrl + c" to stop the REST API, and press "Y" and "enter" (or "S" according your SO languaje)
3. Navigate to `your_folder\paymentapi\src\main\resources`
4. Execute: `docker compose down`
