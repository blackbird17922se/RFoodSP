import React, { useState } from 'react';
import axios from '../api/axiosConfig';
import './Login.css';
import { useNavigate } from 'react-router-dom';  // Importar useNavigate en lugar de useHistory


function Login() {
  const [nomUsuario, setNomUsuario] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate(); // Hook para redirigir

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/login', {
        nomUsuario,
        contrasena,
      });

      // Si el login es exitoso, redirige al usuario a la página principal
      if (response.status === 200) {
        // Puedes guardar el token en localStorage o estado global si es necesario
        localStorage.setItem('token', response.data.token); // Por ejemplo
        navigate('/main');  // Redirigir a la página principal
      }

    } catch (err) {
        setError('Usuario o contraseña incorrectos');
        console.error('Error en el login:', err);  // Cambié 'error' por 'err'
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleLogin}>
        <h2>Iniciar Sesión</h2>
        {error && <p className="error">{error}</p>}
        <div className="form-group">
          <label htmlFor="nomUsuario">Usuario</label>
          <input
            type="text"
            id="nomUsuario"
            value={nomUsuario}
            onChange={(e) => setNomUsuario(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="contrasena">Contraseña</label>
          <input
            type="password"
            id="contrasena"
            value={contrasena}
            onChange={(e) => setContrasena(e.target.value)}
            required
          />
        </div>
        <button type="submit">Ingresar</button>
      </form>
    </div>
  );
};

export default Login;
