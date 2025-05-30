import close from "../assets/Close.svg";
const PopUp = ({ children, onClose }) => {
  onClose = () => {
    document.getElementById("popUp").style.transform = "translateX(-150%)";
  };
  return (
    <div
      className="fixed inset-0 d-flex flex-column align-items-center justify-content-center bg-black bg-opacity-50 z-50 position-absolute vh-100"
      style={{
        zIndex: 1000,
        width: "350px",
        transform: "translateX(-150%)",
        transition: "transform 0.4s ease-in-out",
      }}
      id="popUp"
    >
      <div className="bg-white p-6 shadow-lg relative w-100 ">
        <button
          onClick={onClose}
          className=" position-absolute text-gray-500 hover:text-gray-700 rounded-circle p-0 border-0 "
          style={{ width: "30px", height: "30px", top: "10px", right: "10px" }}
        >
          <img src={close} alt="Close" />
        </button>
        <div>
          <div className="card flex flex-column align-items-center justify-content-around w-100 text-center">
            <div className="card-header bg-primary text-white text-center p-3 w-100">
              <h5>Informações do boleto</h5>
            </div>
            {children}
          </div>
        </div>
      </div>
    </div>
  );
};

export default PopUp;
