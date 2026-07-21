import axios, { AxiosError } from 'axios'
import type { ApiResponse, DesignResponse, ErrorResponse, Room, RoomRequest } from '../types/room'

const client = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
})

export function getApiErrorMessage(error: unknown): string {
  if (axios.isAxiosError(error)) {
    const data = (error as AxiosError<ErrorResponse>).response?.data
    if (data?.message) return data.message
  }
  return 'Something went wrong. Please try again.'
}

export async function listRooms(): Promise<Room[]> {
  const res = await client.get<ApiResponse<Room[]>>('/rooms')
  return res.data.data
}

export async function getRoom(roomId: number): Promise<Room> {
  const res = await client.get<ApiResponse<Room>>(`/rooms/${roomId}`)
  return res.data.data
}

export async function createRoom(request: RoomRequest): Promise<Room> {
  const res = await client.post<ApiResponse<Room>>('/rooms', request)
  return res.data.data
}

export async function updateRoom(roomId: number, request: RoomRequest): Promise<Room> {
  const res = await client.put<ApiResponse<Room>>(`/rooms/${roomId}`, request)
  return res.data.data
}

export async function deleteRoom(roomId: number): Promise<void> {
  await client.delete(`/rooms/${roomId}`)
}

export async function generateDesign(roomId: number, image: File): Promise<DesignResponse> {
  const formData = new FormData()
  formData.append('image', image)

  const res = await client.post<ApiResponse<DesignResponse>>(
    `/rooms/${roomId}/generate-design`,
    formData,
    { headers: { 'Content-Type': 'multipart/form-data' } },
  )
  return res.data.data
}
