import { StyledTitle } from "./styles";
import { memo } from "react";

import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { useEffect, useState, useRef } from "react";
import axios from "../../api/axios";
import AuthContext from "../../context/AuthProvider";
import { useNavigate } from "react-router-dom";

const LOGIN_URL = "/api/excludePath/login";
const Login = () => {
  const phoneRef = useRef();

  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");
  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    phoneRef.current.focus();
  }, []);

  // phone, password 입력하면 에러메시지 비우기
  useEffect(() => {
    setErrMsg("");
  }, [phone, password]);

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        LOGIN_URL,
        JSON.stringify({ phone, password })
      );

      console.log(JSON.stringify({ phone, password }));
      console.log(JSON.stringify(response));
      console.log(JSON.stringify(response?.data?.status).replace(/\"/gi, ""));

      if (JSON.stringify(response?.data?.status).replace(/\"/gi, "") === "S") {
        // setPhone('');
        // setPassword('');
        setSuccess(true);
        navigate("/chat");
        window.location.reload();

        alert("로그인 성공");
      } else {
        setErrMsg("전화번호 또는 비밀번호를 다시 확인해주세요");
        setSuccess(false);
        alert(errMsg);
        console.log("전화번호 또는 비밀번호를 다시 확인해주세요");
      }
    } catch (err) {
      setErrMsg("서버가 응답하지 않습니다");
      setSuccess(false);
      alert(errMsg);
      console.log("서버가 응답하지 않습니다");
    }
  };

  return (
    <Container>
      <div className="my-3">
        <StyledTitle className="text-center">작당</StyledTitle>
        <StyledTitle className="text-center">모임</StyledTitle>
      </div>

      <Form onSubmit={handleLogin}>
        <Form.Group className="mb-3" controlId="formBasicCall">
          <Form.Control
            type="call"
            placeholder="전화번호"
            ref={phoneRef}
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Control
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicCheckbox">
          <Row>
            <Col>
              <Form.Check type="checkbox" label="자동 로그인" />
            </Col>
            <Col>
              <p> 비밀번호 찾기</p>
            </Col>
          </Row>
        </Form.Group>
        <Row>
          <Button variant="secondary" type="submit">
            로그인하기
          </Button>
        </Row>
        <Row>
          <Form.Text value={errMsg} id="errMsg" />
        </Row>
        <Row>
          <Button variant="light" href="/signup">
            작당모임 회원이 아닌가요?
          </Button>
        </Row>
      </Form>
    </Container>
  );
};

export default memo(Login);
