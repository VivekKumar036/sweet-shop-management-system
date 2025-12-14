import { useState } from "react";
import { registerUser } from "../api";   // ✅ CORRECT

export default function Register() {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleRegister = async () => {
    try {
      await registerUser({ fullName, email, password });
      alert("Registered successfully! Now login.");
    } catch (err) {
      alert(err.message || "Registration failed");
    }
  };

  return (
    <>
      <h2>Register</h2>
      <input placeholder="Full Name" onChange={(e) => setFullName(e.target.value)} />
      <input placeholder="Email" onChange={(e) => setEmail(e.target.value)} />
      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleRegister}>Register</button>
    </>
  );
}
