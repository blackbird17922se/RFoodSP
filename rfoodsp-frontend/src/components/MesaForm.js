import React, {useState} from "react";
import { crearMesa } from "../api/mesasService";


function MesaForm(){

    const [nombre, setNombre] = useState('');


    async function  handleSubmit(event) {
        event.preventDefault();
        
        try {
            await crearMesa({ nombre, disponible: true});
            alert('Mesa creada con éxito');
        } catch (error) {
            console.error('Error al crear mesa:', error);
        }
    };


    return(
        <form onSubmit={handleSubmit}>
            <input
            type="text"
            placeholder="Nombre de la mesa"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            />
            <button type="submit">Crear Mesa</button>
        </form>
    );
};

export default MesaForm;