import React from 'react';
import { Link } from 'react-router-dom';

function MainPage() {
  return (
    <div>
      <h1>Bienvenido a RFoodSP</h1>
      <h3>Version 1.0</h3>
      <ul>
        <li><a href="">Ordenes</a></li>
        <li><a href="">Productos</a></li>
        <li><a href="">Ventas</a></li>
        <li><a href="">Usuarios</a></li>
        <li><Link to="/mesas">Mesas</Link></li>
    </ul>
    <p>RFoodSP Para Windows 98 - DsD 2024</p>
    </div>
  );
}

export default MainPage;
