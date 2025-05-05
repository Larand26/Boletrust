import { useState } from "react";
import logo from "./assets/Logo.png";
import Inputs from "./model/Inputs.jsx";
import Buttons from "./model/Buttons.jsx";
import "./estilo/Estilo.css";

const App = () => {
  const [theme, setTheme] = useState("white");

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === "white" ? "dark" : "white"));
    fetch("http://localhost:8080/hello-word")
      .then((res) => res.text())
      .then((data) => console.log(data))
      .catch((err) => console.error(err));
  };

  return (
    <div
      className={`app ${theme} d-flex w-100 vh-100 flex-column align-items-center justify-content-around`}
    >
      <img style={{ maxWidth: "150px" }} src={logo} alt="Logo" />
      <form className="contorno p-3">
        <Inputs />
        <Buttons theme={theme} />
      </form>

      <button className="contorno" onClick={toggleTheme}>
        Alternar para {theme === "white" ? "Modo Escuro" : "Modo Claro"}
      </button>
      {/* Luas aparecem apenas no modo escuro */}
      {theme === "dark" && (
        <div className="luas">
          <div className="grupo-esquerdo">
            {[...Array(1)].map((_, i) => (
              <div key={i} className="lua linha"></div>
            ))}
          </div>
          <div className="grupo-direito">
            {[...Array(0)].map((_, i) => (
              <div key={i} className="lua linha"></div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default App;
