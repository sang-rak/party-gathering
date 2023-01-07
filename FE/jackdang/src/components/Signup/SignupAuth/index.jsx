import { StyledTitle } from './styles';
import { memo } from 'react';

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const SignupAuth = () => {
  return (
    
    <Container>
      <div className = "my-3">
        <StyledTitle className="text-center">작당</StyledTitle>
        <StyledTitle className="text-center">모임</StyledTitle>
      </div>

      <Form>
        
        <Form.Group className="mb-3" controlId="formBasicCall">
          <Form.Control type="call" placeholder="전화번호" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Control type="password" placeholder="비밀번호" />
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
          <Button variant="light" href="/signup">
            작당모임 회원이 아닌가요?
          </Button>
        </Row>
      </Form>
    </Container>
  );
};

export default memo(SignupAuth);
