const Inputs = () => {
    return (
      <>
        <div>
          <label htmlFor="inpCodBarras">Código de Barras:</label>
          <input type="text" name="inpCodBarras" id="inpCodBarras"/>
          <label htmlFor="inpFile">Coloque a sua imagem:</label>
          <input type="file" name="inpFile" id="inpFile"/>
        </div>
      </>
    );
  };
  export default Inputs