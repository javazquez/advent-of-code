# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/2
defmodule Day02 do
 def pad_key([x,y], pad) do
       Enum.at(pad, y) |> Enum.at(x)
  end

  def in_bounds?([_x,_y] = proposed_move, pad) do
      pad_key(proposed_move, pad) != 0
  end

# for each input char get the position
  def push_buttons([], pad, [_x|_y]= last_pos) do
     {pad_key(last_pos, pad), last_pos} 
  end

  def push_buttons([cmd_char| r_commands] = input, pad, [x,y] = start_position ) do
    new_pos = move(cmd_char,start_position,pad)
    push_buttons(r_commands, pad, new_pos)
  end

  def move(command_char, [x,y] = current_position, pad) do

       item = case command_char do
            "U" -> [x, y - 1]
            "D" -> [x, y + 1]
            "L" -> [x  - 1, y]
            "R" -> [x  + 1, y]
        end 
        if in_bounds? item, pad do
            item
        else            
            current_position
        end
  end
 
        
end
