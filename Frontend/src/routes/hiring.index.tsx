import { createFileRoute, Link } from "@tanstack/react-router";
import { applicants, jobs } from "@/data/mock";
import type { ApplicationStage } from "@/types";

export const Route = createFileRoute("/hiring/")({
  head: () => ({
    meta: [
      { title: "Hiring dashboard — Hirely" },
      { name: "description", content: "Track open roles, applicants by stage, and recent hiring activity." },
      { property: "og:title", content: "Hiring dashboard — Hirely" },
      { property: "og:description", content: "Track open roles, applicants by stage, and recent activity." },
    ],
  }),
  component: HiringDashboard,
});

const stages: { id: ApplicationStage; label: string }[] = [
  { id: "applied", label: "Applied" },
  { id: "screening", label: "Screening" },
  { id: "interview", label: "Interview" },
  { id: "offer", label: "Offer" },
];

function HiringDashboard() {
  return (
    <div className="mx-auto max-w-[1100px] px-4 py-12 sm:px-6">
      <h1 className="font-display text-3xl font-bold text-foreground sm:text-4xl">Hiring dashboard</h1>
      <p className="mt-2 text-[15px] text-muted-foreground">Your pipeline across all open roles.</p>

      <div className="mt-8 grid gap-4 sm:grid-cols-4">
        {stages.map((s) => (
          <div key={s.id} className="rounded-xl border border-border bg-card p-5">
            <p className="text-sm text-muted-foreground">{s.label}</p>
            <p className="mt-2 font-display text-3xl font-bold text-foreground">
              {applicants.filter((a) => a.stage === s.id).length}
            </p>
          </div>
        ))}
      </div>

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Open roles</h2>
      <div className="mt-4 space-y-3">
        {jobs.slice(0, 4).map((job) => (
          <Link
            key={job.id}
            to="/hiring/jobs/$jobId/applicants"
            params={{ jobId: job.id }}
            className="flex flex-wrap items-center justify-between gap-4 rounded-xl border border-border bg-card p-5 transition-shadow hover:shadow-md"
          >
            <div>
              <p className="font-display text-lg font-semibold text-foreground">{job.title}</p>
              <p className="mt-1 text-sm text-muted-foreground">
                {job.location} · posted {job.postedAt}
              </p>
            </div>
            <span className="text-sm font-medium text-primary">{applicants.length} applicants →</span>
          </Link>
        ))}
      </div>

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Recent activity</h2>
      <ul className="mt-4 space-y-3">
        {applicants.map((a) => (
          <li key={a.id} className="rounded-xl border border-border bg-card p-5 text-[15px] text-foreground">
            <span className="font-semibold">{a.name}</span> moved to{" "}
            <span className="capitalize">{a.stage}</span>
            <span className="text-muted-foreground"> · {a.appliedAt}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}
