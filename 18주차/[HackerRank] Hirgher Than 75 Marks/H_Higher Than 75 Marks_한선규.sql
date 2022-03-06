# MARKS 가 75보다 큰 학생들의 이름을 조회
# 정렬조건은 2가지
# 첫번째 정렬조건은 학생들의 이름 끝자리 3글자
# 두번째 정렬조건은 학생들의 아이디
SELECT NAME FROM STUDENTS WHERE MARKS > 75 ORDER BY SUBSTR(NAME, -3), ID;