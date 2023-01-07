import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { memo } from "react";
import { useNavigate } from "react-router-dom";
import {
  faHouse,
  faMagnifyingGlass,
  faUser,
  faComment,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
import { Col, Container, Row } from "react-bootstrap";
const Footer = () => {
  const navigate = useNavigate();

  const HomePageChange = () => {
    navigate("/home"); // 리뷰관리 및 기타 페이지
  };
  const SearchPageChange = () => {
    navigate("/search"); // 다른 사람들 마이페이지 프로필 구경
  };
  const RecommendPageChange = () => {
    navigate("/rec"); // 랜덤 추천 페이지
  };
  const ChatPageChange = () => {
    navigate("/chat"); // 채팅페이지
  };
  const MyPageChange = () => {
    navigate("/account"); // 마이페이지
  };

  return (
    <footer>
      <Container className="blockquote-footer">
        <Row>
          <Col>
            <FontAwesomeIcon icon={faHouse} onClick={HomePageChange} />
          </Col>
          <Col>
            <FontAwesomeIcon
              icon={faMagnifyingGlass}
              onClick={SearchPageChange}
            />
          </Col>
          <Col>
            <FontAwesomeIcon icon={faPlus} onClick={RecommendPageChange} />
          </Col>
          <Col>
            <FontAwesomeIcon icon={faComment} onClick={ChatPageChange} />
          </Col>
          <Col>
            <FontAwesomeIcon icon={faUser} onClick={MyPageChange} />
          </Col>
        </Row>
      </Container>
    </footer>
  );
};
export default memo(Footer);
