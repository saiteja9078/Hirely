import { createFileRoute, Link } from "@tanstack/react-router";
import { companies, jobs } from "@/data/mock";

export const Route = createFileRoute("/company/")({
  head: () => ({
    meta: [
      { title: "Company dashboard — Hirely" },
      { name: "description", content: "Manage posted jobs, your team, and your company profile on Hirely." },
      { property: "og:title", content: "Company dashboard — Hirely" },
      { property: "og:description", content: "Manage posted jobs, your team, and your company profile." },
    ],
  }),
  component: CompanyDashboard,
});

const team = [
  { name: "Priya Nair", role: "Hiring Manager", initials: "PN" },
  { name: "Vikram Shah", role: "Recruiter", initials: "VS" },
  { name: "Deepa Iyer", role: "Admin", initials: "DI" },
];

function CompanyDashboard() {
  const company = companies[0]!;
  const posted = jobs.filter((j) => j.companySlug === company.slug);

  return (
    <div className="mx-auto max-w-[1100px] px-4 py-12 sm:px-6">
      <div className="flex flex-wrap items-center justify-between gap-4">
        <div>
          <h1 className="font-display text-3xl font-bold text-foreground sm:text-4xl">{company.name}</h1>
          <p className="mt-2 text-[15px] text-muted-foreground">{company.industry} · {company.size}</p>
        </div>
        <Link
          to="/company/post-job"
          className="rounded-lg bg-primary px-5 py-2.5 text-sm font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
        >
          Post a job
        </Link>
      </div>

      <div className="mt-8 rounded-xl border border-border bg-card p-6">
        <div className="flex items-center justify-between">
          <p className="font-semibold text-foreground">Profile completeness</p>
          <span className="text-sm text-muted-foreground">75%</span>
        </div>
        <div className="mt-3 h-2 overflow-hidden rounded-full bg-secondary">
          <div className="h-full w-3/4 rounded-full bg-primary" />
        </div>
        <Link to="/company/profile" className="mt-4 inline-block text-sm font-medium text-primary hover:underline">
          Finish your company profile
        </Link>
      </div>

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Posted jobs</h2>
      <div className="mt-4 space-y-3">
        {posted.map((j) => (
          <div key={j.id} className="flex flex-wrap items-center justify-between gap-4 rounded-xl border border-border bg-card p-5">
            <div>
              <p className="font-display text-lg font-semibold text-foreground">{j.title}</p>
              <p className="mt-1 text-sm text-muted-foreground">
                {j.location} · {j.payLabel}
              </p>
            </div>
            <Link
              to="/hiring/jobs/$jobId/applicants"
              params={{ jobId: j.id }}
              className="text-sm font-medium text-primary hover:underline"
            >
              View applicants
            </Link>
          </div>
        ))}
      </div>

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Team</h2>
      <div className="mt-4 grid gap-3 sm:grid-cols-3">
        {team.map((m) => (
          <div key={m.name} className="flex items-center gap-3 rounded-xl border border-border bg-card p-5">
            <span className="flex size-10 items-center justify-center rounded-full bg-secondary text-sm font-semibold text-secondary-foreground">
              {m.initials}
            </span>
            <span>
              <span className="block font-medium text-foreground">{m.name}</span>
              <span className="block text-sm text-muted-foreground">{m.role}</span>
            </span>
          </div>
        ))}
      </div>
    </div>
  );
}
