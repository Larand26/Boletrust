import { useState } from "react";
import logo from "./assets/Logo.png";
import Inputs from "./model/Inputs.jsx";
import Buttons from "./model/Buttons.jsx";
import "./estilo/Estilo.css";

const App = () => {
  const [theme, setTheme] = useState("white"); 

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === "white" ? "dark" : "white")); 
  };

  return (
    <div className={`app ${theme} d-flex w-100 vh-100 flex-column align-items-center justify-content-around`}>
      <img style={{ maxWidth: "150px" }} src={logo} alt="Logo" />
      <form>
        <Inputs />
        <Buttons />
      </form>
      <button onClick={toggleTheme}>
        Alternar para {theme === "white" ? "Modo Escuro" : "Modo Claro"}
      </button>
    </div>
  );
};

export default App;

