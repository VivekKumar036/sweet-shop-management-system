import { useContext, useEffect, useState } from "react";
import { getAllSweets } from "../api";
import { AuthContext } from "../AuthContext";
import SweetCard from "./SweetCard";

export default function SweetList() {
  const { token } = useContext(AuthContext);
  const [sweets, setSweets] = useState([]);

  const loadSweets = () => {
    getAllSweets(token).then(setSweets);
  };

  useEffect(() => {
    loadSweets();
  }, [token]);

  return (
    <div className="card">
      <h2>Available Sweets</h2>
      {sweets.map(s => (
        <SweetCard
          key={s.id}
          sweet={s}
          onRefresh={loadSweets}
        />
      ))}
    </div>
  );
}
