import { createFileRoute, Link, notFound } from "@tanstack/react-router";
import { CheckCircle2, Flag } from "lucide-react";
import { useEffect, useState } from "react";
import { candidate, getJob } from "@/data/mock";

export const Route = createFileRoute("/apply/$jobId")({
  loader: ({ params }) => {
    const job = getJob(params.jobId);
    if (!job) throw notFound();
    return { job };
  },
  head: ({ loaderData }) => {
    if (!loaderData) {
      return { meta: [{ title: "Application unavailable — Hirely" }, { name: "robots", content: "noindex" }] };
    }
    const title = `Apply: ${loaderData.job.title} — Hirely`;
    const description = `Review and submit your application for ${loaderData.job.title} at ${loaderData.job.company}.`;
    return {
      meta: [
        { title },
        { name: "description", content: description },
        { property: "og:title", content: title },
        { property: "og:description", content: description },
      ],
    };
  },
  component: ApplyPage,
});

function ApplyPage() {
  const { job } = Route.useLoaderData();
  const [stage, setStage] = useState<"preparing" | "review" | "submitted">("preparing");
  const [alerts, setAlerts] = useState(false);

  useEffect(() => {
    const t = setTimeout(() => setStage("review"), 1400);
    return () => clearTimeout(t);
  }, []);

  return (
    <div className="mx-auto max-w-[640px] px-4 py-10 sm:px-6">
      <div className="rounded-xl border border-border bg-card p-6">
        <h1 className="font-display text-xl font-bold text-foreground">{job.title}</h1>
        <p className="mt-1 text-[15px] text-muted-foreground">
          {job.company} - {job.location}
        </p>
      </div>

      <div className="mt-6 rounded-xl border border-border bg-card p-6">
        {stage === "submitted" ? (
          <div className="py-10 text-center">
            <CheckCircle2 className="mx-auto size-14 text-success" />
            <h2 className="mt-6 font-display text-2xl font-bold text-foreground">Application submitted</h2>
            <p className="mt-3 text-[15px] text-muted-foreground">
              Your application was sent to {job.company}. You can track it from your dashboard.
            </p>
            <Link
              to="/dashboard"
              className="mt-8 inline-flex rounded-lg bg-primary px-8 py-3 text-[15px] font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
            >
              Back to jobs
            </Link>
          </div>
        ) : (
          <>
            <div className="flex items-center justify-end">
              <Link to="/dashboard" className="font-semibold text-primary hover:underline">
                Save and close
              </Link>
            </div>
            <div className="mt-4 flex items-center gap-4">
              <div className="h-1.5 flex-1 overflow-hidden rounded-full bg-secondary">
                <div className="h-full w-full rounded-full bg-primary" />
              </div>
              <span className="text-sm text-muted-foreground">100%</span>
            </div>

            {stage === "preparing" ? (
              <div className="py-20 text-center">
                <ResumeIllustration />
                <p className="mt-6 font-display text-xl font-bold text-foreground">Preparing review</p>
                <div className="mx-auto mt-6 h-2.5 w-64 overflow-hidden rounded-full border border-border">
                  <div className="h-full w-1/5 animate-pulse rounded-full bg-primary" />
                </div>
              </div>
            ) : (
              <div className="mt-8 space-y-8">
                <section>
                  <h2 className="font-display text-lg font-bold text-foreground">Contact information</h2>
                  <div className="mt-3 rounded-lg border border-border p-4 text-[15px] text-foreground">
                    <p className="font-semibold">{candidate.name}</p>
                    <p className="text-muted-foreground">{candidate.email}</p>
                    <p className="text-muted-foreground">{candidate.phone}</p>
                    <p className="text-muted-foreground">{candidate.location}</p>
                  </div>
                </section>

                <section>
                  <div className="flex items-center justify-between">
                    <h2 className="font-display text-lg font-bold text-foreground">Resume</h2>
                    <button type="button" className="font-semibold text-primary hover:underline">
                      Change
                    </button>
                  </div>
                  <div className="mt-3 rounded-lg border border-border p-4 text-[15px] text-foreground">
                    {candidate.resume?.fileName}
                    <p className="mt-2 text-sm leading-relaxed text-muted-foreground">
                      Built KV-cache support with separate prefill and decode passes for efficient autoregressive text
                      generation; trained on a public web dataset with cosine learning-rate decay and mixed precision.
                    </p>
                  </div>
                </section>

                <section>
                  <div className="flex items-center justify-between">
                    <h2 className="font-display text-lg font-bold text-foreground">Supporting documents</h2>
                    <button type="button" className="font-semibold text-primary hover:underline">
                      Add
                    </button>
                  </div>
                  <div className="mt-3 rounded-lg border border-border p-5 text-[15px] text-muted-foreground">
                    No cover letter or additional documents added. This is optional to add.
                  </div>
                </section>

                <section className="flex items-start justify-between gap-6">
                  <div>
                    <h2 className="font-display text-lg font-bold text-foreground">
                      Get email updates for the latest {job.title.toLowerCase()} jobs in {job.location}
                    </h2>
                    <p className="mt-2 text-sm text-muted-foreground">
                      By creating a job alert, you agree to our Terms. You can change your consent settings at any time
                      by unsubscribing or as detailed in our terms.
                    </p>
                  </div>
                  <button
                    type="button"
                    role="switch"
                    aria-checked={alerts}
                    aria-label="Get email updates"
                    onClick={() => setAlerts((v) => !v)}
                    className={`mt-1 inline-flex h-7 w-12 shrink-0 items-center rounded-full border border-border p-0.5 transition-colors ${
                      alerts ? "bg-primary" : "bg-secondary"
                    }`}
                  >
                    <span
                      className={`size-6 rounded-full bg-background shadow transition-transform ${
                        alerts ? "translate-x-5" : ""
                      }`}
                    />
                  </button>
                </section>

                <p className="border-t border-border pt-6 text-sm leading-relaxed text-muted-foreground">
                  By submitting your application, you agree to our Terms, Cookie &amp; Privacy Policies, and you consent
                  to your application being transmitted to the employer and processed in accordance with their policies.
                </p>

                <button
                  type="button"
                  onClick={() => setStage("submitted")}
                  className="w-full rounded-lg bg-primary py-4 text-[16px] font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
                >
                  Submit your application
                </button>

                <p className="flex items-center justify-center gap-2 text-sm text-muted-foreground">
                  <Flag className="size-4" /> <span className="underline">Report an issue</span>
                </p>
              </div>
            )}
          </>
        )}
      </div>
    </div>
  );
}

function ResumeIllustration() {
  return (
    <svg width="180" height="140" viewBox="0 0 180 140" fill="none" className="mx-auto" aria-hidden>
      <circle cx="90" cy="70" r="55" className="fill-secondary" />
      <rect x="52" y="30" width="66" height="84" rx="4" className="fill-background stroke-border" />
      <rect x="64" y="60" width="42" height="5" rx="2.5" className="fill-primary/40" />
      <rect x="64" y="72" width="42" height="5" rx="2.5" className="fill-primary/40" />
      <rect x="64" y="84" width="28" height="5" rx="2.5" className="fill-primary/40" />
      <circle cx="112" cy="52" r="12" className="fill-chart-3" />
    </svg>
  );
}
