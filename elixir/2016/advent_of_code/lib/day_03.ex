# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/3

defmodule Day03 do
    
    def is_valid_triangle?(a,b,c) do
        (a + b) > c &&
        (b + c) > a &&
        (a + c) > b         
    end

end
