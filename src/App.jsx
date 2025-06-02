import { useState } from "react";
import logo from "./assets/Logo.png";
import Inputs from "./model/Inputs.jsx";
import Buttons from "./model/Buttons.jsx";
import readBarrCode from "./utils/readBarrCode.js";
import PopUp from "./model/PopUp.jsx";
import "./estilo/Estilo.css";
import sol from "./assets/Sol.svg";
import lua from "./assets/Lua.svg";

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
    let data;

    if (text) {
      data = readBarrCode
        .post("/txt-verify", {
          cod: text,
        })
        .then((res) => {
          console.log(res.data);
          document.getElementById("pBanco").innerText = res.data.banco;
          document.getElementById("pDataVencimento").innerText =
            res.data.vencimento;
          document.getElementById("pValor").innerText = "R$ " + res.data.valor;
          document.getElementById("pCodigoBarras").innerText = res.data.cod;
        });
    }
    if (!text && img) {
      const fileInput = document.getElementById("inpFile");
      const file = fileInput.files[0];

      const reader = new FileReader();

      reader.onloadend = () => {
        const base64String = reader.result;

        data = readBarrCode
          .post("/img-verify", {
            img: base64String,
          })
          .then((res) => {
            console.log(res.data);
            document.getElementById("pBanco").innerText =
              res.data.banco || "Banco não identificado";
            document.getElementById("pDataVencimento").innerText =
              res.data.vencimento || "Data não identificada";
            document.getElementById("pValor").innerText =
              "R$ " + res.data.valor || "Valor não identificado";
            document.getElementById("pCodigoBarras").innerText =
              res.data.cod || "Código de barras não identificado";
          });
      };

      reader.readAsDataURL(file); // Isso converte para base64
    }
    document.getElementById("popUp").style.transform = "translateX(0)";
  };

  const solImg = <img src={sol} alt="Sol" width="24" />;
  const luaImg = <img src={lua} alt="Lua" width="24" />;

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
        {theme === "white" ? luaImg : solImg}
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
      <PopUp onClose={() => {}}>
        <div>
          <p>Banco</p>
          <p id="pBanco" className="font-weight-bold"></p>
        </div>
        <div>
          <p>Data de vencimento</p>
          <p id="pDataVencimento" className="font-weight-bold"></p>
        </div>
        <div>
          <p>Valor</p>
          <p id="pValor" className="font-weight-bold"></p>
        </div>
        <div>
          <p>Código de barras</p>
          <p id="pCodigoBarras" className="font-bold text-break"></p>
        </div>
      </PopUp>
    </div>
  );
};

export default App;
