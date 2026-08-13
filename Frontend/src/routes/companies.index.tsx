import { createFileRoute, Link } from "@tanstack/react-router";
import { Star } from "lucide-react";
import { useState } from "react";
import { companies } from "@/data/mock";

export const Route = createFileRoute("/companies/")({
  head: () => ({
    meta: [
      { title: "Company reviews — Hirely" },
      { name: "description", content: "Read ratings and reviews from people who work at these companies." },
      { property: "og:title", content: "Company reviews — Hirely" },
      { property: "og:description", content: "Ratings and reviews from people who work at these companies." },
    ],
  }),
  component: CompaniesPage,
});

function CompaniesPage() {
  const [query, setQuery] = useState("");
  const results = companies.filter((c) => c.name.toLowerCase().includes(query.trim().toLowerCase()));

  return (
    <div className="mx-auto max-w-[900px] px-4 py-12 sm:px-6">
      <h1 className="font-display text-4xl font-bold text-foreground">Company reviews</h1>
      <p className="mt-3 text-[17px] text-muted-foreground">
        Find out what it's really like to work somewhere before you apply.
      </p>

      <input
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Company name"
        className="mt-8 w-full rounded-xl border border-input bg-card px-4 py-3 text-[15px] text-foreground placeholder:text-muted-foreground focus:border-primary focus:outline-none"
      />

      <div className="mt-8 space-y-4">
        {results.map((c) => (
          <Link
            key={c.slug}
            to="/companies/$slug"
            params={{ slug: c.slug }}
            className="block rounded-xl border border-border bg-card p-6 transition-shadow hover:shadow-md"
          >
            <div className="flex flex-wrap items-center justify-between gap-3">
              <h2 className="font-display text-xl font-semibold text-foreground">{c.name}</h2>
              <span className="inline-flex items-center gap-1.5 text-sm text-muted-foreground">
                <Star className="size-4 fill-current text-chart-4" />
                <span className="font-semibold text-foreground">{c.rating}</span> · {c.reviewCount} reviews
              </span>
            </div>
            <p className="mt-1 text-sm text-muted-foreground">
              {c.industry} · {c.size} · {c.location}
            </p>
            <p className="mt-3 text-[15px] text-muted-foreground">{c.about}</p>
          </Link>
        ))}
        {results.length === 0 && (
          <p className="rounded-xl border border-border bg-card p-8 text-center text-sm text-muted-foreground">
            No companies match that search.
          </p>
        )}
      </div>
    </div>
  );
}
