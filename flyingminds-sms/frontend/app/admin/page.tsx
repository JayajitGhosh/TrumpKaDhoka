"use client";
import { useState } from 'react';
import axios from 'axios';

export default function AdminPage() {
  const [csv, setCsv] = useState("name,email,subject,temporary_password\nAlice,alice@school.com,Mathematics,Temp@123\nBob,bob@school.com,Physics,Temp@123");
  const [result, setResult] = useState<string>("");
  const baseUrl = process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8080";

  const importCsv = async () => {
    try {
      const res = await axios.post(`${baseUrl}/api/admin/teachers/import`, csv, {
        headers: { 'Content-Type': 'text/plain' }
      });
      setResult(JSON.stringify(res.data, null, 2));
    } catch (e: any) {
      setResult(e?.response?.data || e.message);
    }
  };

  return (
    <div className="space-y-6">
      <div className="card">
        <h1 className="text-xl font-semibold">Admin: Teacher Import (Idempotent)</h1>
        <p className="text-slate-300 text-sm mt-1">Paste CSV; duplicates are skipped automatically.</p>
        <textarea value={csv} onChange={e => setCsv(e.target.value)} className="w-full h-40 mt-4 p-3 rounded bg-slate-900 border border-slate-700" />
        <div className="mt-4">
          <button className="btn-primary" onClick={importCsv}>Import</button>
        </div>
        {result && (
          <pre className="mt-4 bg-slate-900 p-4 rounded border border-slate-700 text-sm overflow-auto">{result}</pre>
        )}
      </div>
    </div>
  );
}