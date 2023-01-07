import { memo, useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import axios from "../../api/axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faChevronDown,
  faPenToSquare,
  faX,
} from "@fortawesome/free-solid-svg-icons";
import { Button, Col, Form, Image, Row } from "react-bootstrap";
import MyModal from "./Modal";
const Account = () => {
  const Account_URL = "";
  const [pagestatus, setPagestatus] = useState(""); // 화면 상태 저장
  const [likearr, setLikearr] = useState([]);
  const [address, setAddress] = useState("");
  const [age, setAge] = useState("");
  const [gender, setGender] = useState("");
  const [job, setJob] = useState("");
  const [school, setSchool] = useState("");
  const [mbti, setMbti] = useState("");
  const [love_status, setLove_status] = useState("");
  const [religion, setReligion] = useState("");
  const [introduce, setIntroduce] = useState("안녕하세요");
  const [interestNm, setInterestNm] = useState("");
  const [nickname, setNickname] = useState("");
  const [editUse, setEditUse] = useState(true); // 편집아이콘 여부
  const [isOpen, setOpen] = useState(false); // 약관동의 모달 핸들링
  // 관심사 리스트
  // const [users, setUsers] = useState([]);

  // 리랜더링되면 실행
  useEffect(() => {
    searchUserData(); // 회원정보 조회
    searchInterestData(); // 관심사 조회
  });

  // 약관동의 Modal
  const handleClick = () => {
    setOpen(true);
  };

  // 관심사 확인
  const searchInterestData = async () => {
    // 추후 개발
    try {
      // 임시 1번 회원 테스트
      const id = "1";
      likearr.length = 0; // 리스트 비우기
      // 세션 회원 확인
      const request = await axios.get(`/api/v1/interest/${id}`);
      for (let i = 0; i < request.data.count; i++) {
        likearr.push(request.data.data[i].interestNm);
      }
    } catch (error) {
      // 응답 실패 (로그아웃상태)
      alert("로그인 후 사용가능합니다.");
    }
  };
  // 회원 확인
  const searchUserData = async () => {
    // 추후 개발
    try {
      // 세션 회원 확인
      const id = "1";
      // const request = await axios.get(requests.fetchUsers);
      const request = await axios.get(`/api/v1/member/${id}`);
      // setUsers(request.data.results);
      setAddress(request.data.address);
      setAge(request.data.age);
      setGender(request.data.gender);
      setIntroduce(request.data.introduce);
      setNickname(request.data.nickname);
    } catch (error) {
      // 응답 실패 (로그아웃상태)
      alert("로그인 후 사용가능합니다.");
    }
  };
  // 회원 변경 내용 저장
  const editUserData = async () => {
    try {
      // 세션 회원 확인
      const id = "1";
      const response = await axios.put(`/api/v1/member/${id}`, {
        //보내고자 하는 데이터
        age: age,
        introduce: introduce,
        address: address,
        job: job,
        school: school,
        mbti: mbti,
        love_status: love_status,
        religion: religion,
      });
      console.log(response);
      setEditUse(true);
      setPagestatus("추가정보화면");
    } catch (error) {
      // 응답 실패 (로그아웃상태)
      console.error(error);
      alert("로그인 후 사용가능합니다.");
    }
  };

  const AccountPagePlus = async () => {
    // 추가 정보 더보기
    setPagestatus("추가정보화면");
  };
  /*
   * 마이페이지 편집 로직
   */
  // 소개변경
  const handleChangeIntroduce = ({ target: { value } }) => {
    setIntroduce(value);
  };

  // 나이변경
  const handleChangeAge = ({ target: { value } }) => {
    setAge(value);
  };
  // 주소변경
  const handleChangeAddress = ({ target: { value } }) => {
    setAddress(value);
  };
  // 직업변경
  const handleChangeJob = ({ target: { value } }) => {
    setJob(value);
  };
  // 학력변경
  const handleChangeSchool = ({ target: { value } }) => {
    setSchool(value);
  };
  // MBTI 변경
  const handleChangeMbti = ({ target: { value } }) => {
    setMbti(value);
  };
  // 연애상태변경
  const handleChangeLove_status = ({ target: { value } }) => {
    setLove_status(value);
  };
  // 종교변경
  const handleChangeReligion = ({ target: { value } }) => {
    setReligion(value);
  };

  // 관심사 등록 후 모달 제거
  const handleModalSubmit = () => {
    // 비지니스 로직
    setOpen(false);
  };

  const AccountEditBtnEvent = async () => {
    // 편집화면이 아니라면
    if (pagestatus !== "정보편집화면") {
      // 편집 페이지 전환
      setEditUse(false);
      setPagestatus("정보편집화면");
    } else {
      // 편집화면이 아니라면 화면 초기화
      setEditUse(true);
      setPagestatus("");
    }
  };

  // 추가 정보 바뀌는 화면 설정
  const Modalpage = () => {
    switch (pagestatus) {
      case "추가정보화면":
        return (
          <>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                직업
              </Col>
              <Col xs={9} sm={9}>
                {job}
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                학교
              </Col>
              <Col xs={9} sm={9}>
                {school}
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                MBTI
              </Col>
              <Col xs={9} sm={9}>
                {mbti}
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                연애
              </Col>
              <Col xs={9} sm={9}>
                {love_status}
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                종교
              </Col>
              <Col xs={9} sm={9}>
                {religion}
              </Col>
            </Row>
          </>
        );
      case "정보편집화면":
        return (
          <>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                소개
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="introduce"
                  name="introduce"
                  value={introduce || ""}
                  onChange={handleChangeIntroduce}
                  placeholder="소개"
                />
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                관심사
              </Col>
              <Col>
                <span
                  onClick={handleClick}
                  className="mx-1 badge bg-secondary text-wrap"
                >
                  +
                </span>
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                나이
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="age"
                  name="age"
                  value={age || ""}
                  onChange={handleChangeAge}
                  placeholder="나이"
                />
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                지역
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="address"
                  name="address"
                  value={address || ""}
                  onChange={handleChangeAddress}
                  placeholder="지역"
                />
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                직업
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="job"
                  name="job"
                  value={job || ""}
                  onChange={handleChangeJob}
                  placeholder="직업"
                />
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                학교
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="school"
                  name="school"
                  value={school || ""}
                  onChange={handleChangeSchool}
                  placeholder="학교"
                />
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                MBTI
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="mbti"
                  name="mbti"
                  value={mbti || ""}
                  onChange={handleChangeMbti}
                  placeholder="MBTI"
                />
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                연애
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="love_status"
                  name="love_status"
                  value={love_status || ""}
                  onChange={handleChangeLove_status}
                  placeholder="연애"
                />
              </Col>
            </Row>
            <Row>
              <Col xs={3} sm={3} className="text-primary">
                종교
              </Col>
              <Col xs={9} sm={9}>
                <Form.Control
                  type="religion"
                  name="religion"
                  value={religion || ""}
                  onChange={handleChangeReligion}
                  placeholder="종교"
                />
              </Col>
            </Row>
            <Row>
              <Col className="text-center">
                <Button onClick={editUserData}>저장하기</Button>
              </Col>
            </Row>
            <Row>
              <MyModal
                isOpen={isOpen}
                onSubmit={handleModalSubmit}
                likearr={likearr}
                setLikearr={setLikearr}
              />
            </Row>
          </>
        );
      default:
        return (
          <Row onClick={AccountPagePlus}>
            <div className="text-center">추가 정보 더보기</div>
            <div>
              <FontAwesomeIcon
                className="mx-auto d-block"
                icon={faChevronDown}
              />
            </div>
          </Row>
        );
    }
  };

  return (
    <Container className="d-grid gap-2">
      <Row className="my-3">
        <Col className="text-end">
          <FontAwesomeIcon
            icon={editUse ? faPenToSquare : faX}
            onClick={AccountEditBtnEvent}
          />
        </Col>
      </Row>
      <Row className="mx-5 my-3">
        <Image
          roundedCircle
          src="https://avatars.githubusercontent.com/u/62869880?v=4"
          alt=""
          className="rounded img-responsive center-block"
          size="sm"
        ></Image>
      </Row>
      <Row className="text-center">
        <div className="fs-1 fw-bold">{nickname}</div>
        <div>
          {age}세 {gender}
        </div>
        <div className="text-muted">{address}</div>
      </Row>
      <hr></hr>
      <Row>
        <Col xs={3} sm={3} className="text-primary">
          소개
        </Col>
        <Col xs={9} sm={9}>
          {introduce}
        </Col>
      </Row>
      <Row>
        <Col xs={3} sm={3} className="text-primary">
          관심사
        </Col>
        <Col xs={9} sm={9}>
          <Row>
            <Col>
              {likearr.map((like, index) => (
                <span className="mx-1 badge bg-secondary text-wrap" key={index}>
                  {like}
                </span>
              ))}
            </Col>
          </Row>
        </Col>
      </Row>
      <hr></hr>
      {Modalpage()}
      <hr></hr>
    </Container>
  );
};

export default memo(Account);
