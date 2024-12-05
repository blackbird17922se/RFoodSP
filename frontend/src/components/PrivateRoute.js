import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';  // Usar Navigate en lugar de Redirect

const PrivateRoute = ({ children, isAuthenticated }) => {
  let location = useLocation();  // Obtiene la ubicación actual

  if (!isAuthenticated) {
    // Si no está autenticado, redirige a la página de login
    return <Navigate to="/login" state={{ from: location }} />;
  }

  return children;  // Si está autenticado, renderiza los niños (el contenido protegido)
};

export default PrivateRoute;
