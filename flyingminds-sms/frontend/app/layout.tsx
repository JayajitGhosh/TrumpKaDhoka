export const metadata = {
  title: 'FlyingMinds SMS',
  description: 'Pristine, executive-grade School Management System',
}

import './globals.css';
import Link from 'next/link';

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body>
        <div className="min-h-screen">
          <header className="sticky top-0 z-20 border-b border-slate-700/60 bg-slate-900/70 backdrop-blur">
            <div className="max-w-7xl mx-auto px-6 py-4 flex items-center justify-between">
              <div className="text-xl font-bold">
                <span className="text-fm-accent">Flying</span>Minds
                <span className="ml-2 text-slate-400">SMS</span>
              </div>
              <nav className="space-x-6 text-sm text-slate-300">
                <Link href="/">Dashboard</Link>
                <Link href="/admin">Admin</Link>
                <Link href="/teacher">Teacher</Link>
                <Link href="/student">Student</Link>
              </nav>
            </div>
          </header>
          <main className="max-w-7xl mx-auto px-6 py-8">{children}</main>
        </div>
      </body>
    </html>
  );
}