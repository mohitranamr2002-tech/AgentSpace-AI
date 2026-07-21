import { useEffect, useState } from 'react'
import type { FormEvent, ReactNode } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { createRoom, getApiErrorMessage, getRoom, updateRoom } from '../api/client'
import type { RoomRequest } from '../types/room'

const emptyForm: RoomRequest = {
  roomName: '',
  roomType: '',
  length: 0,
  width: 0,
  height: 0,
  budget: 0,
  preferredStyle: '',
}

export default function RoomFormPage() {
  const { roomId } = useParams()
  const isEdit = Boolean(roomId)
  const navigate = useNavigate()

  const [form, setForm] = useState<RoomRequest>(emptyForm)
  const [error, setError] = useState<string | null>(null)
  const [saving, setSaving] = useState(false)

  useEffect(() => {
    if (!roomId) return
    getRoom(Number(roomId))
      .then((room) =>
        setForm({
          roomName: room.roomName,
          roomType: room.roomType,
          length: room.length,
          width: room.width,
          height: room.height,
          budget: room.budget,
          preferredStyle: room.preferredStyle,
        }),
      )
      .catch((err) => setError(getApiErrorMessage(err)))
  }, [roomId])

  function updateField<K extends keyof RoomRequest>(key: K, value: RoomRequest[K]) {
    setForm((prev) => ({ ...prev, [key]: value }))
  }

  async function handleSubmit(e: FormEvent) {
    e.preventDefault()
    setSaving(true)
    setError(null)

    try {
      const room = isEdit ? await updateRoom(Number(roomId), form) : await createRoom(form)
      navigate(`/rooms/${room.id}`)
    } catch (err) {
      setError(getApiErrorMessage(err))
      setSaving(false)
    }
  }

  return (
    <div className="mx-auto max-w-xl">
      <h1 className="mb-6 text-2xl font-semibold text-slate-900">
        {isEdit ? 'Edit Room' : 'New Room'}
      </h1>

      <form onSubmit={handleSubmit} className="space-y-4 rounded-lg border border-slate-200 bg-white p-6">
        <Field label="Room Name">
          <input
            required
            className="input"
            value={form.roomName}
            onChange={(e) => updateField('roomName', e.target.value)}
          />
        </Field>

        <Field label="Room Type">
          <input
            required
            className="input"
            placeholder="e.g. Bedroom, Living Room"
            value={form.roomType}
            onChange={(e) => updateField('roomType', e.target.value)}
          />
        </Field>

        <div className="grid grid-cols-3 gap-4">
          <Field label="Length (ft)">
            <input
              required
              type="number"
              min={1}
              step="0.1"
              className="input"
              value={form.length || ''}
              onChange={(e) => updateField('length', Number(e.target.value))}
            />
          </Field>
          <Field label="Width (ft)">
            <input
              required
              type="number"
              min={1}
              step="0.1"
              className="input"
              value={form.width || ''}
              onChange={(e) => updateField('width', Number(e.target.value))}
            />
          </Field>
          <Field label="Height (ft)">
            <input
              required
              type="number"
              min={1}
              step="0.1"
              className="input"
              value={form.height || ''}
              onChange={(e) => updateField('height', Number(e.target.value))}
            />
          </Field>
        </div>

        <Field label="Budget (₹)">
          <input
            required
            type="number"
            min={1000}
            className="input"
            value={form.budget || ''}
            onChange={(e) => updateField('budget', Number(e.target.value))}
          />
        </Field>

        <Field label="Preferred Style">
          <input
            required
            className="input"
            placeholder="e.g. Scandinavian, Modern, Minimalist"
            value={form.preferredStyle}
            onChange={(e) => updateField('preferredStyle', e.target.value)}
          />
        </Field>

        {error && <p className="text-sm text-red-600">{error}</p>}

        <button
          type="submit"
          disabled={saving}
          className="w-full rounded-md bg-violet-600 px-4 py-2.5 font-medium text-white hover:bg-violet-700 disabled:opacity-50"
        >
          {saving ? 'Saving...' : isEdit ? 'Save Changes' : 'Create Room'}
        </button>
      </form>
    </div>
  )
}

function Field({ label, children }: { label: string; children: ReactNode }) {
  return (
    <label className="block">
      <span className="mb-1 block text-sm font-medium text-slate-700">{label}</span>
      {children}
    </label>
  )
}
