import { createFileRoute, Link, notFound } from "@tanstack/react-router";
import { Star } from "lucide-react";
import { getCompany, jobs, reviews } from "@/data/mock";

export const Route = createFileRoute("/companies/$slug")({
  loader: ({ params }) => {
    const company = getCompany(params.slug);
    if (!company) throw notFound();
    return { company };
  },
  head: ({ loaderData }) => {
    if (!loaderData) {
      return { meta: [{ title: "Company not found — Hirely" }, { name: "robots", content: "noindex" }] };
    }
    const title = `${loaderData.company.name} reviews — Hirely`;
    const description = `${loaderData.company.name}: ${loaderData.company.rating} stars from ${loaderData.company.reviewCount} reviews, ${loaderData.company.openRoles} open roles.`;
    return {
      meta: [
        { title },
        { name: "description", content: description },
        { property: "og:title", content: title },
        { property: "og:description", content: description },
      ],
    };
  },
  component: CompanyDetail,
});

function CompanyDetail() {
  const { company } = Route.useLoaderData();
  const companyReviews = reviews[company.slug] ?? [];
  const companyJobs = jobs.filter((j) => j.companySlug === company.slug);

  return (
    <div className="mx-auto max-w-[900px] px-4 py-12 sm:px-6">
      <Link to="/companies" className="text-sm text-primary hover:underline">
        ← All companies
      </Link>

      <h1 className="mt-4 font-display text-4xl font-bold text-foreground">{company.name}</h1>
      <p className="mt-2 flex items-center gap-2 text-[15px] text-muted-foreground">
        <Star className="size-4 fill-current text-chart-4" />
        <span className="font-semibold text-foreground">{company.rating}</span> · {company.reviewCount} reviews
      </p>
      <p className="mt-1 text-[15px] text-muted-foreground">
        {company.industry} · {company.size} · {company.location}
      </p>
      <p className="mt-6 text-[15px] leading-relaxed text-foreground">{company.about}</p>

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Open roles</h2>
      <div className="mt-4 space-y-3">
        {companyJobs.length === 0 && <p className="text-sm text-muted-foreground">No live roles right now.</p>}
        {companyJobs.map((j) => (
          <Link
            key={j.id}
            to="/apply/$jobId"
            params={{ jobId: j.id }}
            className="block rounded-xl border border-border bg-card p-5 transition-shadow hover:shadow-md"
          >
            <p className="font-display text-lg font-semibold text-foreground">{j.title}</p>
            <p className="mt-1 text-sm text-muted-foreground">
              {j.location} · {j.payLabel}
            </p>
          </Link>
        ))}
      </div>

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Reviews</h2>
      <div className="mt-4 space-y-4">
        {companyReviews.map((r) => (
          <article key={r.id} className="rounded-xl border border-border bg-card p-6">
            <div className="flex items-center gap-1">
              {Array.from({ length: 5 }).map((_, i) => (
                <Star
                  key={i}
                  className={`size-4 ${i < r.rating ? "fill-current text-chart-4" : "text-border"}`}
                />
              ))}
            </div>
            <h3 className="mt-3 font-display text-lg font-semibold text-foreground">{r.title}</h3>
            <p className="mt-1 text-sm text-muted-foreground">
              {r.role} · {r.date}
            </p>
            <p className="mt-3 text-[15px] leading-relaxed text-foreground">{r.body}</p>
          </article>
        ))}
      </div>
    </div>
  );
}
