import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { getApiErrorMessage, listRooms } from '../api/client'
import type { Room } from '../types/room'

export default function DashboardPage() {
  const [rooms, setRooms] = useState<Room[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    listRooms()
      .then(setRooms)
      .catch((err) => setError(getApiErrorMessage(err)))
      .finally(() => setLoading(false))
  }, [])

  if (loading) {
    return <p className="text-slate-500">Loading rooms...</p>
  }

  if (error) {
    return <p className="text-red-600">{error}</p>
  }

  if (rooms.length === 0) {
    return (
      <div className="rounded-lg border border-dashed border-slate-300 bg-white p-12 text-center">
        <h2 className="text-xl font-semibold text-slate-900">No rooms yet</h2>
        <p className="mt-2 text-slate-500">
          Create your first room to get an AI-generated interior design.
        </p>
        <Link
          to="/rooms/new"
          className="mt-6 inline-block rounded-md bg-violet-600 px-5 py-2.5 text-sm font-medium text-white hover:bg-violet-700"
        >
          Create Room
        </Link>
      </div>
    )
  }

  return (
    <div>
      <h1 className="mb-6 text-2xl font-semibold text-slate-900">Your Rooms</h1>
      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
        {rooms.map((room) => (
          <Link
            key={room.id}
            to={`/rooms/${room.id}`}
            className="rounded-lg border border-slate-200 bg-white p-5 shadow-sm transition hover:shadow-md"
          >
            <h2 className="font-semibold text-slate-900">{room.roomName}</h2>
            <p className="text-sm text-slate-500">{room.roomType}</p>
            <div className="mt-3 flex justify-between text-sm text-slate-600">
              <span>{room.preferredStyle}</span>
              <span>₹{room.budget.toLocaleString()}</span>
            </div>
          </Link>
        ))}
      </div>
    </div>
  )
}
