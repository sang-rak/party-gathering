import App from "./App";
import React from "react";
import ReactDOM from "react-dom/client";
import ReactModal from "react-modal";
import { BrowserRouter } from "react-router-dom";
import { GlobalStyle } from "./styles/GlobalStyle";

ReactModal.setAppElement("#root");

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter>
    <GlobalStyle />

    <App />
  </BrowserRouter>
);
