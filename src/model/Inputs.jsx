const Inputs = () => {
  return (
    <>
      <div className="d-flex flex-column align-items-center justify-content-around">
        <label htmlFor="inpCodBarras" className="fs-5">
          Código de Barras:
        </label>
        <input
          type="text"
          name="inpCodBarras"
          id="inpCodBarras"
          className="contorno p-2 mt-2"
        />
        <label htmlFor="inpFile" className="fs-5 mt-3">
          Coloque a sua imagem:
        </label>
        <input
          type="file"
          name="inpFile"
          id="inpFile"
          className="contorno input-file p-2 mt-2"
        />
      </div>
    </>
  );
};
export default Inputs;
