import arrow from "../assets/PlayArrow.svg"
import scanner from "../assets/Scanner.svg"

// Para acesssar pastas anteriores a pasta que esta sofrendo manutenção não esquecer do ../ ou para os mais leigos PONTO PONTO BARRA.

const Buttons = () => {
  return (
    <>
      <div>
        <button id="buttonScanner">
          <img src={scanner} alt= "" />
        </button>
        <button id="buttonPlay">
          <img src={arrow} alt= "" />
        </button>
      </div>
    </>
  );
};
export default Buttons
