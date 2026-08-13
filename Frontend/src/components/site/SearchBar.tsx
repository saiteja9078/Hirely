import { MapPin, Search } from "lucide-react";
import { useState, type FormEvent } from "react";

interface SearchBarProps {
  defaultQuery?: string;
  defaultLocation?: string;
  onSearch?: (query: string, location: string) => void;
}

export function SearchBar({ defaultQuery = "", defaultLocation = "", onSearch }: SearchBarProps) {
  const [query, setQuery] = useState(defaultQuery);
  const [location, setLocation] = useState(defaultLocation);

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    onSearch?.(query, location);
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="flex flex-col gap-2 rounded-2xl border border-border bg-card p-2 shadow-sm md:flex-row md:items-center md:rounded-full md:pl-5"
    >
      <label className="flex flex-1 items-center gap-3 px-3 py-2 md:px-0">
        <Search className="size-5 shrink-0 text-muted-foreground" />
        <input
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="Job title, keywords, or company"
          className="w-full bg-transparent text-[15px] text-foreground placeholder:text-muted-foreground focus:outline-none"
        />
      </label>

      <div className="hidden h-8 w-px bg-border md:block" />

      <label className="flex flex-1 items-center gap-3 px-3 py-2 md:px-5">
        <MapPin className="size-5 shrink-0 text-muted-foreground" />
        <input
          value={location}
          onChange={(e) => setLocation(e.target.value)}
          placeholder='City, state, zip code, or "remote"'
          className="w-full bg-transparent text-[15px] text-foreground placeholder:text-muted-foreground focus:outline-none"
        />
      </label>

      <button
        type="submit"
        className="rounded-full bg-primary px-7 py-3 text-[15px] font-semibold text-primary-foreground transition-colors hover:bg-primary-hover"
      >
        Find jobs
      </button>
    </form>
  );
}
