import { useEffect, useState } from 'react'
import type { ChangeEvent } from 'react'
import { Link, useParams } from 'react-router-dom'
import { generateDesign, getApiErrorMessage, getRoom } from '../api/client'
import type { DesignResponse, Room } from '../types/room'
import DesignReport from '../components/DesignReport'

export default function RoomDetailPage() {
  const { roomId } = useParams()
  const [room, setRoom] = useState<Room | null>(null)
  const [error, setError] = useState<string | null>(null)

  const [image, setImage] = useState<File | null>(null)
  const [previewUrl, setPreviewUrl] = useState<string | null>(null)
  const [generating, setGenerating] = useState(false)
  const [design, setDesign] = useState<DesignResponse | null>(null)

  useEffect(() => {
    if (!roomId) return
    getRoom(Number(roomId))
      .then(setRoom)
      .catch((err) => setError(getApiErrorMessage(err)))
  }, [roomId])

  function handleImageChange(e: ChangeEvent<HTMLInputElement>) {
    const file = e.target.files?.[0] ?? null
    setImage(file)
    setDesign(null)
    setPreviewUrl(file ? URL.createObjectURL(file) : null)
  }

  async function handleGenerate() {
    if (!roomId || !image) return
    setGenerating(true)
    setError(null)

    try {
      const result = await generateDesign(Number(roomId), image)
      setDesign(result)
    } catch (err) {
      setError(getApiErrorMessage(err))
    } finally {
      setGenerating(false)
    }
  }

  if (error && !room) {
    return <p className="text-red-600">{error}</p>
  }

  if (!room) {
    return <p className="text-slate-500">Loading room...</p>
  }

  return (
    <div>
      <div className="mb-6 flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-semibold text-slate-900">{room.roomName}</h1>
          <p className="text-slate-500">
            {room.roomType} &middot; {room.length}ft x {room.width}ft x {room.height}ft &middot;{' '}
            {room.preferredStyle} &middot; ₹{room.budget.toLocaleString()}
          </p>
        </div>
        <Link
          to={`/rooms/${room.id}/edit`}
          className="rounded-md border border-slate-300 px-4 py-2 text-sm font-medium text-slate-700 hover:bg-slate-100"
        >
          Edit Room
        </Link>
      </div>

      <div className="rounded-lg border border-slate-200 bg-white p-6 shadow-sm">
        <h2 className="mb-3 font-semibold text-slate-900">Upload Room Photo</h2>

        <input
          type="file"
          accept="image/png,image/jpeg,image/webp"
          onChange={handleImageChange}
          className="block text-sm text-slate-600 file:mr-4 file:rounded-md file:border-0 file:bg-violet-50 file:px-4 file:py-2 file:text-sm file:font-medium file:text-violet-700 hover:file:bg-violet-100"
        />

        {previewUrl && (
          <img src={previewUrl} alt="Room preview" className="mt-4 max-h-80 rounded-md object-cover" />
        )}

        {error && <p className="mt-3 text-sm text-red-600">{error}</p>}

        <button
          type="button"
          onClick={handleGenerate}
          disabled={!image || generating}
          className="mt-4 rounded-md bg-violet-600 px-5 py-2.5 text-sm font-medium text-white hover:bg-violet-700 disabled:opacity-50"
        >
          {generating ? 'Generating design (this can take up to a minute)...' : 'Generate Design'}
        </button>
      </div>

      {design && (
        <div className="mt-8">
          <h2 className="mb-4 text-xl font-semibold text-slate-900">AI Interior Design Report</h2>
          <DesignReport design={design} />
        </div>
      )}
    </div>
  )
}
