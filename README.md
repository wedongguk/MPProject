# 2023 Mobile Programming Project
# ⚽ 경기도 축구장 조회 & 일기 어플리케이션

## 개요

본 프로젝트는 **경기도 내 축구 경기장 조회** 및 **개인 축구 일기 작성 기능**을 제공하는 Android 기반 어플리케이션입니다. 사용자는 공공 API를 통해 경기장 정보를 확인하고, 자신의 축구 활동을 일기로 기록할 수 있습니다.

- 플랫폼: Android
- 데이터 저장: Firebase Firestore
- 외부 데이터: 경기도 공공데이터 Open API

---

## 기획 의도

- 지역 기반 정보 서비스 구현
- 사용자 일상 기록 기능 강화
- 공공데이터 API 활용 경험
- Firebase를 이용한 백엔드 연동 실습

---

## 주요 기능

### 1. 시작화면
![start](screens/start.png)
- `시작하기` 버튼을 눌러 로그인 화면으로 이동


### 2. 로그인 기능
|![login](screens/login1.png)|![login](screens/login2.png)|![login](screens/login3.png)|![login](screens/login4.png)|
|---|---|---|---|
- 이메일(아이디)과 비밀번호 입력
- 로그인 성공 시 메인 화면으로 이동
- ‘아이디 기억하기’ 기능 지원
- 로그인 실패 시 오류 메시지 출력
- 회원가입 페이지로 이동 가능

### 3. 회원가입 기능
|![join](screens/join1.png)|![join](screens/join2.png)|![join](screens/join3.png)|![join](screens/join4.png)|![join](screens/join5.png)|
|---|---|---|---|---|
- 입력 항목: 이메일, 비밀번호, 닉네임, 나이
- 입력 검증:
  - 이메일 형식 체크
  - 중복 아이디 검사
  - 비밀번호 6자리 이상 조건
- 회원가입 성공 시 로그인 화면으로 이동
- 사용자 정보는 **Firestore Database**에 저장됨
- ![firebase](screens/firebase.png)

### 4. 메인 화면
![main](screens/main.png)
- 두 가지 기능으로 이동 가능:
  - `우리 동네 축구장 찾기` → 경기장 검색 화면
  - `축구 일기 쓰기` → 일기장 화면

### 5. 축구장 검색 기능
|![search](screens/search1.png)|![search](screens/search2.png)|![search](screens/search3.png)|
|---|---|---|
- **Open API 연동**: [경기도 공공체육시설(축구장)](https://openapi.gg.go.kr/PublicTrainingFacilitySoccer)
- `데이터 가져오기` 버튼을 통해 XML 데이터 조회
- 시군명 입력:
  - 입력 없을 시: 경기도 전체 검색
  - 입력 있을 시: 해당 시/군의 경기장 목록 검색
- 출력 정보: 경기장 이름, 바닥 재료, 경기장 크기

### 6. 축구 일기장 기능
![diary](screens/diary.png)
- 날짜 기반으로 일기 관리
- 기능:
  - 일기 작성 날짜 선택
  - 해당 날짜의 일기 조회
  - 기존 일기 수정 및 재저장
- 일기 제목은 날짜로 자동 설정

---
