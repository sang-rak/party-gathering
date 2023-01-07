import "bootstrap/dist/css/bootstrap.min.css";
import * as Pages from "./pages";
import { Route, Routes } from "react-router-dom";
import Nav from "./components/common/Nav";
import Footer from "./components/common/Footer";

function App() {
  return (
    <>
      {/* 로그인 회원가입 페이지 Nav 제거 */}
      {window.location.pathname === "/" ||
        window.location.pathname === "/signup" || (
          <>
            <Nav />
          </>
        )}
      <Routes>
        <Route path="/" element={<Pages.LoginPage />} />
        <Route path="/signup" element={<Pages.SignupPage />} />
        <Route path="/tos" element={<Pages.TosPage />} />
        <Route path="/chat" element={<Pages.ChatPage />} />
        <Route path="/account" element={<Pages.AccountPage />} />
      </Routes>
      {/* 로그인 회원가입 페이지 Footer 제거 */}
      {window.location.pathname === "/" ||
        window.location.pathname === "/signup" || (
          <>
            <Footer />
          </>
        )}
    </>
  );
}

export default App;
