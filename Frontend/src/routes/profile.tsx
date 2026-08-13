import { createFileRoute, Link } from "@tanstack/react-router";
import { ChevronDown, ChevronRight, Eye, Mail, MapPin, MoreHorizontal, Phone } from "lucide-react";
import { useState } from "react";
import { candidate } from "@/data/mock";

export const Route = createFileRoute("/profile")({
  head: () => ({
    meta: [
      { title: "Your profile — Hirely" },
      { name: "description", content: "Manage your contact details, resume, and job match preferences." },
      { property: "og:title", content: "Your profile — Hirely" },
      { property: "og:description", content: "Manage your contact details, resume, and job match preferences." },
    ],
  }),
  component: ProfilePage,
});

const improveRows = [
  { title: "Qualifications", body: "Highlight your skills and experience." },
  { title: "Job preferences", body: "Save specific details like minimum desired pay and schedule." },
  { title: "Hide jobs with these details", body: "Manage the qualifications or preferences used to hide jobs from your search." },
];

function ProfilePage() {
  const [bannerOpen, setBannerOpen] = useState(false);

  return (
    <div className="mx-auto max-w-[760px] px-4 py-12 sm:px-6">
      <div className="flex items-start justify-between gap-6">
        <h1 className="font-display text-4xl font-bold text-foreground sm:text-5xl">{candidate.name}</h1>
        <div className="flex size-20 shrink-0 items-center justify-center rounded-full bg-secondary font-display text-2xl font-semibold text-secondary-foreground">
          {candidate.initials}
        </div>
      </div>

      <div className="mt-10 space-y-4">
        <ContactRow icon={<Mail className="size-5" />} value={candidate.email} />
        <ContactRow icon={<Phone className="size-5" />} value={candidate.phone} chevron />
        <ContactRow icon={<MapPin className="size-5" />} value={candidate.location} />
      </div>

      <button
        type="button"
        onClick={() => setBannerOpen((v) => !v)}
        className="mt-8 flex w-full items-center justify-between gap-3 rounded-lg bg-success-muted px-5 py-4 text-left"
      >
        <span className="flex items-center gap-3">
          <Eye className="size-5 text-success" />
          <span className="font-semibold text-foreground">Employers can find you</span>
        </span>
        <ChevronDown className={`size-5 text-foreground/70 transition-transform ${bannerOpen ? "rotate-180" : ""}`} />
      </button>
      {bannerOpen && (
        <p className="rounded-b-lg bg-success-muted/60 px-5 pb-4 text-sm text-foreground">
          Your profile is visible in employer searches. Turn this off in privacy settings at any time.
        </p>
      )}

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Resume</h2>
      {candidate.resume && (
        <div className="mt-4 flex items-center gap-4 rounded-xl border border-border bg-card p-5">
          <div className="flex size-14 shrink-0 items-end justify-center rounded-md bg-secondary pb-1 text-[10px] font-bold tracking-wide text-primary">
            PDF
          </div>
          <div className="min-w-0 flex-1">
            <p className="truncate font-semibold text-foreground">{candidate.resume.fileName}</p>
            <p className="text-sm text-muted-foreground">{candidate.resume.addedLabel}</p>
          </div>
          <button type="button" aria-label="Resume options" className="rounded-full p-2 text-foreground/70 hover:bg-accent">
            <MoreHorizontal className="size-5" />
          </button>
        </div>
      )}

      <h2 className="mt-12 font-display text-2xl font-bold text-foreground">Improve your job matches</h2>
      <div className="mt-2 border-t border-border">
        {improveRows.map((row) => (
          <button
            key={row.title}
            type="button"
            className="flex w-full items-center justify-between gap-6 border-b border-border py-6 text-left transition-colors hover:bg-accent/40"
          >
            <span>
              <span className="block font-display text-xl font-semibold text-foreground">{row.title}</span>
              <span className="mt-1 block text-[15px] text-muted-foreground">{row.body}</span>
            </span>
            <ChevronRight className="size-5 shrink-0 text-primary" />
          </button>
        ))}
      </div>

      <Link to="/settings" className="mt-8 inline-block text-sm font-medium text-primary hover:underline">
        Go to account settings
      </Link>
    </div>
  );
}

function ContactRow({ icon, value, chevron = false }: { icon: React.ReactNode; value: string; chevron?: boolean }) {
  return (
    <div className="flex items-center gap-4">
      <span className="text-foreground/70">{icon}</span>
      <span className="flex-1 text-[17px] text-foreground">{value}</span>
      {chevron && <ChevronRight className="size-5 text-primary" />}
    </div>
  );
}
