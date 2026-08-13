# Hirely — Indeed-style job platform (front-end only)

A multi-page job platform clone in the visual language of the attached screenshots: white/near-white surfaces, one strong blue accent, bold geometric sans headings, soft rounded cards with thin borders, generous whitespace, no decorative flourishes. Desktop-first, then adapted down to mobile. Full dark mode with a manual toggle that respects system preference and persists.

No backend. All data comes from typed mock modules; forms are no-op handlers so real APIs can be wired in later.

## Roles

Three account types, chosen at sign-in/sign-up: **Candidate**, **Hiring Manager**, **Company**. The selected role is held in a client-side context (persisted to localStorage) and drives which nav links and dashboard the app shows. No real auth.

## Pages

Public
- `/` — Landing page: hero with job search bar, value props, popular categories, featured companies, role-based CTA ("I'm looking for work" / "I'm hiring"), footer
- `/jobs` — Search results: filter rail (pay, job type, remote, date) + result list + right-hand detail pane, matching screenshot 1's split layout
- `/companies` — Company reviews index + `/companies/$slug` detail
- `/salaries` — Salary guide with role search and pay ranges
- `/signin`, `/signup` — role picker (Candidate / Hiring Manager / Company) then a static form

Candidate
- `/dashboard` — "Welcome, {name}", pay chip, "Jobs for you" list + selected job detail pane (screenshot 1)
- `/profile` — name + avatar, contact rows, "Employers can find you" banner, resume card, "Improve your job matches" rows (screenshot 2)
- `/notifications` — empty state with illustration, copy, and Find jobs button (screenshot 3)
- `/settings` — sidebar (Account / Security / Communications / Devices / Privacy) + account settings panel (screenshot 4)
- `/apply/$jobId` — application flow: progress bar, "Preparing review" loading state, review module with resume preview, supporting documents, job-alert toggle, legal copy, Submit (screenshots 5 & 6), then a submitted confirmation state

Hiring Manager
- `/hiring` — pipeline dashboard: open roles, applicants by stage, recent activity
- `/hiring/jobs/$jobId/applicants` — applicant list + candidate detail pane, stage actions

Company
- `/company` — company dashboard: posted jobs, team members, profile completeness
- `/company/post-job` — multi-section job posting form (no-op submit)
- `/company/profile` — public-facing company profile editor

## Shared components

`Header` (logo, primary nav, saved/messages/notifications/profile icon rail, theme toggle, role-aware right side), `Footer`, `Button` (primary/secondary/ghost/link), `Card`, `Badge`/`Chip`, `Input`, `Select`, `Toggle`, `Modal`, `Tabs`, `EmptyState`, `ProgressBar`, `SearchBar`, `JobCard`, `JobDetailPanel`, `SettingsNavItem`, `RoleBadge`, `ThemeToggle`.

## Technical notes

- Stack: TanStack Start (React 19 + TypeScript), file-based routes under `src/routes/`, Tailwind v4 with tokens in `src/styles.css`.
- Theming: light/dark values as `oklch` CSS variables in `:root` and `.dark`, mapped in `@theme inline`. A small `ThemeProvider` (no `next-themes` on this stack) reads `prefers-color-scheme`, allows manual override, persists to localStorage, and applies the class before paint to avoid a flash. Components use semantic tokens only — no hardcoded colors.
- Type shapes in `src/types/` (`Job`, `Company`, `CandidateProfile`, `Application`, `Notification`, `Applicant`, `UserRole`) and mock data in `src/data/`, so each page's data source is one import to swap for an API call.
- Fonts: geometric grotesque close to Indeed's — Poppins-adjacent pairing loaded via `<link>` in `__root.tsx`.
- Responsive: desktop layouts built to the screenshots first; split panes collapse to a single column with a detail drawer on mobile, nav collapses to a sheet menu.
- Each route gets its own `head()` title/description/OG metadata.
