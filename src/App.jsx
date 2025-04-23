import logo from "./assets/Logo.png"
import Inputs from "./model/Inputs.jsx"
import Buttons from "./model/Buttons.jsx"

const App = () => {

  return (
    <div className="d-flex w-100 vh-100 flex-column align-items-center justify-content-around">
      <img style={{maxWidth:"150px"}} src= {logo}/> 
      <Inputs/>
      <Buttons/>
    </div>
  );
};

export default App;
