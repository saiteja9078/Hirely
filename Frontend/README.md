# Hirely Clone Studio

Build a pixel-perfect clone of Hirely using Next.js + TypeScript, based on the attached screenshots.

Reference: Screenshots attached — light mode covering [list pages/screens] and dark mode covering [list pages/screens]. Where a state isn't shown (hover, focus, active, loading, empty, error), infer it from the overall design language.

Scope:

Full multi-page site, matching the real site's structure: 

Replicate layout, spacing, typography, color tokens, iconography, and component styling as closely as the screenshots allow

Keep the same simple, minimal UX — no added flourishes not present in the original

Recreate shared components (nav bar, footer, buttons, cards, forms, modals) as reusable Next.js components so styling stays consistent across pages

Client-side routing via the Next.js App Router

Dark mode:

Toggle between light/dark themes matching the two screenshot sets exactly (colors, borders, shadows, contrast)

Use CSS variables / a theme provider (e.g. next-themes) so theme state persists across page navigation

Respect system preference by default, with a manual override toggle

Backend: None — front-end only. I'm building the backend myself, so use mock/placeholder data, no-op form handlers, and typed interfaces (TypeScript types/interfaces) for the data shapes so it's easy for me to wire up real API calls later.

Responsiveness: [mobile-first / desktop-first / both — specify]

Styling approach: [Tailwind CSS / CSS Modules / styled-components — your call, or specify]

Fonts/assets: [exact font family if known, otherwise closest available match]

This project was built with [Lovable](https://lovable.dev).

## Build with Lovable

Continue developing this project in the [Lovable editor](https://lovable.dev/projects/ff4878c8-4909-4fdd-a7e9-297aacb6b7c4).

- **Ship faster**: describe what you want to build and Lovable handles the code.
- **Stay in sync**: every change made in Lovable is committed straight to this repository.
- **Full ownership**: this code is yours. Push to `main` on GitHub and your changes sync back into Lovable, ready for your next prompt.

## Development

Prefer working locally? You need Node.js and npm — [install with nvm](https://github.com/nvm-sh/nvm#installing-and-updating).

```sh
git clone <this-repository-url>
cd <repository-name>
npm i
npm run dev
```
