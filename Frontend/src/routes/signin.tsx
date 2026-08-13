import { createFileRoute } from "@tanstack/react-router";
import { AuthForm } from "@/components/site/AuthForm";

export const Route = createFileRoute("/signin")({
  head: () => ({
    meta: [
      { title: "Sign in — Hirely" },
      { name: "description", content: "Sign in to Hirely as a candidate, hiring manager, or company." },
      { property: "og:title", content: "Sign in — Hirely" },
      { property: "og:description", content: "Sign in as a candidate, hiring manager, or company." },
    ],
  }),
  component: () => <AuthForm mode="signin" />,
});
