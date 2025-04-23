# High-Performance Gaming Platform

A high‑performance, cloud‑ready game‑sales platform.  Front‑end: JavaScript SPA.  Back‑end: Spring Boot, Spring Cloud, MyBatis, Redis, Kafka, Amazon RDS, ElastiCache, Cognito.  Container‑first (Docker / Kubernetes) with DevOps best‑practices.

---

## 1  Project Overview

- **Front‑end** – Vite + Vue 3, Element Plus, Tailwind CSS, Axios, Prettier.
- **Edge Gateway** – Spring Cloud Gateway with CORS, rate‑limiting, JWT filter, circuit breaker.
- **Service Discovery** – Eureka Registry.
- **Stateless Micro‑services** – user, auth‑center, item, search, cart, order, payment, sms, upload, admin.
- **Data Stores** – Amazon RDS (MySQL 8), Amazon ElastiCache (Redis), Elasticsearch, S3 for objects.
- **Messaging** – Kafka for event sourcing (item, sms); idempotent producers for exactly‑once.
- **Identity** – Amazon Cognito user pools; JWT signed with RSA key pair.
- **Observability** – Spring Boot Actuator, Prometheus, Grafana.
- **Build / CI** – Maven, GitHub, Docker.

---

## 2  Core Features

- User registration and login.
- Product catalogue and search with Elasticsearch.
- Shopping cart stored in Redis.
- Order management and Wechat payment.
- Admin dashboard for product CRUD, user moderation.
- SMS 2‑factor authentication using Kafka.
- Upload service for cover images and trailers.

---

## 3  Prerequisites

- JDK 8 
- Maven 3.6+
- Node.js 18 LTS 
- MySQL Workbench 8.x  (or AWS RDS console)
- Redis 6.x  (local or ElastiCache)
- Kafka 3.x
- Elasticsearch 7.17
- Nginx (for static hosting / reverse proxy)
- Git
- Docker 24+ and Docker‑Compose

---

## 4  Database Setup with MySQL Workbench

1. **Production (AWS RDS)**
   - Create a MySQL 8 instance.
   - Configure master username and password; optionally enable IAM authentication.
   - Open port 3306 only to the application subnet via a security group.
2. **Local Development**
   ```bash
   docker run -d --name mysql8 -e MYSQL_ROOT_PASSWORD=shop1234 -p 3306:3306 mysql:8
   ```
3. **Import Schema and Seed Data**
   - MySQL Workbench → *Server > Data Import*.
   - Choose **Import from Self‑Contained File** and select `mysql|5.7.sql`.
   - Target schema: `cloud_shop`, then start the import.
4. **Verify Data**
   ```sql
   SELECT COUNT(*) FROM tb_item;
   ```
5. **Configure Services**
   - Update each `application.yml` with the RDS endpoint or `localhost` connection string.

---

## 5  Running the Back‑end (IntelliJ IDEA + Maven)

1. Open project: *File > Open* → `cloud_shopping_project` (Maven workspace).
2. Ensure all modules use the correct JDK.
3. Create Spring Boot run configurations.
4. Start services in the following order:
   1. `cloud-shopping-registry` (Eureka)
   2. `cloud-shopping-gateway`
   3. All other micro‑services.
5. Active profiles:
   - `dev` (default) – local MySQL, in‑memory secrets.
   - `prod` – RDS, ElastiCache, Cognito.  Activate with `-Dspring.profiles.active=prod`.

---

## 6  Running the Front‑end

```bash
cd cloud_shopping_portal
npm ci           # install exact versions
npm run dev      # Vite dev server on http://localhost:5173
npm run build    # production bundle written to dist/
```

---

## 7  Deployment with Docker and Kubernetes

### 7.1  Docker Images

```bash
mvn -Pprod clean package -DskipTests
for m in registry gateway item cart order search user sms upload auth-center page; do
  docker build -t ghcr.io/yourrepo/$m:1.0 ./cloud-shopping-$m
  docker push ghcr.io/yourrepo/$m:1.0
done
```

### 7.2  Kubernetes

1. Create a local cluster:
   ```bash
   kind create cluster --image kindest/node:v1.29.0
   kubectl create namespace cloud-shop
   ```
2. Add Nginx Ingress and cert‑manager for TLS.
3. Configure Horizontal Pod Autoscaler based on CPU and Kafka lag metrics.

---

## 8  Security and Compliance

- JWT RSA keys are stored and rotated in AWS Secrets Manager.
- Cognito enforces SRP protocol, optional MFA, and strong password policies.

---

## 9  Testing Strategy

- **Unit tests** – JUnit.
- **API contract tests** – Postman.
- **Integration tests** – Testcontainers for MySQL, Kafka, Redis.
- **Chaos tests** – ChaosBlade.
- **Load tests** – Jmeter.
