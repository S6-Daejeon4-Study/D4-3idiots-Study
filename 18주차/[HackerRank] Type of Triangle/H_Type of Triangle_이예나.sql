select 
case
    when (a+b+c)/2 <=a or (a+b+c)/2 <=b or (a+b+c)/2 <=c
    then 'Not A Triangle'
    when a=b and b=c and c=a then 'Equilateral'
    when a=b or b=c or c=a then 'Isosceles'
    else 'Scalene'
end as result
from triangles;