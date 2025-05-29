import { useState } from "react";
import logo from "./assets/Logo.png";
import Inputs from "./model/Inputs.jsx";
import Buttons from "./model/Buttons.jsx";
import readBarrCode from "./utils/readBarrCode.js";
import "./estilo/Estilo.css";

const App = () => {
  const [theme, setTheme] = useState("white");

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === "white" ? "dark" : "white"));
    fetch("http://localhost:8080/hello-world")
      .then((res) => res.text())
      .then((data) => console.log(data))
      .catch((err) => console.error(err));
  };

  const verify = () => {
    const text = document.getElementById("inpCodBarras").value;
    const img = document.getElementById("inpFile").value;

    if (text && !img) {
      readBarrCode
        .post("/txt-verify", {
          cod: text,
        })
        .then((res) => {
          console.log(res.data);
        });
    }
    if (!text && img) {
      const fileInput = document.getElementById("inpFile");
      const file = fileInput.files[0];

      const reader = new FileReader();

      reader.onloadend = () => {
        const base64String = reader.result;

        readBarrCode
          .post("/img-verify", {
            img: base64String,
          })
          .then((res) => {
            console.log(res.data);
          });
      };

      reader.readAsDataURL(file); // Isso converte para base64
    }
  };

  return (
    <div
      className={`app ${theme} d-flex vh-100 flex-column align-items-center justify-content-around`}
      style={{ width: "400px" }}
    >
      <img style={{ maxWidth: "150px" }} src={logo} alt="Logo" />
      <form className="contorno p-3">
        <Inputs />
        <Buttons theme={theme} verify={() => verify()} />
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
