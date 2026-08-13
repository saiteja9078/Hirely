import { Link, useNavigate } from "@tanstack/react-router";
import { Briefcase, Building2, UserRound } from "lucide-react";
import { useState, type FormEvent } from "react";
import { ROLE_HOME, useRole } from "@/lib/role";
import type { UserRole } from "@/types";

const roleOptions: { value: UserRole; label: string; blurb: string; icon: React.ReactNode }[] = [
  { value: "candidate", label: "Candidate", blurb: "Find jobs and track applications", icon: <UserRound className="size-5" /> },
  { value: "hiring", label: "Hiring Manager", blurb: "Review applicants and run interviews", icon: <Briefcase className="size-5" /> },
  { value: "company", label: "Company", blurb: "Post jobs and manage your profile", icon: <Building2 className="size-5" /> },
];

export function AuthForm({ mode }: { mode: "signin" | "signup" }) {
  const [selected, setSelected] = useState<UserRole>("candidate");
  const { setRole } = useRole();
  const navigate = useNavigate();

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    setRole(selected);
    navigate({ to: ROLE_HOME[selected] });
  }

  return (
    <div className="mx-auto max-w-[520px] px-4 py-14 sm:px-6">
      <h1 className="font-display text-3xl font-bold text-foreground">
        {mode === "signin" ? "Sign in to Hirely" : "Create your Hirely account"}
      </h1>
      <p className="mt-2 text-[15px] text-muted-foreground">
        Choose the account you want to use. You can switch at any time.
      </p>

      <div className="mt-8 space-y-3">
        {roleOptions.map((opt) => (
          <button
            key={opt.value}
            type="button"
            onClick={() => setSelected(opt.value)}
            className={`flex w-full items-center gap-4 rounded-xl border p-4 text-left transition-colors ${
              selected === opt.value
                ? "border-primary bg-info-muted"
                : "border-border bg-card hover:border-input"
            }`}
          >
            <span
              className={`inline-flex size-10 shrink-0 items-center justify-center rounded-lg ${
                selected === opt.value ? "bg-primary text-primary-foreground" : "bg-secondary text-secondary-foreground"
              }`}
            >
              {opt.icon}
            </span>
            <span>
              <span className="block font-semibold text-foreground">{opt.label}</span>
              <span className="block text-sm text-muted-foreground">{opt.blurb}</span>
            </span>
          </button>
        ))}
      </div>

      <form onSubmit={handleSubmit} className="mt-8 space-y-4">
        {mode === "signup" && (
          <Field label="Full name" type="text" placeholder="Your name" />
        )}
        <Field label="Email address" type="email" placeholder="you@example.com" />
        <Field label="Password" type="password" placeholder="••••••••" />

        <button
          type="submit"
          className="w-full rounded-lg bg-primary py-3 text-[15px] font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
        >
          {mode === "signin" ? "Sign in" : "Create account"}
        </button>
      </form>

      <p className="mt-6 text-center text-sm text-muted-foreground">
        {mode === "signin" ? (
          <>
            New to Hirely?{" "}
            <Link to="/signup" className="text-primary hover:underline">
              Create an account
            </Link>
          </>
        ) : (
          <>
            Already have an account?{" "}
            <Link to="/signin" className="text-primary hover:underline">
              Sign in
            </Link>
          </>
        )}
      </p>
    </div>
  );
}

function Field({ label, type, placeholder }: { label: string; type: string; placeholder: string }) {
  return (
    <label className="block">
      <span className="mb-1.5 block text-sm font-medium text-foreground">{label}</span>
      <input
        type={type}
        placeholder={placeholder}
        className="w-full rounded-lg border border-input bg-card px-4 py-2.5 text-[15px] text-foreground placeholder:text-muted-foreground focus:border-primary focus:outline-none"
      />
    </label>
  );
}
