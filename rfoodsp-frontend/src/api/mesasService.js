import axiosInstance from "./axiosConfig";

/* servicio de Mesas (mesasService.js)
* Aquí escribiremos las funciones que consumirán los endpoints del backend. */

// export const obtenerMesas = async () => {
//     const response = await axiosInstance.get('/mesas');
//     return response.data;
// };

export async function obtenerMesas() {
    const response = await axiosInstance.get('/mesas');
    return response.data;
}


// obtenerMesaPorNombre 
export async function obtenerMesaPorNombre (nombre){
    const response = await axiosInstance.get(`/mesas/nombre?nombre=${nombre}`)
    return response.data;
}

// crearMesa
export async function crearMesa(datos){
    const response = await axiosInstance.post('/mesas', datos);
    return response.data;
}