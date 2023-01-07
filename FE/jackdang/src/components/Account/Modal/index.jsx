import ReactModal from "react-modal";
import Button from "react-bootstrap/Button";
import React, { useState } from "react";
import { Col, Form, Row } from "react-bootstrap";
import axios from "../../../api/axios";
import { useEffect } from "react";

const MyModal = ({ isOpen, onSubmit, likearr, setLikearr }) => {
  useEffect(() => {
    initInterest(); // 관심사 동기화
  });

  // 관심사 목록
  const [likelist, setLikelist] = useState([
    {
      id: 1,
      icon: "🙋‍♀",
      interestNm: "동네친구",
      description: "심심해? 지금 바로 만날까?",
      status: false,
    },
    {
      id: 2,
      icon: "☕",
      interestNm: "카페투어",
      description: "커피 맛집부터 인증샷 카페까지!",
      status: false,
    },
    {
      id: 3,
      icon: "👕",
      interestNm: "데일리룩",
      description: "옷 사느라 텅장 되는 사람?",
      status: false,
    },
    {
      id: 4,
      icon: "🍴",
      interestNm: "맛집투어",
      description: "맛있는 음식 먹을 때 찐행복!",
      status: false,
    },
    {
      id: 5,
      icon: "👟",
      interestNm: "운동 챌린지",
      description: "땀 흘리며 건강 레벨업",
      status: false,
    },
    {
      id: 6,
      icon: "🥗",
      interestNm: "다이어트",
      description: "오늘도 배고픔을 참습니다",
      status: false,
    },
    {
      id: 7,
      icon: "📺︎",
      interestNm: "넷플릭스",
      description: "자기 전 넷플릭스가 취미",
      status: false,
    },
    {
      id: 8,
      icon: "🐕︎",
      interestNm: "강아지",
      description: "멍멍~ 내강아지가 제일 귀여워!",
      status: false,
    },
  ]);

  const InterestData = async (interestNm) => {
    // 회원 관심사 삭제
    if (likearr.includes(interestNm)) {
      try {
        // 관심사 있을경우 삭제
        const id = "1";
        const response = await axios.delete(
          "/api/v1/interest/" + id + "/" + interestNm
        );
        console.log(response);
        onRemoveInterest(interestNm); // 해당 관심사 이름 삭제
        onToggle(interestNm);
      } catch (error) {
        // 응답 실패
        console.log(error);
        alert("응답 실패");
      }
    } else {
      // 회원 관심사 추가
      try {
        // 이미 관심사 추가했을 경우 관심사 해제처리
        const id = "1";
        const response = await axios.post("/api/v1/interest", {
          //보내고자 하는 데이터
          interestNm: interestNm,
          memberId: id,
        });
        console.log(response);
        likearr.push(interestNm);
        onToggle(interestNm);
      } catch (error) {
        // 응답 실패
        console.log(error);
        alert("응답 실패");
      }
    }
  };

  // 해당 관심사 이름 삭제
  const onRemoveInterest = (interestNm) => {
    const newLikearr = likearr.filter((like) => like !== interestNm);
    setLikearr(newLikearr);
  };

  // 관심사 동기화
  const initInterest = async () => {
    for (let i = 0; i < likelist.length; i++) {
      if (likearr.includes(likelist[i].interestNm)) {
        likelist[i].status = true;
        console.log(likelist[i].status);
      }
    }
  };

  // 관심사 상태값 토글
  const onToggle = async (interestNm) => {
    setLikelist(
      likelist.map((like) =>
        like.interestNm === interestNm
          ? { ...like, status: !like.status }
          : like
      )
    );
  };

  // 확인 후 창닫기
  const handleClickSubmit = () => {
    onSubmit();
  };

  const Modalpage = () => {
    return (
      <Form>
        <Row>
          <Col>
            <Form.Label column="lg">관심사 선택하기</Form.Label>
          </Col>
          <Col>
            <Button variant="secondary" onClick={handleClickSubmit}>
              확인
            </Button>
          </Col>
        </Row>
        <hr></hr>
        {likelist.map((like) => (
          <Row key={like.id} className="my-4">
            <Col>
              <Row>{like.icon}</Row>
            </Col>
            <Col>
              <Row>{like.interestNm}</Row>
              <Row>{like.description}</Row>
            </Col>
            <Col>
              {like.status ? (
                <Button
                  variant="outline-primary"
                  onClick={() => {
                    InterestData(like.interestNm);
                  }}
                >
                  해제하기
                </Button>
              ) : (
                <Button
                  variant="primary"
                  onClick={() => {
                    InterestData(like.interestNm);
                  }}
                >
                  선택하기
                </Button>
              )}
            </Col>
          </Row>
        ))}
      </Form>
    );
  };
  return <ReactModal isOpen={isOpen}>{Modalpage()}</ReactModal>;
};

export default MyModal;
