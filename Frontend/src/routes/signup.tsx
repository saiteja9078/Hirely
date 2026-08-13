import { createFileRoute } from "@tanstack/react-router";
import { AuthForm } from "@/components/site/AuthForm";

export const Route = createFileRoute("/signup")({
  head: () => ({
    meta: [
      { title: "Create an account — Hirely" },
      { name: "description", content: "Create a Hirely account as a candidate, hiring manager, or company." },
      { property: "og:title", content: "Create an account — Hirely" },
      { property: "og:description", content: "Create an account as a candidate, hiring manager, or company." },
    ],
  }),
  component: () => <AuthForm mode="signup" />,
});
