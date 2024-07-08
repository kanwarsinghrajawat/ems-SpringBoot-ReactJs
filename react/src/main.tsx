import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import PrelineScript from "./components/PrelineScript";
import ReduxProvider from "./redux/ReduxProvider";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <ReduxProvider>
      <App />
    </ReduxProvider>
    {/* <PrelineScript /> */}
  </React.StrictMode>
);
