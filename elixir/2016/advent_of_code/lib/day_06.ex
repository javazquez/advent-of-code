# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/6

defmodule Day06 do

  def columns_to_rows(input) do
    input 
    |> Enum.map(&String.graphemes/1)
    |> List.zip 
    |> Enum.map(&Tuple.to_list/1) 
    |> Enum.map(fn row -> Enum.group_by(row, &(&1)) end) 
    |> Enum.map(&Map.values/1)
  end    
end