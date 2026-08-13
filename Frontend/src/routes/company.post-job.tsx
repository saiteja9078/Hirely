import { createFileRoute } from "@tanstack/react-router";
import { useState, type FormEvent } from "react";
import { toast } from "sonner";

export const Route = createFileRoute("/company/post-job")({
  head: () => ({
    meta: [
      { title: "Post a job — Hirely" },
      { name: "description", content: "Create a job listing with pay, location, and requirements." },
      { property: "og:title", content: "Post a job — Hirely" },
      { property: "og:description", content: "Create a job listing with pay, location, and requirements." },
    ],
  }),
  component: PostJobPage,
});

const jobTypes = ["Full-time", "Part-time", "Internship", "Contract", "Fresher"];

function PostJobPage() {
  const [selectedTypes, setSelectedTypes] = useState<string[]>(["Full-time"]);

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    toast.success("Job saved as draft (demo — no backend connected).");
  }

  return (
    <div className="mx-auto max-w-[720px] px-4 py-12 sm:px-6">
      <h1 className="font-display text-3xl font-bold text-foreground sm:text-4xl">Post a job</h1>
      <p className="mt-2 text-[15px] text-muted-foreground">Candidates see this exactly as you write it.</p>

      <form onSubmit={handleSubmit} className="mt-8 space-y-8">
        <Section title="Basics">
          <Field label="Job title" placeholder="e.g. Python Developer Intern" />
          <Field label="Location" placeholder="City, state or Remote" />
        </Section>

        <Section title="Pay">
          <div className="grid gap-4 sm:grid-cols-2">
            <Field label="Minimum" placeholder="₹10,000" />
            <Field label="Maximum" placeholder="₹18,000" />
          </div>
          <Field label="Pay period" placeholder="per month" />
        </Section>

        <Section title="Job type">
          <div className="flex flex-wrap gap-2">
            {jobTypes.map((t) => (
              <button
                key={t}
                type="button"
                onClick={() =>
                  setSelectedTypes((prev) => (prev.includes(t) ? prev.filter((x) => x !== t) : [...prev, t]))
                }
                className={`rounded-lg border px-4 py-2 text-sm font-medium transition-colors ${
                  selectedTypes.includes(t)
                    ? "border-primary bg-primary text-primary-foreground"
                    : "border-input text-foreground hover:bg-accent"
                }`}
              >
                {t}
              </button>
            ))}
          </div>
        </Section>

        <Section title="Description">
          <label className="block">
            <span className="mb-1.5 block text-sm font-medium text-foreground">Full job description</span>
            <textarea
              rows={8}
              placeholder="What the role involves, who it's for, and what a good week looks like."
              className="w-full rounded-lg border border-input bg-card px-4 py-3 text-[15px] text-foreground placeholder:text-muted-foreground focus:border-primary focus:outline-none"
            />
          </label>
        </Section>

        <button
          type="submit"
          className="w-full rounded-lg bg-primary py-3.5 text-[15px] font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
        >
          Save job
        </button>
      </form>
    </div>
  );
}

function Section({ title, children }: { title: string; children: React.ReactNode }) {
  return (
    <section className="space-y-4 rounded-xl border border-border bg-card p-6">
      <h2 className="font-display text-lg font-bold text-foreground">{title}</h2>
      {children}
    </section>
  );
}

function Field({ label, placeholder }: { label: string; placeholder: string }) {
  return (
    <label className="block">
      <span className="mb-1.5 block text-sm font-medium text-foreground">{label}</span>
      <input
        placeholder={placeholder}
        className="w-full rounded-lg border border-input bg-card px-4 py-2.5 text-[15px] text-foreground placeholder:text-muted-foreground focus:border-primary focus:outline-none"
      />
    </label>
  );
}
