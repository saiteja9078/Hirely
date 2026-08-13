import { createFileRoute } from "@tanstack/react-router";
import { useState } from "react";
import { salaries } from "@/data/mock";

export const Route = createFileRoute("/salaries")({
  head: () => ({
    meta: [
      { title: "Salary guide — Hirely" },
      { name: "description", content: "Compare typical pay ranges by role before your next application." },
      { property: "og:title", content: "Salary guide — Hirely" },
      { property: "og:description", content: "Compare typical pay ranges by role." },
    ],
  }),
  component: SalariesPage,
});

function SalariesPage() {
  const [query, setQuery] = useState("");
  const results = salaries.filter((s) => s.role.toLowerCase().includes(query.trim().toLowerCase()));

  return (
    <div className="mx-auto max-w-[900px] px-4 py-12 sm:px-6">
      <h1 className="font-display text-4xl font-bold text-foreground">Salary guide</h1>
      <p className="mt-3 text-[17px] text-muted-foreground">
        Typical pay for roles across India, based on listings on Hirely.
      </p>

      <input
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Job title"
        className="mt-8 w-full rounded-xl border border-input bg-card px-4 py-3 text-[15px] text-foreground placeholder:text-muted-foreground focus:border-primary focus:outline-none"
      />

      <div className="mt-8 overflow-hidden rounded-xl border border-border bg-card">
        {results.map((s, i) => (
          <div
            key={s.role}
            className={`flex flex-wrap items-center justify-between gap-4 p-5 ${i > 0 ? "border-t border-border" : ""}`}
          >
            <div>
              <p className="font-display text-lg font-semibold text-foreground">{s.role}</p>
              <p className="mt-1 text-sm text-muted-foreground">
                {s.category} · {s.jobCount.toLocaleString()} jobs
              </p>
            </div>
            <div className="text-right">
              <p className="font-semibold text-foreground">{s.averageLabel}</p>
              <p className="text-sm text-muted-foreground">{s.rangeLabel}</p>
            </div>
          </div>
        ))}
        {results.length === 0 && (
          <p className="p-8 text-center text-sm text-muted-foreground">No salary data for that title yet.</p>
        )}
      </div>
    </div>
  );
}
