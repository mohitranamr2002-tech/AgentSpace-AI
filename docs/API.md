# REST APIs

## Create Room

POST /api/v1/rooms

---

## Update Room

PUT /api/v1/rooms/{id}

---

## Delete Room

DELETE /api/v1/rooms/{id}

---

## Get Room

GET /api/v1/rooms/{id}

---

## Generate AI Design

POST /api/v1/rooms/{id}/generate-design

Multipart Request

image : File

Returns

- Room Analysis

- Planner

- Style

- Budget

- Report

- Generated Images
