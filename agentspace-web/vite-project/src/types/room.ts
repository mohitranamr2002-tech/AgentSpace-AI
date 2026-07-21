export interface Room {
  id: number
  roomName: string
  roomType: string
  length: number
  width: number
  height: number
  budget: number
  preferredStyle: string
  createdAt: string
}

export interface RoomRequest {
  roomName: string
  roomType: string
  length: number
  width: number
  height: number
  budget: number
  preferredStyle: string
}

export interface DesignResponse {
  planner: string
  style: string
  budget: string
  finalReport: string
}

export interface ApiResponse<T> {
  success: boolean
  message: string
  data: T
}

export interface ErrorResponse {
  success: boolean
  status: number
  error: string
  message: string
  timestamp: string
}
