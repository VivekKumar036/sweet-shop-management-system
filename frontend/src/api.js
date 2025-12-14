const BASE_URL = "http://localhost:8080/api";

// ---------- AUTH ----------
export async function loginUser(data) {
  const res = await fetch(`${BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });

  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function registerUser(data) {
  const res = await fetch(`${BASE_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });

  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

// ---------- SWEETS ----------
export async function getAllSweets() {
  const res = await fetch(`${BASE_URL}/sweets`);
  return res.json();
}

export async function addSweet(data, token) {
  const res = await fetch(`${BASE_URL}/sweets`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function purchaseSweet(id, token) {
  const res = await fetch(`${BASE_URL}/sweets/${id}/purchase`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return res.json();
}
