# Minesweeper

안드로이드로 구현한 지뢰찾기

## (1)MainActivity
#### 1)기본 설명 
  - UI 디자인 및 지뢰찾기 게임 기능 구현
  - xml은 데이터 바인딩을 이용하여 변수 조작
#### 2)코드 구조
  - binding : activity_main.xml을 데이터 바인딩으로 이용하기 위한 변수
  - numberOfBtn : 게임 승리를 위한 조건 , 지뢰를 제외한 71개의 버튼이 모두 오픈되었을때 승리로 인정
  - flagNumbers : 깃발의 개수를 표현하기위한 변수
  - flag : 깃발을 표현하기 위한 변수
  - buttons[][] : 9x9의 BlockButton 2차원 배열
  - onCreate()
  - createView()   
	  + xml에 9x9 형태의 지뢰 배열 표현   
	  + 깃발상태 초기상태 설정버튼 추가  
	  + 각 버튼이 눌렸을때 깃발이 꽂혀있지않다면 uncoverNeighborMines() 호출  
	  + 지뢰를 누른다면 그대로 게임 종료  
	  + 깃발상태로 버튼을 누르면 버튼에 +를 표현하고 Flag 1 감소  
  - makeNeighborMines() : 자신 주변 8개의 존재하는 지뢰 개수를 설정하는 메소드
  - findNeighborMines(int x , int y) : 자신 주변 8개의 존재하는 지뢰 개수를 찾는 메소드
  - makeMine() : 9x9배열에서 랜덤하게 10개의 버튼을 골라 지뢰로 설정하는 메소드
  - checkMine(int x , int y) : 주어진 좌표의 버튼이 지뢰인지 확인하는 메소드
  - checkNeighborMines(int x , int y) : 주어진 좌표의 버튼이 아직 오픈되지않았는지와 깃발이 꽂혀있지않는지 확인하는 메소드
  - uncoverNeighborMines(int x , int y) : 주어진 좌표의 버튼의 neighborMines를 표현하고 그것이 0이라면 주변 버튼의 neighborMines를 표현 
	

## (2)BlockButton  
#### 1)기본 설명
  - 버튼 클래스를 상속받은 클래스
  - 각각의 버튼에 대한 속성들을 가지고 있음
#### 2)코드 구조
  - uncover : 아직 오픈되지 않았다는 상태를 나타냄 , 오픈이 된다면 false
  - mine : 버튼 자신이 지뢰인지를 판별하는 변수 , 지뢰라면 true
  - x,y : 9x9 배열에서의 자신의 위치에 대한 x,y 좌표
  - flag : 버튼 자신에 깃발이 놓여져있는지에 대한 상태 , 깃발이 놓여지면 true
  - neighborMines : 자신 주변의 8개 버튼중 지뢰의 개수를 나타내는 변수 , 기본 0
  + 각각 변수들에 대한 getter , setter 메소드 구현
	
## (3)GameWinActivity
#### 1)기본 설명
  - 게임이 승리했을때 넘어가는 xml의 activity로 게임승리 확인과 다시하기 버튼 표현

## (4)GameDefeatActivity
#### 1)기본 설명
  - 게임이 패배했을때 넘어가는 xml의 activity로 게임패배 확인과 다시하기 버튼 표현
