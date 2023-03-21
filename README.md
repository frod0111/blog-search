## _블로그 검색, 인기 키워드 검색 프로젝트_

#

### 사용 기술

#### - Java

#### - Springboot

#### - webClient

#### - Mapstruct

#### - Caffeine - cache

#### - Scheduler

#

#

### JAR 파일 다운로드 URL

#### - https://drive.google.com/file/d/16uHImqbNOHyxupMKIhlv09DY31fSKpzA/view?usp=share_link

#

### 프로젝트 설명

##### - API, CORE 분리 모듈 구성.

##### - 데이터 매핑을 위해 Mapstruct 사용.

#

#

### 추가기능

- 카카오, 네이버 API를 선택해서 호출할 수 있도록 하였습니다.
- 인기 키워드 검색어 조회API의 트래픽을 줄이고자 2초 캐싱(Caffeine)을 적용하였습니다.
- 인기 키워드 검색어 조회 성능을 위해 등록 후 3일까지의 데이터를 노출합니다.
- 인기 키워드 검색어의 무한정 데이터 삽입을 방지 하고자, 등록 후 3일 지난 데이터는 매일 자정 1분에 스케줄러로 삭제합니다.