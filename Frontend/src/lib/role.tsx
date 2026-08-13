import { createContext, useCallback, useContext, useEffect, useState, type ReactNode } from "react";
import type { UserRole } from "@/types";

const STORAGE_KEY = "hirely-role";

export const ROLE_LABELS: Record<UserRole, string> = {
  candidate: "Candidate",
  hiring: "Hiring Manager",
  company: "Company",
};

export const ROLE_HOME: Record<UserRole, string> = {
  candidate: "/dashboard",
  hiring: "/hiring",
  company: "/company",
};

interface RoleContextValue {
  role: UserRole | null;
  setRole: (role: UserRole | null) => void;
}

const RoleContext = createContext<RoleContextValue>({ role: null, setRole: () => {} });

export function RoleProvider({ children }: { children: ReactNode }) {
  const [role, setRoleState] = useState<UserRole | null>(null);

  useEffect(() => {
    const stored = localStorage.getItem(STORAGE_KEY) as UserRole | null;
    if (stored === "candidate" || stored === "hiring" || stored === "company") {
      setRoleState(stored);
    }
  }, []);

  const setRole = useCallback((next: UserRole | null) => {
    if (next) localStorage.setItem(STORAGE_KEY, next);
    else localStorage.removeItem(STORAGE_KEY);
    setRoleState(next);
  }, []);

  return <RoleContext.Provider value={{ role, setRole }}>{children}</RoleContext.Provider>;
}

export function useRole() {
  return useContext(RoleContext);
}
