import { Link } from "@tanstack/react-router";
import { Bell, Bookmark, Menu, MessageSquare, User } from "lucide-react";
import { useState } from "react";
import { Logo } from "./Logo";
import { ThemeToggle } from "./ThemeToggle";
import { ROLE_HOME, ROLE_LABELS, useRole } from "@/lib/role";

const publicNav = [
  { to: "/", label: "Home" },
  { to: "/jobs", label: "Find jobs" },
  { to: "/companies", label: "Company reviews" },
  { to: "/salaries", label: "Salary guide" },
] as const;

const activeProps = {
  className: "text-foreground after:scale-x-100",
};

export function Header() {
  const { role, setRole } = useRole();
  const [open, setOpen] = useState(false);

  return (
    <header className="sticky top-0 z-40 border-b border-border bg-background/95 backdrop-blur">
      <div className="mx-auto flex h-16 max-w-[1400px] items-center gap-8 px-4 sm:px-6">
        <Logo />

        <nav className="hidden items-center gap-7 md:flex">
          {publicNav.map((item) => (
            <Link
              key={item.to}
              to={item.to}
              activeOptions={{ exact: item.to === "/" }}
              activeProps={activeProps}
              className="relative py-5 text-[15px] text-muted-foreground transition-colors after:absolute after:inset-x-0 after:bottom-0 after:h-[3px] after:scale-x-0 after:bg-primary after:transition-transform hover:text-foreground"
            >
              {item.label}
            </Link>
          ))}
          {role && (
            <Link
              to={ROLE_HOME[role]}
              activeProps={activeProps}
              className="relative py-5 text-[15px] text-muted-foreground transition-colors after:absolute after:inset-x-0 after:bottom-0 after:h-[3px] after:scale-x-0 after:bg-primary after:transition-transform hover:text-foreground"
            >
              {role === "candidate" ? "My jobs" : "Dashboard"}
            </Link>
          )}
        </nav>

        <div className="ml-auto flex items-center gap-1">
          {role === "candidate" && (
            <div className="hidden items-center gap-1 sm:flex">
              <IconLink to="/jobs" label="Saved jobs">
                <Bookmark className="size-5" />
              </IconLink>
              <IconLink to="/messages" label="Messages">
                <MessageSquare className="size-5" />
              </IconLink>
              <IconLink to="/notifications" label="Notifications">
                <Bell className="size-5" />
              </IconLink>
              <IconLink to="/profile" label="Profile">
                <User className="size-5" />
              </IconLink>
            </div>
          )}

          <ThemeToggle />

          <div className="mx-2 hidden h-6 w-px bg-border sm:block" />

          {role ? (
            <div className="hidden items-center gap-3 sm:flex">
              <span className="rounded-full bg-secondary px-3 py-1 text-xs font-medium text-secondary-foreground">
                {ROLE_LABELS[role]}
              </span>
              <button
                type="button"
                onClick={() => setRole(null)}
                className="text-sm font-medium text-muted-foreground transition-colors hover:text-foreground"
              >
                Sign out
              </button>
            </div>
          ) : (
            <div className="hidden items-center gap-4 sm:flex">
              <Link to="/signin" className="text-sm font-medium text-foreground hover:underline">
                Sign in
              </Link>
              <Link
                to="/signup"
                className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
              >
                Employers / Post Job
              </Link>
            </div>
          )}

          <button
            type="button"
            aria-label="Open menu"
            onClick={() => setOpen((v) => !v)}
            className="inline-flex size-9 items-center justify-center rounded-full text-foreground md:hidden"
          >
            <Menu className="size-5" />
          </button>
        </div>
      </div>

      {open && (
        <nav className="border-t border-border bg-background px-4 pb-4 md:hidden">
          {[...publicNav, ...(role ? [{ to: ROLE_HOME[role], label: "Dashboard" } as const] : [])].map((item) => (
            <Link
              key={item.to}
              to={item.to}
              onClick={() => setOpen(false)}
              className="block border-b border-border py-3 text-[15px] text-foreground last:border-0"
            >
              {item.label}
            </Link>
          ))}
          {!role && (
            <div className="flex gap-3 pt-3">
              <Link to="/signin" onClick={() => setOpen(false)} className="flex-1 rounded-lg border border-input py-2 text-center text-sm font-medium">
                Sign in
              </Link>
              <Link to="/signup" onClick={() => setOpen(false)} className="flex-1 rounded-lg bg-primary py-2 text-center text-sm font-semibold text-primary-foreground">
                Sign up
              </Link>
            </div>
          )}
        </nav>
      )}
    </header>
  );
}

function IconLink({ to, label, children }: { to: string; label: string; children: React.ReactNode }) {
  return (
    <Link
      to={to}
      aria-label={label}
      activeProps={{ className: "text-primary" }}
      className="inline-flex size-9 items-center justify-center rounded-full text-foreground/80 transition-colors hover:bg-accent"
    >
      {children}
    </Link>
  );
}
