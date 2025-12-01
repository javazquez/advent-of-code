defmodule Day10 do
  def look_say(input) do
    String.graphemes(input)
    |> Enum.chunk_by(&Function.identity/1)
    |> Enum.map(&Enum.frequencies/1)
    |> Enum.flat_map(&Map.to_list/1)
    |> Enum.map(fn {x, y} -> "#{y}#{x}" end)
    |> Enum.join()
  end

  def solve_part1(input, times) do
    Stream.iterate(input, &look_say/1)
    |> Stream.drop(times)
    |> Enum.take(1)
    |> Enum.at(0)
    |> String.length()
  end
end
