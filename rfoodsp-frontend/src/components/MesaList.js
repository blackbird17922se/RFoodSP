/* useState y useEffect son hooks de React que se utilizan para manejar el estado 
* y los efectos secundarios (como obtener datos de un servidor).*/
import React, {useEffect, useState} from "react";
import { obtenerMesas } from "../api/mesasService";

/** componente MesaList.js
* Para listar todas las mesas. 
*/
// Definición del Componente
function MesaList() {
// const MesaList = () => {

    /** hook useState:
        * mesas es el estado que almacena la lista de mesas que obtendrás desde tu backend.
        * setMesas es una función que te permite actualizar ese estado.
        * useState([]) inicializa mesas como un array vacío []. */
    const[mesas, setMesas] = useState([]);



    /** hook useEffect:
    * useEffect se utiliza para realizar efectos secundarios en el componente, como 
    * obtener datos del backend cuando el componente se monta por primera vez. */
    useEffect(() => {

        // La función fetchMesas:
        const fetchMesas = async () => {

            try {

                /* Llama a la función obtenerMesas() que hiciste en tu servicio para 
                * obtener los datos desde el backend. */
                const datos = await obtenerMesas();

                /* Una vez que los datos son obtenidos (await obtenerMesas()), se 
                * almacenan en el estado mesas mediante setMesas(datos). */
                setMesas(datos);

            } catch (error) {
                console.error('Error al obtener mesas:', error); 
            }
        };
        fetchMesas();
    }, 
    /* Dependencias ([]):
    * El segundo argumento que pasas al useEffect es un array de dependencias.
    * Si el array está vacío [], el efecto solo se ejecutará una vez cuando el 
    * componente se monte, como un componentDidMount: */
    []);


    // Renderización del Componente (return)
    // Aquí estás renderizando lo que aparecerá en la página:
    return(
        <div>
            <h2>Lista de Mesas</h2>

            {/* {mesas.map(...)}: Itera sobre el array mesas que obtuviste del backend. */}
            {mesas.map((mesa) => (

                // Para cada mesa, se crea un <div>
                // key={mesa.idMesa}: Es un identificador único que React necesita 
                // para optimizar el renderizado de listas.
                <div key={mesa.idMesa}>
                    <p>Nombre: {mesa.nombre}</p>
                    <p>Disponible: {mesa.disponible ? 'Sí' : 'No'}</p>
                </div>
            ))}
        </div>
    );
};

// Exportación del Componente
export default MesaList;