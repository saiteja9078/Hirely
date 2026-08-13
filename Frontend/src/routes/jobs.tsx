import { createFileRoute } from "@tanstack/react-router";
import { useMemo, useState } from "react";
import { SearchBar } from "@/components/site/SearchBar";
import { JobCard } from "@/components/site/JobCard";
import { JobDetailPanel } from "@/components/site/JobDetailPanel";
import { jobs } from "@/data/mock";

export const Route = createFileRoute("/jobs")({
  head: () => ({
    meta: [
      { title: "Search jobs — Hirely" },
      { name: "description", content: "Browse open roles by pay, job type, and location on Hirely." },
      { property: "og:title", content: "Search jobs — Hirely" },
      { property: "og:description", content: "Browse open roles by pay, job type, and location." },
    ],
  }),
  component: JobsPage,
});

const jobTypeFilters = ["Full-time", "Part-time", "Internship", "Contract", "Fresher"];
const dateFilters = ["Last 24 hours", "Last 3 days", "Last 7 days", "Last 14 days"];

function JobsPage() {
  const [query, setQuery] = useState("");
  const [remoteOnly, setRemoteOnly] = useState(false);
  const [types, setTypes] = useState<string[]>([]);
  const [datePosted, setDatePosted] = useState<string | null>(null);
  const [selectedId, setSelectedId] = useState(jobs[0]!.id);

  const results = useMemo(() => {
    return jobs.filter((j) => {
      const q = query.trim().toLowerCase();
      const matchesQuery = !q || j.title.toLowerCase().includes(q) || j.company.toLowerCase().includes(q);
      const matchesRemote = !remoteOnly || j.remote;
      const matchesType = types.length === 0 || types.some((t) => j.jobTypes.includes(t));
      return matchesQuery && matchesRemote && matchesType;
    });
  }, [query, remoteOnly, types]);

  const selected = results.find((j) => j.id === selectedId) ?? results[0];

  function toggleType(t: string) {
    setTypes((prev) => (prev.includes(t) ? prev.filter((x) => x !== t) : [...prev, t]));
  }

  return (
    <div>
      <div className="border-b border-border bg-surface">
        <div className="mx-auto max-w-[1400px] px-4 py-6 sm:px-6">
          <SearchBar onSearch={(q) => setQuery(q)} />
        </div>
      </div>

      <div className="mx-auto grid max-w-[1400px] gap-6 px-4 py-8 sm:px-6 lg:grid-cols-[240px_minmax(0,1fr)] xl:grid-cols-[240px_minmax(0,420px)_minmax(0,1fr)]">
        <aside className="space-y-6">
          <FilterGroup title="Job type">
            {jobTypeFilters.map((t) => (
              <label key={t} className="flex cursor-pointer items-center gap-3 py-1.5 text-sm text-foreground">
                <input
                  type="checkbox"
                  checked={types.includes(t)}
                  onChange={() => toggleType(t)}
                  className="size-4 rounded border-input accent-primary"
                />
                {t}
              </label>
            ))}
          </FilterGroup>

          <FilterGroup title="Remote">
            <label className="flex cursor-pointer items-center gap-3 py-1.5 text-sm text-foreground">
              <input
                type="checkbox"
                checked={remoteOnly}
                onChange={() => setRemoteOnly((v) => !v)}
                className="size-4 rounded border-input accent-primary"
              />
              Remote only
            </label>
          </FilterGroup>

          <FilterGroup title="Date posted">
            {dateFilters.map((d) => (
              <label key={d} className="flex cursor-pointer items-center gap-3 py-1.5 text-sm text-foreground">
                <input
                  type="radio"
                  name="date-posted"
                  checked={datePosted === d}
                  onChange={() => setDatePosted(d)}
                  className="size-4 accent-primary"
                />
                {d}
              </label>
            ))}
          </FilterGroup>
        </aside>

        <section className="space-y-4">
          <p className="text-sm text-muted-foreground">
            {results.length} {results.length === 1 ? "job" : "jobs"}
          </p>
          {results.length === 0 ? (
            <div className="rounded-xl border border-border bg-card p-10 text-center">
              <p className="font-display text-lg font-semibold text-foreground">No matching jobs</p>
              <p className="mt-2 text-sm text-muted-foreground">Try removing a filter or searching a different title.</p>
            </div>
          ) : (
            results.map((job) => (
              <JobCard key={job.id} job={job} selected={selected?.id === job.id} onSelect={(j) => setSelectedId(j.id)} />
            ))
          )}
        </section>

        <section className="hidden xl:block">
          {selected && (
            <div className="sticky top-24 max-h-[calc(100vh-8rem)] overflow-y-auto">
              <JobDetailPanel job={selected} />
            </div>
          )}
        </section>
      </div>
    </div>
  );
}

function FilterGroup({ title, children }: { title: string; children: React.ReactNode }) {
  return (
    <div className="rounded-xl border border-border bg-card p-4">
      <h2 className="mb-2 text-sm font-semibold text-foreground">{title}</h2>
      {children}
    </div>
  );
}
