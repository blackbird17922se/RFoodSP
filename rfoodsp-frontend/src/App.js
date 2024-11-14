import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MesaPage from "./pages/MesasPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MesaPage/>}/>
      </Routes>
    </Router>
    
  );
}

export default App;
