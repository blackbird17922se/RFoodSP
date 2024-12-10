import React, { useState, useEffect } from 'react';
import axios from '../api/axiosConfig';
import './Mesas.css'; // Opcional para estilos

function Mesas() {
  const [mesas, setMesas] = useState([]); // Lista de mesas
  const [nombre, setNombre] = useState('');
  const [disponible, setDisponible] = useState(true);
  const [error, setError] = useState('');

    //******* estados para saber si esta creando o editando *****/ 
    // indica si el formulario está en modo edición.
    const [isEditar, setIsEditar] = useState(false);

    // contiene los datos de la mesa seleccionada para editar
    const[mesaSelect, setMesaSelect] = useState(null);


  // Obtener las mesas al cargar el componente
  useEffect(() => {
    fetchMesas();
  }, []);

  const fetchMesas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/mesas'); // Endpoint de tu backend para obtener mesas
      setMesas(response.data);
    } catch (err) {
      console.error('Error al obtener las mesas:', err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if(isEditar){
        // ojooooo, idMesa es por el nombre de la propiedad en el dto!!
        await axios.put(`http://localhost:8080/mesas/editar?id=${mesaSelect.idMesa}`, {
            nombre,
            disponible,
        });
        alert("Mesa actualizada");
    } else{

        if (!nombre.trim()) {
            setError('El nombre de la mesa es obligatorio.');
            return;
        }
        setError('');
        try {
        const newMesa = { nombre, disponible };
        const response = await axios.post('http://localhost:8080/mesas', newMesa); // Endpoint para crear mesas
        setMesas([...mesas, response.data]); // Agregar la nueva mesa a la lista
        setDisponible(true);
        alert("Mesa creada");
        } catch (err) {
        console.error('Error al crear la mesa:', err);
        }

    };
           // Reinicia los estados
           setIsEditar(false);
           setMesaSelect(null);
           setNombre("");
           setDisponible(true);
           fetchMesas(); // Llama una función para actualizar la tabla

  };


 /** evento de editar
    Cuando se haga clic en "Editar", configura el estado para activar el modo edición 
     y carga los datos de la mesa seleccionada en el formulario. 
 */
 const handelEdit = (mesa) => {
    setIsEditar(true);
    setMesaSelect(mesa);
    setNombre(mesa.nombre);
    setDisponible(true); // ToDo: pendiente agregar a back cambiar estado
 };

 const cancelEdit = () => {
    setIsEditar(false);
    setMesaSelect(null);
    setNombre("");
    setDisponible(true);
 }




  return (
    <div className="mesas-container">
      <h2>Gestión de Mesas</h2>
      
      {/* Formulario para crear mesa */}
      <form onSubmit={handleSubmit} className="mesas-form">
        <h2>{isEditar ? "Editar Mesa" : "Crear Mesa"}</h2>
        <div>
          <label htmlFor="nombre">Nombre de la Mesa:</label>
          <input
            type="text"
            id="nombre"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="disponible">Disponible:</label>
          <select
            id="disponible"
            value={disponible}
            onChange={(e) => setDisponible(e.target.value === 'true')}
          >
            <option value="true">Sí</option>
            <option value="false">No</option>
          </select>
        </div>
        <button type="submit">{isEditar ? "Actualizar" : "Agregar Mesa"}</button>
        {isEditar && (<button onClick={cancelEdit} type='button'> Cancelar</button>)}
        {error && <p className="error">{error}</p>}
      </form>
      
      {/* Tabla para mostrar mesas */}
      <table className="mesas-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Disponible</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {mesas.length > 0 ? (
            mesas.map((mesa) => (
              <tr key={mesa.idMesa}>
                <td>{mesa.idMesa}</td>
                <td>{mesa.nombre}</td>
                <td>{mesa.disponible ? 'Sí' : 'No'}</td>
                <td>
                    <button onClick={() => handelEdit(mesa)}>Editar</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="3">No hay mesas registradas.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Mesas;
