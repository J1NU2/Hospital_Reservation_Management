<h1 align="center">
  <img src="https://github.com/user-attachments/assets/9323ce6b-3820-4c99-a06b-7447866f537a" alt="HRMS" width="400px">
  <br>
  Hospital Reservation Management System
  <br>
  [ 병원 예약 관리 시스템 ]
  <br>
</h1>

<br>

## 📌 목차
1. [🖥️ 프로젝트 개요](#-프로젝트-개요)
2. [📕 주요 기능 구현](#-주요-기능-구현)
3. [🖌️ 화면 설계](#-화면-설계)
4. [📄 정의서 및 명세서](#-정의서-및-명세서)
5. [🏗️ ERD](#-ERD)
6. [📽️ 영상](#-영상)
7. [📂 발표자료](#-발표자료)

<br>

## 🖥️ 프로젝트 개요
### 📆 개발기간
  - 2024.10.14 ~ 2024.10.24 개발
  ![WBS](https://github.com/user-attachments/assets/fc8abc78-d12d-4418-8ab8-4b32638bebdd)

### 🔖 프로젝트 주제
  - 주제 : 병원 예약 관리 시스템(HRMS)
  - 구성원 : 1인 프로젝트
  - 선정 이유
    <br>
    ① 직접 병원에 갈 시간이 부족하거나, 전화로 진료 예약을 하기 힘든 사람들을 위한 프로그램을 구현하고자 함
    <br>
    ② 사용자가 원하는 시간대에 미리 병원 진료를 예약하고, 관계자(의사)가 예약 목록을 확인할 수 있고자 함

### ⚙️ 개발환경 및 도구
![개발환경](https://github.com/user-attachments/assets/48e7da36-83a8-43b9-a80b-2c5605d20c44)
  - IDE :
    ![Eclipse](https://img.shields.io/badge/Eclipse-2C2255.svg?&style=for-the-badge&logo=Eclipse&logoColor=white)
  - Language :
    ![Java 1.8 (JDK 1.8)](https://img.shields.io/badge/Java%208v%20(JDK%201.8)-007396.svg?&style=for-the-badge&logo=Java%208v%20(JDK%201.8)&logoColor=white)
  - DBMS :
    ![Oracle 11g r2](https://img.shields.io/badge/Oracle%2011g%20r2-9F1D20.svg?&style=for-the-badge&logo=Oracle%2011g%20r2&logoColor=white)
  - DB Tool :
    ![SQL Developer](https://img.shields.io/badge/SQL%20Developer-242F4B.svg?&style=for-the-badge&logo=SQL%20Developer&logoColor=white)
    ![SQL Plus](https://img.shields.io/badge/SQL%20Plus-2AA5DC.svg?&style=for-the-badge&logo=SQL%20Plus&logoColor=white)
    ![ERD Cloud](https://img.shields.io/badge/ERD%20Cloud-6B46C1.svg?&style=for-the-badge&logo=ERD%20Cloud&logoColor=white)
  - Library :
    ![Java AWT/Swing](https://img.shields.io/badge/Java%20AWT/Swing-007396.svg?&style=for-the-badge&logo=Java%20AWT/Swing&logoColor=white)
    ![ojdbc6.jar](https://img.shields.io/badge/ojdbc6.jar-FE5F50.svg?&style=for-the-badge&logo=ojdbc6.jar&logoColor=white)
  - VCS :
    ![GitHub](https://img.shields.io/badge/github-181717.svg?&style=for-the-badge&logo=github&logoColor=white)
    ![Git Bash](https://img.shields.io/badge/Git%20Bash-F05032.svg?&style=for-the-badge&logo=git&logoColor=white)

### 🧑‍🤝‍🧑 멤버 구성
|팀원명|프로필|담당업무|
|---|---|---|
|[박진우](https://github.com/J1NU2)|<p align="center"><img src="https://github.com/user-attachments/assets/b4bdb924-a878-40b9-a448-aea6ee2259c8" width="100"></p>|프로젝트 설계, 데이터베이스 설계, UI 설계, 클래스 설계<br>로그인 및 회원가입 기능, 진료 예약 기능 구현|

<br>

## 📕 주요 기능 구현
<details>
  <summary><b>① 회원가입 입력 제한</b></summary>
  <h3>회원가입 화면</h3>
  <ul>
    <li>회원가입 정보 입력 시 제한 사항</li>
    <ul>
      <li>최대 길이</li>
      <li>특정 문자 입력 제한 (ex. 아이디:한글,특수문자, 주민번호:한글,영어,특수문자)</li>
      <img src="https://github.com/user-attachments/assets/0a517ccc-9386-4581-b079-57505f1d749b">
    </ul>
  </ul>
  <br>
</details>
<details>
  <summary><b>② 회원가입 나이/성별 자동 입력</b></summary>
  <h3>회원가입 화면</h3>
  <ul>
    <li>주민번호 입력 시 자동 입력</li>
    <ul>
      <li>나이 : 주민번호 앞자리의 생년월일을 기준으로 만나이로 자동 계산</li>
      <li>성별 : 주민번호 뒷자리의 첫번째 자리를 기준으로 남성(M), 여성(F) 판별</li>
      <img src="https://github.com/user-attachments/assets/9f71dcaf-003e-4327-99cf-241a1ee2ea81">
    </ul>
  </ul>
  <br>
</details>
<details>
  <summary><b>③ 현재 날짜 선택 시 예약일 배치</b></summary>
  <h3>고객(환자) : 예약하기 화면</h3>
  <ul>
    <li>현재 날짜 선택 시 년/월 기준 일 표시</li>
    <img src="https://github.com/user-attachments/assets/b63d862a-561b-414e-9ebd-529199bec0de">
  </ul>
  <br>
</details>
<details>
  <summary><b>④ 예약 내역 리스트 확인</b></summary>
  <h3>환자 및 의사 : 예약 화면</h3>
  <ul>
    <li>고객(환자) : 현재 예약 내역 및 전체 예약 내역 확인</li>
    <ul>
      <li>현재 예약 내역</li>
      <img src="https://github.com/user-attachments/assets/0f2d615e-d8fe-4877-a69a-d7a447cdd5d9">
      <br>
      <li>전체 예약 내역</li>
      <img src="https://github.com/user-attachments/assets/6a5c4109-26e5-4366-aecd-d0ffe6d94f50">
    </ul>
    <br>
    <li>관계자(의사) : 자신에게 예약된 전체 예약 내역 확인</li>
    <ul>
      <li>환자 예약 내역</li>
      <img src="https://github.com/user-attachments/assets/9ea9d6d4-4c1a-4d40-9164-10a6795f6844">
    </ul>
  </ul>
</details>

<br>

## 🖌️ 화면 설계
![UsecaseDiagram](https://github.com/user-attachments/assets/9c2d1754-5c35-48d2-ba5e-6c2e136d96c7)
<br>
![Mockup](https://github.com/user-attachments/assets/52d3831c-d627-403e-9213-4cef1977013a)

<br>

## 📄 정의서 및 명세서
[(👋 Click) 프로젝트 정의서 및 명세서](https://docs.google.com/spreadsheets/d/13u1YEDT1LHjEmL1LEIXfqM1haCqhPTS_WeAJEBv8Ai4/edit?usp=sharing)

<br>

## 🏗️ ERD
[(👋 Click) ERD Cloud](https://www.erdcloud.com/d/2ec4a2znoC3ve6dMf)
<br>
![ERD](https://github.com/user-attachments/assets/c2d07cc9-cc60-4fd7-9102-ac1e4e2be7f9)

<br>

## 📽️ 영상
[(👋 Click) 프로젝트 동영상](https://drive.google.com/file/d/1hJfhXx4wGVSjIWv7QwzysHmhZzCCFfmi/view?usp=sharing)

<br>

## 📂 발표자료
[(👋 Click) PPT 발표자료](https://www.canva.com/design/DAGUWQDx8Yg/OJtQ8Jb9mjJHM5uAcEwSgQ/edit?utm_content=DAGUWQDx8Yg&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
