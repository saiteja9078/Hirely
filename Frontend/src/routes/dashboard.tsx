import { createFileRoute } from "@tanstack/react-router";
import { Wallet } from "lucide-react";
import { useState } from "react";
import { SearchBar } from "@/components/site/SearchBar";
import { JobCard } from "@/components/site/JobCard";
import { JobDetailPanel } from "@/components/site/JobDetailPanel";
import { candidate, jobs } from "@/data/mock";

export const Route = createFileRoute("/dashboard")({
  head: () => ({
    meta: [
      { title: "Jobs for you — Hirely" },
      { name: "description", content: "Your personalised job feed, saved searches, and application activity." },
      { property: "og:title", content: "Jobs for you — Hirely" },
      { property: "og:description", content: "Your personalised job feed and application activity." },
    ],
  }),
  component: Dashboard,
});

function Dashboard() {
  const [selectedId, setSelectedId] = useState(jobs[0]!.id);
  const selected = jobs.find((j) => j.id === selectedId) ?? jobs[0]!;

  return (
    <div>
      <div className="border-b border-border bg-surface">
        <div className="mx-auto max-w-[1400px] px-4 py-6 sm:px-6">
          <SearchBar />
        </div>
      </div>

      <div className="mx-auto max-w-[1400px] px-4 pt-10 sm:px-6">
        <h1 className="font-display text-3xl font-bold text-foreground sm:text-4xl">
          Welcome, {candidate.name.split(" ")[0]}
        </h1>
        <span className="mt-4 inline-flex items-center gap-2 rounded-lg border border-border bg-card px-4 py-2 text-sm font-medium text-foreground">
          <Wallet className="size-4 text-foreground/70" />
          {candidate.desiredPayLabel}
        </span>
      </div>

      <div className="mx-auto grid max-w-[1400px] gap-8 px-4 py-8 sm:px-6 lg:grid-cols-[minmax(0,1fr)_minmax(0,1.1fr)]">
        <section>
          <h2 className="font-display text-2xl font-bold text-foreground">Jobs for you</h2>
          <div className="mt-5 space-y-4">
            {jobs.map((job) => (
              <JobCard key={job.id} job={job} selected={selected.id === job.id} onSelect={(j) => setSelectedId(j.id)} />
            ))}
          </div>
        </section>

        <section className="hidden lg:block">
          <div className="sticky top-24 max-h-[calc(100vh-8rem)] overflow-y-auto">
            <JobDetailPanel job={selected} />
          </div>
        </section>
      </div>
    </div>
  );
}
