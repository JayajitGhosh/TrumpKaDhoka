import { CheckCircleIcon, ExclamationTriangleIcon, ClockIcon } from '@heroicons/react/24/solid'

export default function Home() {
  return (
    <div className="space-y-8">
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="card">
          <div className="text-slate-400 text-sm">Active Employees</div>
          <div className="text-3xl font-bold mt-2">124</div>
          <div className="mt-3 badge badge-success"><CheckCircleIcon className="w-4 h-4 mr-1"/> Stable</div>
        </div>
        <div className="card">
          <div className="text-slate-400 text-sm">Pending Approvals</div>
          <div className="text-3xl font-bold mt-2">8</div>
          <div className="mt-3 badge badge-warning"><ClockIcon className="w-4 h-4 mr-1"/> Review</div>
        </div>
        <div className="card">
          <div className="text-slate-400 text-sm">Escalations</div>
          <div className="text-3xl font-bold mt-2">2</div>
          <div className="mt-3 badge badge-danger"><ExclamationTriangleIcon className="w-4 h-4 mr-1"/> Investigate</div>
        </div>
      </div>

      <div className="card">
        <div className="flex items-center justify-between">
          <h2 className="text-lg font-semibold">Supervisory Surveillance</h2>
          <span className="badge badge-info">Realtime</span>
        </div>
        <div className="mt-4 grid grid-cols-1 md:grid-cols-2 gap-4 text-sm text-slate-300">
          <div>Teacher Ψ inactivity: 14 days — Auto escalation queued</div>
          <div>PTM reminders sent today: 36</div>
          <div>Fees pending verifications: 12</div>
          <div>Live timetable swaps: 1</div>
        </div>
      </div>
    </div>
  )
}