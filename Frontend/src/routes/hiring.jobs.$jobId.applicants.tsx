import { createFileRoute, Link, notFound } from "@tanstack/react-router";
import { useState } from "react";
import { applicants as allApplicants, getJob } from "@/data/mock";
import type { ApplicationStage } from "@/types";

export const Route = createFileRoute("/hiring/jobs/$jobId/applicants")({
  loader: ({ params }) => {
    const job = getJob(params.jobId);
    if (!job) throw notFound();
    return { job };
  },
  head: ({ loaderData }) => {
    if (!loaderData) {
      return { meta: [{ title: "Role not found — Hirely" }, { name: "robots", content: "noindex" }] };
    }
    const title = `Applicants: ${loaderData.job.title} — Hirely`;
    const description = `Review and move applicants for ${loaderData.job.title} at ${loaderData.job.company}.`;
    return {
      meta: [
        { title },
        { name: "description", content: description },
        { property: "og:title", content: title },
        { property: "og:description", content: description },
      ],
    };
  },
  component: ApplicantsPage,
});

const stageOptions: ApplicationStage[] = ["applied", "screening", "interview", "offer", "rejected"];

function ApplicantsPage() {
  const { job } = Route.useLoaderData();
  const [list, setList] = useState(allApplicants);
  const [selectedId, setSelectedId] = useState(allApplicants[0]!.id);
  const selected = list.find((a) => a.id === selectedId) ?? list[0]!;

  function setStage(id: string, stage: ApplicationStage) {
    setList((prev) => prev.map((a) => (a.id === id ? { ...a, stage } : a)));
  }

  return (
    <div className="mx-auto max-w-[1200px] px-4 py-10 sm:px-6">
      <Link to="/hiring" className="text-sm text-primary hover:underline">
        ← Hiring dashboard
      </Link>
      <h1 className="mt-4 font-display text-3xl font-bold text-foreground">{job.title}</h1>
      <p className="mt-1 text-[15px] text-muted-foreground">
        {job.company} · {list.length} applicants
      </p>

      <div className="mt-8 grid gap-6 lg:grid-cols-[minmax(0,1fr)_minmax(0,1fr)]">
        <div className="space-y-3">
          {list.map((a) => (
            <button
              key={a.id}
              type="button"
              onClick={() => setSelectedId(a.id)}
              className={`flex w-full items-center gap-4 rounded-xl border bg-card p-5 text-left transition-shadow hover:shadow-md ${
                selected.id === a.id ? "border-primary ring-1 ring-primary" : "border-border"
              }`}
            >
              <span className="flex size-11 shrink-0 items-center justify-center rounded-full bg-secondary font-semibold text-secondary-foreground">
                {a.initials}
              </span>
              <span className="min-w-0 flex-1">
                <span className="block font-semibold text-foreground">{a.name}</span>
                <span className="block text-sm text-muted-foreground">{a.headline}</span>
              </span>
              <span className="shrink-0 rounded-md bg-secondary px-2.5 py-1 text-xs font-medium capitalize text-secondary-foreground">
                {a.stage}
              </span>
            </button>
          ))}
        </div>

        <div className="rounded-xl border border-border bg-card p-6 lg:sticky lg:top-24 lg:self-start">
          <h2 className="font-display text-2xl font-bold text-foreground">{selected.name}</h2>
          <p className="mt-1 text-[15px] text-muted-foreground">
            {selected.headline} · {selected.location}
          </p>
          <p className="mt-4 text-sm text-muted-foreground">
            Applied {selected.appliedAt} · match score {selected.matchScore}%
          </p>

          <div className="mt-5 flex flex-wrap gap-2">
            {selected.skills.map((s) => (
              <span key={s} className="rounded-md bg-secondary px-3 py-1.5 text-sm text-secondary-foreground">
                {s}
              </span>
            ))}
          </div>

          <p className="mt-8 text-sm font-semibold text-foreground">Move to stage</p>
          <div className="mt-3 flex flex-wrap gap-2">
            {stageOptions.map((s) => (
              <button
                key={s}
                type="button"
                onClick={() => setStage(selected.id, s)}
                className={`rounded-lg border px-4 py-2 text-sm font-medium capitalize transition-colors ${
                  selected.stage === s
                    ? "border-primary bg-primary text-primary-foreground"
                    : "border-input text-foreground hover:bg-accent"
                }`}
              >
                {s}
              </button>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
