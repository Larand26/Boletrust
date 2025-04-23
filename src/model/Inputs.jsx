const Inputs = () => {
    return (
      <>
        <div className="d-flex flex-column align-items-center justify-content-around">
          <label htmlFor="inpCodBarras">Código de Barras:</label>
          <input type="text" name="inpCodBarras" id="inpCodBarras"/>
          <label htmlFor="inpFile">Coloque a sua imagem:</label>
          <input type="file" name="inpFile" id="inpFile"/>
        </div>
      </>
    );
  };
  export default Inputs