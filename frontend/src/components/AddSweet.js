import { useContext, useState } from "react";
import { addSweet } from "../api";
import { AuthContext } from "../AuthContext";

export default function AddSweet() {
  const { token } = useContext(AuthContext);
  const [sweet, setSweet] = useState({ name: "", category: "", price: 0, quantityInStock: 0 });

  const submit = async () => {
    await addSweet(sweet, token);
    window.location.reload();
  };

  return (
    <div className="card">
      <h2>Add Sweet (Admin)</h2>
      <input placeholder="Name" onChange={e=>setSweet({...sweet,name:e.target.value})}/>
      <input placeholder="Category" onChange={e=>setSweet({...sweet,category:e.target.value})}/>
      <input placeholder="Price" onChange={e=>setSweet({...sweet,price:e.target.value})}/>
      <input placeholder="Quantity" onChange={e=>setSweet({...sweet,quantityInStock:e.target.value})}/>
      <button onClick={submit}>Add</button>
    </div>
  );
}
