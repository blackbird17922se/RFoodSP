import React from "react";
import MesaList from "../components/MesaList";
import MesaForm from "../components/MesaForm";

const MesaPage = () => (
    <div>
        <h1>Gestión de Mesas</h1>
        <MesaForm/>
        <MesaList/>
    </div>
);

export default MesaPage;