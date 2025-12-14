import { useContext } from "react";
import { AuthContext } from "../AuthContext";
import { purchaseSweet } from "../api";

export default function SweetCard({ sweet, onRefresh }) {
  const { token } = useContext(AuthContext);

  const buySweet = async () => {
    await purchaseSweet(sweet.id, 1, token);
    onRefresh();
  };

  return (
    <div className="sweet-card">
      <h4>{sweet.name}</h4>
      <p>Category: {sweet.category}</p>
      <p>Price: ₹{sweet.price}</p>
      <p>Stock: {sweet.quantityInStock}</p>

      <button
        onClick={buySweet}
        disabled={sweet.quantityInStock === 0}
      >
        {sweet.quantityInStock === 0 ? "Out of Stock" : "Buy"}
      </button>
    </div>
  );
}
