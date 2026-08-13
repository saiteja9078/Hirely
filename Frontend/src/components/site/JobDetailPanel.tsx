import { Link } from "@tanstack/react-router";
import { Bookmark, Briefcase, Check, ChevronDown, Share2, ThumbsDown, Wallet } from "lucide-react";
import type { Job } from "@/types";

export function JobDetailPanel({ job }: { job: Job }) {
  return (
    <div className="overflow-hidden rounded-xl border border-border bg-card">
      <div className="p-7">
        <h2 className="font-display text-3xl font-bold text-foreground">{job.title}</h2>
        <p className="mt-3 text-[17px] text-muted-foreground">{job.company}</p>
        <p className="text-[17px] text-muted-foreground">{job.location}</p>
        <p className="text-[17px] text-muted-foreground">{job.payLabel}</p>

        <div className="mt-6 flex flex-wrap items-center gap-3">
          <Link
            to="/apply/$jobId"
            params={{ jobId: job.id }}
            className="rounded-lg bg-primary px-6 py-3 text-[15px] font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
          >
            Apply with Hirely
          </Link>
          <RoundButton label="Save job">
            <Bookmark className="size-5" />
          </RoundButton>
          <RoundButton label="Not interested">
            <ThumbsDown className="size-5" />
          </RoundButton>
          <RoundButton label="Share job">
            <Share2 className="size-5" />
          </RoundButton>
        </div>
      </div>

      <div className="border-t border-border p-7">
        <h3 className="font-display text-2xl font-bold text-foreground">Job details</h3>
        <p className="mt-2 text-sm text-muted-foreground">
          Here's how the job details align with your{" "}
          <Link to="/profile" className="text-primary underline underline-offset-2">
            profile
          </Link>
          .
        </p>

        <div className="mt-6 flex items-start gap-3">
          <Wallet className="mt-1 size-5 text-foreground/70" />
          <div>
            <p className="font-semibold text-foreground">Pay</p>
            <span className="mt-2 inline-flex items-center gap-2 rounded-md bg-success-muted px-3 py-1.5 text-sm font-medium text-foreground">
              <Check className="size-4 text-success" />
              {job.payLabel}
              <ChevronDown className="size-4 opacity-60" />
            </span>
          </div>
        </div>

        <div className="mt-6 flex items-start gap-3">
          <Briefcase className="mt-1 size-5 text-foreground/70" />
          <div>
            <p className="font-semibold text-foreground">Job type</p>
            <div className="mt-2 flex flex-wrap gap-2">
              {job.jobTypes.map((t) => (
                <span
                  key={t}
                  className="inline-flex items-center gap-2 rounded-md bg-secondary px-3 py-1.5 text-sm font-medium text-secondary-foreground"
                >
                  {t}
                  <ChevronDown className="size-4 opacity-60" />
                </span>
              ))}
            </div>
          </div>
        </div>
      </div>

      <div className="border-t border-border p-7">
        <h3 className="font-display text-2xl font-bold text-foreground">Benefits</h3>
        <p className="mt-1 text-sm text-muted-foreground">Pulled from the full job description</p>
        <ul className="mt-4 space-y-2">
          {job.benefits.map((b) => (
            <li key={b} className="text-[15px] text-foreground">
              {b}
            </li>
          ))}
        </ul>
      </div>

      <div className="border-t border-border p-7">
        <h3 className="font-display text-2xl font-bold text-foreground">Full job description</h3>
        <p className="mt-4 text-[15px] leading-relaxed text-foreground">{job.description}</p>
        <ul className="mt-4 list-disc space-y-2 pl-5 text-[15px] text-foreground">
          {job.responsibilities.map((r) => (
            <li key={r}>{r}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

function RoundButton({ label, children }: { label: string; children: React.ReactNode }) {
  return (
    <button
      type="button"
      aria-label={label}
      className="inline-flex size-12 items-center justify-center rounded-lg bg-secondary text-secondary-foreground transition-colors hover:bg-accent"
    >
      {children}
    </button>
  );
}
