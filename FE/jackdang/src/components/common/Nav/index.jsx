import { memo } from 'react';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';


const Nav = () => {
  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="/">작당모임</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <li href="/">로그인</li>
          <li href="/logout">로그아웃</li>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};
export default memo(Nav);