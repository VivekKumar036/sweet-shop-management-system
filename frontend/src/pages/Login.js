import { useState, useContext } from "react";
import { AuthContext } from "../AuthContext";
import { loginUser } from "../api";   // ✅ CORRECT
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const res = await loginUser({ email, password });
      login(res.token);
      navigate("/dashboard");
    } catch (err) {
      alert(err.message || "Invalid credentials");
    }
  };

  return (
    <>
      <h2>Login</h2>
      <input placeholder="Email" onChange={(e) => setEmail(e.target.value)} />
      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
    </>
  );
}
