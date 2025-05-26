import scanner from "../assets/Scanner.svg";
import scannerWhite from "../assets/ScannerWhite.svg";
import arrow from "../assets/PlayArrow.svg";
import arrowWhite from "../assets/PlayArrowWhite.svg";
// Para acesssar pastas anteriores a pasta que esta sofrendo manutenção não esquecer do ../ ou para os mais leigos PONTO PONTO BARRA.

const Buttons = (props) => {
  const theme = props.theme;
  const scannerImg = theme === "white" ? scanner : scannerWhite;
  const arrowImg = theme === "white" ? arrow : arrowWhite;

  return (
    <>
      <div className="d-flex justify-content-around gap-5 mt-5">
        <button
          id="buttonScanner"
          className="contorno btn btn-light rounded-circle p-3"
        >
          <img src={scannerImg} alt="" />
        </button>
        <button
          id="buttonPlay"
          className="contorno btn btn-light rounded-circle p-3"
          onClick={() => props.verify()}
          type="button"
        >
          <img src={arrowImg} alt="" />
        </button>
      </div>
    </>
  );
};
export default Buttons;
