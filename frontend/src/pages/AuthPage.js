import { useState } from "react";
import Login from "./Login";
import Register from "./Register";

export default function AuthPage() {
  const [isLogin, setIsLogin] = useState(true);

  return (
    <div className="auth-container">
      <h1>🍬 Sweet Shop Management System</h1>

      <div className="auth-box">
        {isLogin ? <Login /> : <Register />}

        <p style={{ marginTop: "10px" }}>
          {isLogin ? "New user?" : "Already registered?"}{" "}
          <button onClick={() => setIsLogin(!isLogin)}>
            {isLogin ? "Register" : "Login"}
          </button>
        </p>
      </div>
    </div>
  );
}
