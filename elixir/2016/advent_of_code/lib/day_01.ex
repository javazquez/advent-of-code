# http://adventofcode.com/2016/day/1
defmodule Day01 do
    def distance(directions , move_list_tuple \\ [{:north, 0 , 0}] )

    def distance([] , [{ _direction, x_coord , y_coord } | _rest] = whole_list ) do        
        {Kernel.abs(x_coord) + Kernel.abs(y_coord), whole_list}
    end

    def distance([ str_direction | rest ] , [{ direction, x_coord , y_coord }| _paths] = moves ) do
        [l_or_r, blocks] = String.split(str_direction, "", parts: 2)
        new_direction = setcompass(l_or_r, direction)
        { blocks, _ } = Integer.parse(blocks)
        # need to generate all the intersections
        latest_destinations = Enum.map(blocks..1, 
                                fn block -> move({new_direction, x_coord, y_coord}, block) end)
        distance(rest, latest_destinations ++ moves)
    end

    defp setcompass("R",direction) do
         case direction do
            :north -> :east
            :east -> :south
            :south -> :west
            :west -> :north
        end                
    end

     defp setcompass("L", direction) do
         case direction do
            :north -> :west
            :east -> :north
            :south -> :east
            :west -> :south
        end                
    end

    defp move({direction, x, y}, block) do        
        case direction do
            :north -> {:north, x, y + block}
            :east -> {:east, x + block, y }
            :south -> {:south, x, y - block}
            :west -> {:west, x - block, y }
        end                
    end
end



