import axios from 'axios';

// Crear una instancia de Axios con la configuración base
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api', // Cambia esto a la URL de tu backend
    timeout: 10000,                      // Tiempo de espera (en milisegundos)
    headers: {
        'Content-Type': 'application/json', // Tipo de contenido predeterminado
    },
});

export default axiosInstance;
