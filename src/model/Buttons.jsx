import arrow from "../assets/PlayArrow.svg";
import scanner from "../assets/Scanner.svg";

// Para acesssar pastas anteriores a pasta que esta sofrendo manutenção não esquecer do ../ ou para os mais leigos PONTO PONTO BARRA.

const Buttons = () => {
  return (
    <>
      <div className="d-flex justify-content-around gap-5 mt-5">
        <button id="buttonScanner" className="btn btn-light rounded-circle p-3">
          <img src={scanner} alt="" />
        </button>
        <button id="buttonPlay" className="btn btn-light rounded-circle p-3">
          <img src={arrow} alt="" />
        </button>
      </div>
    </>
  );
};
export default Buttons;
