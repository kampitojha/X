import React, { useState } from "react";
import './App.css'


const STEPS = [
  { id: "details", title: "Details" },
  { id: "terms", title: "Terms" },
  { id: "charges", title: "Charges" },
  { id: "preview", title: "Preview" },
];

function Stepper({ activeIndex, onJump }: { readonly activeIndex: number; readonly onJump: (index: number) => void }) {
  const getStepStyles = (isActive: boolean, isDone: boolean) => {
    if (isActive) return "bg-white border-indigo-600 text-indigo-600 shadow";
    if (isDone) return "bg-indigo-600 text-white border-indigo-600";
    return "bg-white border-gray-300 text-gray-500";
  };

  return (
    <nav className="w-full max-w-3xl mx-auto mb-6">
      <ol className="flex items-center justify-between">
        {STEPS.map((s, i) => {
          const isActive = i === activeIndex;
          const isDone = i < activeIndex;
          return (
            <li key={s.id} className="flex-1 text-center">
              <button
                onClick={() => onJump(i)}
                className="w-full focus:outline-none"
                aria-current={isActive ? "step" : undefined}
              >
                <div className="flex flex-col items-center">
                  <div
                    className={`rounded-full w-10 h-10 flex items-center justify-center border-2 transition-colors ${getStepStyles(isActive, isDone)}`}
                  >
                    {i + 1}
                  </div>
                  <span className={`mt-2 text-sm ${isActive ? "text-indigo-600 font-medium" : "text-gray-500"}`}>
                    {s.title}
                  </span>
                </div>
              </button>
            </li>
          );
        })}
      </ol>
    </nav>
  );
}

function PageShell({ title, children }: { readonly title: string; readonly children: React.ReactNode }) {
  return (
    <div className="w-full max-w-3xl mx-auto bg-white rounded-2xl shadow-sm p-6">
      <h2 className="text-2xl font-semibold mb-4">{title}</h2>
      <div className="border border-dashed border-gray-200 rounded-lg p-8 min-h-[220px] flex items-center justify-center">
        {children}
      </div>
    </div>
  );
}

function DetailsPlaceholder() {
  return (
    <PageShell title="Details">
      <div className="text-center">
        <p className="text-gray-500 mb-4">Placeholder for Details form fields (e.g., account name, customer info).</p>
        <div className="w-48 h-24 bg-gray-50 rounded-md flex items-center justify-center text-gray-300">Form fields go here</div>
      </div>
    </PageShell>
  );
}

function TermsPlaceholder() {
  return (
    <PageShell title="Terms">
      <div className="text-center">
        <p className="text-gray-500 mb-4">Placeholder for Terms and Conditions acceptance UI.</p>
        <div className="w-72 h-28 bg-gray-50 rounded-md flex items-center justify-center text-gray-300">Terms content preview</div>
      </div>
    </PageShell>
  );
}

function ChargesPlaceholder() {
  return (
    <PageShell title="Charges">
      <div className="text-center">
        <p className="text-gray-500 mb-4">Placeholder for Charges breakdown (fees, rates).</p>
        <div className="w-64 h-20 bg-gray-50 rounded-md flex items-center justify-center text-gray-300">Charges table placeholder</div>
      </div>
    </PageShell>
  );
}

function PreviewPlaceholder() {
  return (
    <PageShell title="Preview">
      <div className="text-center">
        <p className="text-gray-500 mb-4">Placeholder for final preview of the account creation details.</p>
        <div className="w-full max-w-md bg-gray-50 rounded-md p-4 text-gray-300">Preview summary goes here</div>
      </div>
    </PageShell>
  );
}

export default function AccountCreationStepper({ initial = 0 }: { readonly initial?: number }) {
  const [active, setActive] = useState(initial);

  const goNext = () => setActive((s) => s === STEPS.length - 1 ? 0 : s + 1);
  const goBack = () => setActive((s) => s === 0 ? STEPS.length - 1 : s - 1);
  const jumpTo = (i: number) => setActive(i);

  const renderActive = () => {
    switch (STEPS[active].id) {
      case "details":
        return <DetailsPlaceholder />;
      case "terms":
        return <TermsPlaceholder />;
      case "charges":
        return <ChargesPlaceholder />;
      case "preview":
        return <PreviewPlaceholder />;
      default:
        return null;
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 p-6">
      <div className="max-w-4xl mx-auto">
        <header className="mb-4">
          <h1 className="text-xl font-bold">Create New Account</h1>
          <p className="text-sm text-gray-500">Flow skeleton: Details → Terms → Charges → Preview</p>
        </header>

        <Stepper activeIndex={active} onJump={jumpTo} />

        <main className="mb-6">{renderActive()}</main>

        <footer className="max-w-3xl mx-auto flex items-center justify-between">
          <button
            onClick={goBack}
            className="px-4 py-2 rounded-lg border text-gray-700 border-gray-300 hover:shadow"
          >
            Back
          </button>

          <div className="flex items-center gap-3">
            <span className="text-sm text-gray-500">Step {active + 1} of {STEPS.length}</span>
            <button
              onClick={goNext}
              className="px-4 py-2 rounded-lg bg-indigo-600 text-white font-medium hover:bg-indigo-700"
            >
              Next
            </button>
          </div>
        </footer>
      </div>
    </div>
  );
}
