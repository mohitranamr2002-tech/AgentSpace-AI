import ReactMarkdown from 'react-markdown'
import type { DesignResponse } from '../types/room'

const sections: { key: keyof DesignResponse; title: string }[] = [
  { key: 'planner', title: 'Space Planning' },
  { key: 'style', title: 'Design Style' },
  { key: 'budget', title: 'Budget Estimate' },
  { key: 'finalReport', title: 'Final Report' },
]

export default function DesignReport({ design }: { design: DesignResponse }) {
  return (
    <div className="space-y-6">
      {sections.map(({ key, title }) => (
        <section key={key} className="rounded-lg border border-slate-200 bg-white p-6 shadow-sm">
          <h2 className="mb-3 text-lg font-semibold text-violet-700">{title}</h2>
          <div className="markdown-body">
            <ReactMarkdown>{design[key]}</ReactMarkdown>
          </div>
        </section>
      ))}
    </div>
  )
}
