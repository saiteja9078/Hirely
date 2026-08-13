import { createFileRoute } from "@tanstack/react-router";
import { type FormEvent } from "react";
import { toast } from "sonner";
import { companies } from "@/data/mock";

export const Route = createFileRoute("/company/profile")({
  head: () => ({
    meta: [
      { title: "Company profile — Hirely" },
      { name: "description", content: "Edit the company profile candidates see alongside your job listings." },
      { property: "og:title", content: "Company profile — Hirely" },
      { property: "og:description", content: "Edit the profile candidates see alongside your job listings." },
    ],
  }),
  component: CompanyProfilePage,
});

function CompanyProfilePage() {
  const company = companies[0]!;

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    toast.success("Profile changes saved (demo — no backend connected).");
  }

  return (
    <div className="mx-auto max-w-[720px] px-4 py-12 sm:px-6">
      <h1 className="font-display text-3xl font-bold text-foreground sm:text-4xl">Company profile</h1>
      <p className="mt-2 text-[15px] text-muted-foreground">This is what candidates see on your listings.</p>

      <form onSubmit={handleSubmit} className="mt-8 space-y-4 rounded-xl border border-border bg-card p-6">
        <Field label="Company name" defaultValue={company.name} />
        <Field label="Industry" defaultValue={company.industry} />
        <Field label="Company size" defaultValue={company.size} />
        <Field label="Headquarters" defaultValue={company.location} />
        <label className="block">
          <span className="mb-1.5 block text-sm font-medium text-foreground">About</span>
          <textarea
            rows={6}
            defaultValue={company.about}
            className="w-full rounded-lg border border-input bg-card px-4 py-3 text-[15px] text-foreground focus:border-primary focus:outline-none"
          />
        </label>
        <button
          type="submit"
          className="w-full rounded-lg bg-primary py-3 text-[15px] font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
        >
          Save changes
        </button>
      </form>
    </div>
  );
}

function Field({ label, defaultValue }: { label: string; defaultValue: string }) {
  return (
    <label className="block">
      <span className="mb-1.5 block text-sm font-medium text-foreground">{label}</span>
      <input
        defaultValue={defaultValue}
        className="w-full rounded-lg border border-input bg-card px-4 py-2.5 text-[15px] text-foreground focus:border-primary focus:outline-none"
      />
    </label>
  );
}
