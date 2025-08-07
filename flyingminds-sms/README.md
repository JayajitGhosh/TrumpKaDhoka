# FlyingMinds School Management System (Series D)

A full-stack, scalable School Management System designed for daily executive visibility and lucid user workflows.

- Creator: FlyingMinds
- Target: C‑Suite monitoring and everyday operational clarity
- UX: Role‑based, colorful, pristine, responsive UI with clear hierarchy
- Stack: Next.js + Tailwind (frontend), Spring Boot (backend), PostgreSQL (DB), Docker (DevOps)

## Roles and Hierarchy
- Super Admin → School Admin → Dept Head → Teacher/Staff → Student → Parent → Guest (read‑only)

## Key Features
- Automatic approval systems via API checks (no manual checkboxes)
- Pending users (teachers/parents) have view‑only material access
- Admin onboarding for teachers: manual or CSV import with temp password and email invite
- Teacher workspace: Exams, Assignments, Announcements; update grades/attendance; delete drafts only
- Admin approvals for high‑stakes actions (final grades, promotions)
- Students: linked to classes for read access; activation post‑fees approval
- Parents: flagging/escalations, reminders, PTM notifications
- Admins: dashboards with escalations and inactivity alerts
- Protocols: Deactivation and archival, temporary replacements, timetable swaps and real‑time routine changes

## Monorepo Structure

- `frontend/` Next.js 14 + Tailwind UI
- `backend/` Spring Boot + Spring Security + JPA + Flyway
- `docker-compose.yml` Dev services
- `.env` Shared environment variables

## Quick Start (Docker)

1. Create a `.env` in the repo root (or keep defaults):
   ```env
   POSTGRES_DB=flyingminds_sms
   POSTGRES_USER=fm_user
   POSTGRES_PASSWORD=fm_password
   JWT_SECRET=dev_jwt_secret_change_me
   BACKEND_PORT=8080
   FRONTEND_PORT=3000
   ```
2. Start services:
   ```bash
   docker compose up --build
   ```
3. Apps:
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080
   - Postgres: localhost:5432

## Sample Accounts (seed data)
- Super Admin: `super@flyingminds.app` / `super123!`
- School Admin: `admin@flyingminds.app` / `admin123!`
- Dept Head: `dept@flyingminds.app` / `dept123!`
- Teacher: `teacher@flyingminds.app` / `teacher123!` (Pending until approved)
- Parent: `parent@flyingminds.app` / `parent123!` (Pending until approved)
- Student: `student@flyingminds.app` / `student123!` (Pending until fees approved)

## Dev Notes
- RBAC via Spring Security + JWT
- Flyway for schema + seed data
- Strict approvals and workflow flags enforced server‑side
- CSV teacher import supported via Admin endpoint
- Timetable swap and temporary replacement workflows included as models/APIs

## License
© FlyingMinds. All rights reserved.