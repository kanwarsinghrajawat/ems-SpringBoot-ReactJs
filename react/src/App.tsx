import { BrowserRouter, Routes, Route } from "react-router-dom";

import EmsTable from "./pages/EmsTable";
import SignUp from "./pages/SignUp";
import SignIn from "./pages/SignIn";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<SignIn />}></Route>
          <Route path="/signup" element={<SignUp />}></Route>
          <Route path="/table" element={<EmsTable />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}
export default App;
