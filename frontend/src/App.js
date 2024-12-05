import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import MainPage from './components/MainPage';
// import PrivateRoute from './components/PrivateRoute';

function App() {
  return (
    <Router>
      <Routes>
      <Route path="/login" element={<Login />} />  {/* Cambiar component por element */}
        <Route path="/main" element={<MainPage />} /> {/* Cambiar component por element */}
        <Route exact path="/" element={<Login />} />
        {/* <PrivateRoute path="/main" component={MainPage} /> mas adelante para privar ruta si no hay token*/}

      </Routes>
    </Router>
  );
}

export default App;
