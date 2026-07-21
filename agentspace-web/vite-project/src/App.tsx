import { Route, Routes } from 'react-router-dom'
import Layout from './components/Layout'
import DashboardPage from './pages/DashboardPage'
import RoomFormPage from './pages/RoomFormPage'
import RoomDetailPage from './pages/RoomDetailPage'

export default function App() {
  return (
    <Routes>
      <Route element={<Layout />}>
        <Route index element={<DashboardPage />} />
        <Route path="rooms/new" element={<RoomFormPage />} />
        <Route path="rooms/:roomId/edit" element={<RoomFormPage />} />
        <Route path="rooms/:roomId" element={<RoomDetailPage />} />
      </Route>
    </Routes>
  )
}
