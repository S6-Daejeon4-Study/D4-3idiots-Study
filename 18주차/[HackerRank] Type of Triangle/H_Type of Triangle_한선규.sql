# 세 변의 길이가 모두 같으면 정삼각형 'Equilateral'
# 두 변의 길이가 같으면 이등변 삼각형 'Isosceles'
# 세 변의 길이가 다른 삼각형 'Scalene'
# 가장 긴 변의 길이가 나머지 두변의 길이의 합보다 크다면 삼각형이 될 수 없다. 'Not A Triangle'
SELECT
    CASE
        WHEN A + B > C AND B + C > A AND A + C > B THEN
            CASE
                WHEN A = B AND B = C THEN 'Equilateral'
                WHEN A = B OR B = C OR A = C THEN 'Isosceles'
                ELSE 'Scalene'
            END
        ELSE 'Not A Triangle'
    END
FROM TRIANGLES;