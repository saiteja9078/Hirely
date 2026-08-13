import { createFileRoute } from "@tanstack/react-router";
import { ChevronRight, Mail, Monitor, Lock, ShieldCheck, UserRound } from "lucide-react";
import { useState } from "react";
import { candidate } from "@/data/mock";

export const Route = createFileRoute("/settings")({
  head: () => ({
    meta: [
      { title: "Settings — Hirely" },
      { name: "description", content: "Manage your account, security, communications, devices, and privacy." },
      { property: "og:title", content: "Settings — Hirely" },
      { property: "og:description", content: "Manage your account, security, communications, and privacy." },
    ],
  }),
  component: SettingsPage,
});

type SectionId = "account" | "security" | "communications" | "devices" | "privacy";

const sections: { id: SectionId; title: string; blurb: string; icon: React.ReactNode; badge?: string }[] = [
  { id: "account", title: "Account settings", blurb: "Your contact information", icon: <UserRound className="size-5" /> },
  { id: "security", title: "Security settings", blurb: "Manage your account security", icon: <Lock className="size-5" />, badge: "New" },
  { id: "communications", title: "Communications settings", blurb: "Manage notifications and message settings", icon: <Mail className="size-5" /> },
  { id: "devices", title: "Device management", blurb: "Manage your active devices and sessions", icon: <Monitor className="size-5" /> },
  { id: "privacy", title: "Privacy settings", blurb: "Information about your privacy on Hirely", icon: <ShieldCheck className="size-5" /> },
];

function SettingsPage() {
  const [active, setActive] = useState<SectionId>("account");

  return (
    <div className="mx-auto grid max-w-[1400px] lg:grid-cols-[420px_minmax(0,1fr)]">
      <aside className="border-border bg-surface lg:border-r">
        <h1 className="px-6 py-10 font-display text-4xl font-bold text-foreground sm:px-10">Settings</h1>
        <nav>
          {sections.map((s) => (
            <button
              key={s.id}
              type="button"
              onClick={() => setActive(s.id)}
              className={`flex w-full items-start gap-4 border-t border-border px-6 py-6 text-left transition-colors sm:px-10 ${
                active === s.id ? "border-l-4 border-l-primary bg-card pl-5 sm:pl-9" : "hover:bg-accent/40"
              }`}
            >
              <span className="mt-0.5 text-foreground/70">{s.icon}</span>
              <span className="flex-1">
                <span className="flex items-center gap-2">
                  <span className="font-display text-lg font-semibold text-foreground">{s.title}</span>
                  {s.badge && (
                    <span className="rounded-md bg-primary px-2 py-0.5 text-xs font-semibold text-primary-foreground">
                      {s.badge}
                    </span>
                  )}
                </span>
                <span className="mt-1 block text-sm text-muted-foreground">{s.blurb}</span>
              </span>
              <ChevronRight className="mt-1 size-5 shrink-0 text-foreground/40" />
            </button>
          ))}
        </nav>
      </aside>

      <section className="px-6 py-10 sm:px-12">
        {active === "account" && <AccountPanel />}
        {active === "security" && (
          <Panel title="Security settings">
            <Row label="Two-step verification" value="Off" action="Turn on" />
            <Row label="Recent sign-ins" value="3 devices in the last 30 days" action="Review" />
          </Panel>
        )}
        {active === "communications" && (
          <Panel title="Communications settings">
            <Row label="Job alert emails" value="Weekly" action="Change" />
            <Row label="Employer messages" value="Enabled" action="Change" />
          </Panel>
        )}
        {active === "devices" && (
          <Panel title="Device management">
            <Row label="MacBook Pro · Chrome" value="Active now · Guntur, IN" action="Sign out" />
            <Row label="iPhone · Hirely app" value="Last active yesterday" action="Sign out" />
          </Panel>
        )}
        {active === "privacy" && (
          <Panel title="Privacy settings">
            <Row label="Profile visibility" value="Employers can find you" action="Change" />
            <Row label="Download your data" value="Request a copy of your Hirely data" action="Request" />
          </Panel>
        )}
      </section>
    </div>
  );
}

function AccountPanel() {
  return (
    <Panel title="Account settings">
      <Row label="Account type:" value="Jobseeker" action="Change account type" />
      <Row label="Email" value={candidate.email} action="Change email" />
      <Row label="Phone number" value={`+91 ${candidate.phone}`} action="Change phone number" />
      <Row label="Passkey" value="" action="Create passkey" />
      <Row label={candidate.email} value="" action="Sign out" />
      <button type="button" className="pt-4 font-semibold text-destructive hover:underline">
        Close my account
      </button>
    </Panel>
  );
}

function Panel({ title, children }: { title: string; children: React.ReactNode }) {
  return (
    <div className="max-w-3xl">
      <h2 className="font-display text-3xl font-bold text-foreground">{title}</h2>
      <div className="mt-8 space-y-0">{children}</div>
    </div>
  );
}

function Row({ label, value, action }: { label: string; value: string; action: string }) {
  return (
    <div className="flex flex-wrap items-center justify-between gap-4 border-t border-border py-7">
      <div>
        <p className="font-semibold text-foreground">{label}</p>
        {value && <p className="mt-1 text-[15px] text-muted-foreground">{value}</p>}
      </div>
      <button
        type="button"
        className="rounded-lg border border-input bg-card px-5 py-2.5 text-[15px] font-semibold text-primary transition-colors hover:bg-accent"
      >
        {action}
      </button>
    </div>
  );
}
