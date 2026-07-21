# 🚀 AgentSpace AI

> Multi-Agent AI Powered Interior Design Recommendation System built using Spring Boot, Gemini AI and PostgreSQL.

---

# 📌 Overview

AgentSpace AI is an intelligent interior design recommendation platform that uses multiple AI agents to analyze a room, recommend layouts, estimate budgets, suggest interior styles and generate a professional design report.

Users can upload a room image along with room details and receive AI-generated recommendations.

---

# ✨ Features

- Room Management (CRUD)
- Image Upload
- AI Vision Analysis
- Planner Agent
- Style Recommendation Agent
- Budget Estimation Agent
- Report Generation Agent
- AI Image Prompt Generation
- Interior Design Image Generation
- PostgreSQL Persistence
- REST APIs
- Layered Architecture

---

# 🏗 Architecture

```

Room Image
│
▼

Vision Agent
│
▼

Planner Agent
│
▼

Style Agent
│
▼

Budget Agent
│
▼

Report Agent
│
▼

Image Generator Agent
│
▼

Generated Interior Design

```

---

# 🛠 Tech Stack

Backend

- Java 21
- Spring Boot 3
- Gradle

Database

- PostgreSQL

AI

- Google Gemini 2.5 Flash
- HuggingFace FLUX

Libraries

- Spring Web
- Spring Data JPA
- Lombok
- Google GenAI SDK

---

# 📂 Project Structure

```

src
└── main
├── java
│ ├── ai
│ ├── room
│ ├── config
│ └── exception
│
└── resources
└── prompts

```

---

# 🤖 AI Agents

## Vision Agent

Analyzes uploaded room image.

---

## Planner Agent

Suggests furniture placement.

---

## Style Agent

Recommends interior themes.

---

## Budget Agent

Provides estimated budget.

---

## Report Agent

Generates complete design report.

---

## Image Generator Agent

Creates AI image prompt and generates interior design visualization.

---

# 📸 API Flow

```

Upload Image

↓

Vision

↓

Planner

↓

Style

↓

Budget

↓

Report

↓

Image Generation

↓

JSON Response

```

---

# 📦 API Endpoints

| Method | Endpoint |
|----------|----------------------------------|
| GET | /api/v1/rooms |
| GET | /api/v1/rooms/{id} |
| POST | /api/v1/rooms |
| PUT | /api/v1/rooms/{id} |
| DELETE | /api/v1/rooms/{id} |
| POST | /api/v1/rooms/{id}/generate-design |

---

# ▶ Running the Project

```

git clone <repo>

cd agentspace-api

./gradlew bootRun

```

---

# 📷 Demo

1. Create Room

2. Upload Image

3. Generate Design

4. View AI Recommendations

5. View Generated Interior Image

---

# 👨‍💻 Developed By

Mohit Singh Rana
